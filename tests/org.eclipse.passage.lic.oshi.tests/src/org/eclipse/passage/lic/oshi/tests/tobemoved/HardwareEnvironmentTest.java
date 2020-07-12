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
package org.eclipse.passage.lic.oshi.tests.tobemoved;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Disk;
import org.eclipse.passage.lic.internal.base.inspection.hardware.OS;
import org.eclipse.passage.lic.internal.oshi.tobemoved.HardwareEnvironment;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class HardwareEnvironmentTest {

	@Test
	public void isDedicatedToHardware() {
		assertEquals(new EvaluationType.Hardware(), new HardwareEnvironment().id());
	}

	@Test
	public void inspects() {
		try {
			assertFalse(new HardwareEnvironment().isAssuptionTrue(//
					new OS.Family(), //
					"not-existing-operating-system")); //$NON-NLS-1$
		} catch (LicensingException e) {
			fail("Is not supposed to fail on valid data"); //$NON-NLS-1$
		}
	}

	@Test(expected = NullPointerException.class)
	public void doesNotInspectNullProperty() {
		try {
			new HardwareEnvironment().isAssuptionTrue(null, "none"); //$NON-NLS-1$
		} catch (LicensingException e) {
			fail("No insection activity is intended to be triggered for invalid input data"); //$NON-NLS-1$
		}
	}

	@Test(expected = NullPointerException.class)
	public void doesNotInspectForNullValue() {
		try {
			new HardwareEnvironment().isAssuptionTrue(new OS.Family(), null); // $NON-NLS-1$
		} catch (LicensingException e) {
			fail("No insection activity is intended to be triggered for invalid input data"); //$NON-NLS-1$
		}
	}

	@Test
	public void knowsSimpleRegexp() {
		try {
			assertTrue(new HardwareEnvironment().isAssuptionTrue(new OS.Family(), "*"));//$NON-NLS-1$
		} catch (LicensingException e) {
			fail("Is not supposed to fail on valid data"); //$NON-NLS-1$
		}
	}

	@Test
	public void standsSimultaneousRequests() {
		// given: single instance of the env and lots of requesters
		HardwareEnvironment hardware = new HardwareEnvironment();
		int threads = 128;
		CountDownLatch readySteadyGo = new CountDownLatch(1);
		CountDownLatch done = new CountDownLatch(threads);
		List<InspectionDemand> demands = IntStream.range(0, threads) //
				.mapToObj(no -> new InspectionDemand(hardware, readySteadyGo, done, no))//
				.collect(Collectors.toList());

		// when: run all of the requesters simultaneously (latched by readySteadyGo)
		Executor executor = Executors.newFixedThreadPool(threads);
		demands.stream().forEach(executor::execute);
		try {
			Thread.sleep(2000); // let'em all start and hold waiting for each other
			readySteadyGo.countDown(); // and now trigger'em all to ddos the env
			done.await(); // and just wait until each of'em finish
		} catch (InterruptedException e) {
			fail("Test has been interrupted"); //$NON-NLS-1$
		}

		// then: all of'em succeed
		assertTrue(demands.stream().allMatch(InspectionDemand::result));
	}

	private static final class InspectionDemand implements Runnable {
		private final CountDownLatch altogether;
		private final CountDownLatch done;
		private final HardwareEnvironment env;
		private boolean result = false;

		InspectionDemand(HardwareEnvironment env, CountDownLatch altogether, CountDownLatch done, int no) {
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
				result = !env.isAssuptionTrue(new Disk.Serial(), "not-existing-disk-serial"); //$NON-NLS-1$
			} catch (Exception e) {
				result = false;
			}
		}

		boolean result() {
			return result;
		}
	}

}
