/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
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

import org.eclipse.passage.lbc.internal.api.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.FloatingState;
import org.eclipse.passage.lbc.internal.base.EagerFloatingState;
import org.eclipse.passage.lbc.internal.base.Failure;
import org.eclipse.passage.lbc.internal.base.ProductUserRequest;
import org.eclipse.passage.lbc.internal.base.acquire.Acquisition;
import org.eclipse.passage.lic.floating.model.api.GrantAcqisition;
import org.eclipse.passage.lic.floating.model.meta.FloatingPackage;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.emf.EObjectFromBytes;
import org.junit.Test;

public final class ExtensiveReleaseTest {

	private final TestData data = new TestData();
	private final int noGrants = new Failure.NoGrantsAvailable(data.product, data.feature).error().code();

	@Test
	public void concurrentAcquireAndRelease() throws InterruptedException, ExecutionException {
		// having
		int amount = 128;
		// when
		Set<Future<Result>> futures = runProtectedActionsConcurrently(amount);
		// then
		Map<Result, Integer> results = countResults(futures);
		assertEquals(0, results.get(Result.Wiered).intValue()); // no one ended wierdly
		assertTrue(results.get(Result.GainedAndReleased) >= 4); // we have only 4
		assertEquals(amount, results.get(Result.GainedAndReleased) + results.get(Result.NotGainedNotExecuted));
	}

	private Set<Future<Result>> runProtectedActionsConcurrently(int amount) throws InterruptedException {
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 10);
		FloatingState state = new EagerFloatingState(new LicFolder());
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
		GainedAndReleased, NotGainedNotExecuted, Wiered
	}

	private final class ProtectedAction implements Callable<Result> {

		private final FloatingState state;

		private ProtectedAction(FloatingState state) {
			this.state = state;
		}

		@Override
		public Result call() throws Exception {
			FloatingResponse acq = new Acquisition(acquireRequest()).get();
			if (acq.failed() && (noGrants == acq.error().code())) {
				return Result.NotGainedNotExecuted;
			}
			if (!acq.failed()) {
				GrantAcqisition acquisition = acquisition(acq);
				Thread.sleep(1000); // emulating a protected action work
				FloatingResponse rel = new Acquisition(releaseRequest(acquisition)).returnBack();
				if (!rel.failed()) {
					return Result.GainedAndReleased;
				}
			}
			return Result.Wiered;
		}

		private ProductUserRequest acquireRequest() throws LicensingException {
			return new ProductUserRequest(new FeatureRequest(//
					new ConditionAction.Acquire(), data.product, data.feature, data.albert.id, state).get());
		}

		private ProductUserRequest releaseRequest(GrantAcqisition acquisition) throws LicensingException {
			return new ProductUserRequest(new FeatureRequest(//
					new ConditionAction.Acquire(), data.product, data.feature, data.elder.id, acquisition, state)
							.get());
		}

		private GrantAcqisition acquisition(FloatingResponse response) throws LicensingException, IOException {
			try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
				response.write(stream);
				stream.flush();
				return new EObjectFromBytes<>(stream.toByteArray(), GrantAcqisition.class)//
						.get(Collections.singletonMap(FloatingPackage.eNS_URI, FloatingPackage.eINSTANCE));
			}
		}

	}
}
