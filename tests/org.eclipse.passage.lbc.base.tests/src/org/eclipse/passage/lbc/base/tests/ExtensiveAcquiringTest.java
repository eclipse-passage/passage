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
import static org.junit.Assert.fail;

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
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.junit.Test;

public final class ExtensiveAcquiringTest {

	private final TestData data = new TestData();

	@Test
	public void concurrentAcquire() throws InterruptedException {
		// having
		int amount = 128;
		// when
		Set<Future<FloatingResponse>> futures = runConcurrentAcquireRequest(amount);
		// then
		int[] counts = countGainsAndLates(futures);
		assertEquals(4, counts[0]); // gain grant acquisition, we have only 4
		assertEquals(amount - 4, counts[1]); // all the rest has 'no available grants' response
	}

	private Set<Future<FloatingResponse>> runConcurrentAcquireRequest(int amount) throws InterruptedException {
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 10);
		FloatingState state = new EagerFloatingState(new LicFolder());
		Set<Future<FloatingResponse>> futures = IntStream.range(0, amount)//
				.mapToObj(i -> pool.submit(new Acq(state)))//
				.collect(Collectors.toSet());
		pool.shutdown();
		return futures;
	}

	private int[] countGainsAndLates(Set<Future<FloatingResponse>> futures) throws InterruptedException {
		int gains = 0;
		int lates = 0;
		int none = new Failure.NoGrantsAvailable(data.product(), data.feature()).error().code();
		for (Future<FloatingResponse> future : futures) {
			FloatingResponse response;
			try {
				response = future.get();
			} catch (ExecutionException e) {
				fail(e.getCause().getMessage());
				return new int[0];// unreachable
			}
			if (!response.failed()) {
				gains++;
			} else if (none == response.error().code()) {
				lates++;
			}
		}
		return new int[] { gains, lates };
	}

	private final class Acq implements Callable<FloatingResponse> {

		private final FloatingState state;

		Acq(FloatingState state) {
			this.state = state;
		}

		@Override
		public FloatingResponse call() throws Exception {
			return new Acquisition(request()).get();
		}

		private ProductUserRequest request() throws LicensingException {
			return new ProductUserRequest(new FeatureRequest(//
					new ConditionAction.Acquire(), data.product(), data.feature(), data.albert().id(), state).get());
		}

	}

}
