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
package org.eclipse.passage.lic.api.tests.inspection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNoException;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.inspection.EnvironmentProperty;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironment;
import org.junit.Test;

public abstract class RuntimeEnvironmentContractTest {
	@Test
	public void isDedicatedToProperEvaluationType() {
		assertEquals(expectedEvaluationType(), environment().id());
	}

	@Test
	public void inspects() {
		try {
			assertFalse(environment().isAssuptionTrue(property(), invalidPropertyValue()));
		} catch (LicensingException e) {
			assumeNoException(e); // skip the test in the case of environment denial
		}
	}

	@Test(expected = NullPointerException.class)
	public void doesNotInspectNullProperty() {
		try {
			environment().isAssuptionTrue(null, "none"); //$NON-NLS-1$
		} catch (LicensingException e) {
			fail("No insection activity is intended to be triggered for invalid input data"); //$NON-NLS-1$
		}
	}

	@Test(expected = NullPointerException.class)
	public void doesNotInspectForNullValue() {
		try {
			environment().isAssuptionTrue(property(), null); // $NON-NLS-1$
		} catch (LicensingException e) {
			fail("No insection activity is intended to be triggered for invalid input data"); //$NON-NLS-1$
		}
	}

	@Test
	public void knowsSimpleRegexp() {
		try {
			assertTrue(environment().isAssuptionTrue(property(), "*"));//$NON-NLS-1$
		} catch (LicensingException e) {
			assumeNoException(e); // skip the test in the case of environment denial
		}
	}

	@Test
	public void depictsState() {
		try {
			String state = environment().state();
			assertNotNull(state);
			assertFalse(state.trim().isEmpty());
		} catch (LicensingException e) {
			assumeNoException(e); // skip the test in the case of environment denial
		}
	}

	@Test
	public void standsSimultaneousRequests() {
		// given: single instance of the env and lots of requesters
		RuntimeEnvironment environment = environment();
		int threads = 128;
		CountDownLatch readySteadyGo = new CountDownLatch(1);
		CountDownLatch done = new CountDownLatch(threads);
		List<InspectionDemand> demands = IntStream.range(0, threads) //
				.mapToObj(no -> new InspectionDemand(environment, readySteadyGo, done))//
				.collect(Collectors.toList());

		// when: run all of the requesters simultaneously (latched by readySteadyGo)
		Executor executor = Executors.newFixedThreadPool(threads);
		demands.stream().forEach(executor::execute);
		try {
			Thread.sleep(2000); // let'em all start and hold waiting for each other
			readySteadyGo.countDown(); // and now trigger'em all to ddos the env
			done.await(); // and just wait until each of'em finish
		} catch (InterruptedException e) {
			assumeNoException(e); // skip the test then: further checks will fail
		}

		// then: all of'em succeed
		assertTrue(demands.stream()//
				.filter(InspectionDemand::notSkipped) //
				.allMatch(InspectionDemand::result));
	}

	private final class InspectionDemand implements Runnable {
		private final CountDownLatch altogether;
		private final CountDownLatch done;
		private final RuntimeEnvironment env;
		private boolean result = false;
		private boolean skip = false;

		InspectionDemand(RuntimeEnvironment env, CountDownLatch altogether, CountDownLatch done) {
			this.env = env;
			this.altogether = altogether;
			this.done = done;
		}

		@Override
		public void run() {
			try {
				altogether.await(); // wait for a common signal to start simultaneously
				ask();
				done.countDown(); // report you are done
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		private void ask() {
			try {
				result = !env.isAssuptionTrue(property(), invalidPropertyValue());
			} catch (Exception e) {
				skip = true;
			}
		}

		boolean result() {
			return result;
		}

		boolean notSkipped() {
			return !skip;
		}
	}

	protected abstract RuntimeEnvironment environment();

	protected abstract EvaluationType expectedEvaluationType();

	protected abstract String invalidPropertyValue();

	protected abstract EnvironmentProperty property();

}
