/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lbc.base.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.passage.lbc.internal.base.EagerFloatingState;
import org.eclipse.passage.lbc.internal.base.acquire.Acquisition;
import org.eclipse.passage.lbc.internal.base.acquire.NoGrantsAvailable;
import org.eclipse.passage.lbc.internal.base.api.FloatingState;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.PassageAction;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class ExtensiveReleaseTest {

	private final TestData data = new TestData();
	private final int noGrants = new NoGrantsAvailable(data.product(), data.feature()).error().code();

	@Test
	// TODO: add enough test grants to check capacity protection:
	// TODO: min {grant.capacity, unlicensedCapacity}
	public void concurrentAcquireAndRelease() throws InterruptedException, ExecutionException {
		// having
		int amount = 128;
		// when
		Set<Future<Result>> futures = runProtectedActionsConcurrently(amount);
		// then
		Map<Result, Integer> results = countResults(futures);
		assertEquals(0, results.get(Result.Weird).intValue()); // no one ended weirdly
		int grantedAndReleased = results.get(Result.GainedAndReleased);
		assertTrue(String.format("granted and released: %d", grantedAndReleased), //$NON-NLS-1$
				grantedAndReleased >= 3); // we have only 3, can possible grant several times each
		assertEquals(amount, grantedAndReleased + results.get(Result.NotGainedNotExecuted));
	}

	private Set<Future<Result>> runProtectedActionsConcurrently(int amount) throws InterruptedException {
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 10);
		FloatingState state = new EagerFloatingState(new TestLicFolder());
		Set<Future<Result>> futures = IntStream.range(0, amount)//
				.mapToObj(i -> pool.submit(new ProtectedAction(state)))//
				.collect(Collectors.toSet());
		pool.shutdown();
		return futures;
	}

	private Map<Result, Integer> countResults(Set<Future<Result>> futures)
			throws InterruptedException, ExecutionException {
		Map<Result, Integer> counts = new HashMap<>();
		Arrays.stream(Result.values()).forEach(r -> counts.put(r, 0));
		for (Future<Result> future : futures) {
			Result result = future.get();
			counts.put(result, counts.get(result) + 1);
		}
		return counts;
	}

	private enum Result {
		GainedAndReleased, NotGainedNotExecuted, Weird
	}

	private final class ProtectedAction implements Callable<Result> {

		private final FloatingState state;

		private ProtectedAction(FloatingState state) {
			this.state = state;
		}

		@Override
		public Result call() throws Exception {
			ProductUserRequest<RawRequest> request = acquireRequest();
			NetResponse acq = new Acquisition(request).get();
			if (acq.failed() && (noGrants == acq.error().code())) {
				return Result.NotGainedNotExecuted;
			}
			if (!acq.failed()) {
				GrantAcqisition acquisition = acquisition(acq, request);
				Thread.sleep(1000); // emulating a protected action work
				NetResponse rel = new Acquisition(releaseRequest(acquisition)).returnBack();
				if (!rel.failed()) {
					return Result.GainedAndReleased;
				}
			}
			return Result.Weird;
		}

		private ProductUserRequest<RawRequest> acquireRequest() throws LicensingException {
			return new ProductUserRequest<>(new FeatureRequest(//
					new PassageAction.Acquire(), //
					data.product(), //
					data.feature(), //
					data.albert().id(), //
					state).get());
		}

		private ProductUserRequest<RawRequest> releaseRequest(GrantAcqisition acquisition) throws LicensingException {
			return new ProductUserRequest<>(new FeatureRequest(//
					new PassageAction.Acquire(), //
					data.product(), //
					data.feature(), //
					data.elder().id(), //
					acquisition, //
					state).get());
		}

		private GrantAcqisition acquisition(NetResponse response, ProductUserRequest<RawRequest> request)
				throws LicensingException, IOException {
			return new DecodedResponse.GrantAck(response, request.raw()).get();
		}

	}

}
