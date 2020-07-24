/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.base.tests.permission.observatory;

import static org.junit.Assert.fail;

import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.function.Consumer;

import org.eclipse.passage.lic.internal.api.observatory.Limited;
import org.eclipse.passage.lic.internal.base.observatory.CheckSchedule;
import org.eclipse.passage.lic.internal.base.observatory.BaseObservatory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test suite for {@linkplain BaseObservatory}, which mostly intended to keep an eye
 * on {@linkplain Limited} instances, given under it's watch and fire
 * {@code expired} action when a {@linkplain Limited} expires.
 * 
 * @since 0.6
 */
@SuppressWarnings("restriction")
public class ObservatoryTest {

	/**
	 * <p>
	 * This a two seconds test.
	 * </p>
	 * Observatory is opened and the guard is scheduled to check it through every 1
	 * second.
	 * <ul>
	 * <li>0: put two {@code Limited}s under the observatory's watch, each valid for
	 * 1 seconds</li>
	 * <li>0.5: in a half a second check nothing is released yet</li>
	 * <li>2: in a 1.5 seconds more check both entries are released.</li>
	 * </ul>
	 */
	@Test
	public void twoSeconds() {
		Countdown countdown = new Countdown(2);
		BaseObservatory<Limited> observatory = new BaseObservatory<Limited>(buzy(), countdown);
		observatory.open();
		observatory.watch(new TimeLimited(1));
		observatory.watch(new TimeLimited(1));

		waitABit(500);
		Assert.assertFalse(countdown.complete()); // not yet

		assertCountdownIsComplete(countdown, 700); // kinda now
	}

	@Test
	public void forget() {
		Countdown countdown = new Countdown(1); // watch two entries, but then forget one of them
		BaseObservatory<Limited> observatory = new BaseObservatory<Limited>(buzy(), countdown);
		observatory.open();
		observatory.watch(new TimeLimited(1));
		TimeLimited toBeForgotten = new TimeLimited(1);
		observatory.watch(toBeForgotten);

		waitABit(500);
		Assert.assertFalse(countdown.complete());

		observatory.forget(toBeForgotten);
		assertCountdownIsComplete(countdown, 700);
	}

	@Test

	public void guardIsReliable() {
		testGuardReliability(new Trouble());
	}

	@Test
	public void guardIsHighlyReliable() {
		testGuardReliability(new Disaster());
	}

	private void testGuardReliability(Consumer<Set<Limited>> sabotage) {
		Countdown countdown = new Countdown(4);
		BaseObservatory<Limited> observatory = new BaseObservatory<Limited>(buzy(), countdown.andThen(sabotage));
		observatory.open();
		observatory.watch(new TimeLimited(1));
		observatory.watch(new TimeLimited(1));

		waitABit(1500);
		observatory.watch(new TimeLimited(1));
		waitABit(100);
		observatory.watch(new TimeLimited(1));

		assertCountdownIsComplete(countdown, 1200);
	}

	private void waitABit(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void assertCountdownIsComplete(Countdown countdown, int delay) {
		waitABit(delay);
		for (int i = 0; i < 5; i++) {
			if (countdown.complete())
				return;
			waitABit(500);
		}
		if (countdown.complete())
			fail("Some expired Limited are still active."); //$NON-NLS-1$
	}

	private CheckSchedule buzy() {
		return new CheckSchedule(1, ChronoUnit.SECONDS);
	}
	// multithreaded usage
	// failing in Limited::expire implementation
	// negative schedule

}
