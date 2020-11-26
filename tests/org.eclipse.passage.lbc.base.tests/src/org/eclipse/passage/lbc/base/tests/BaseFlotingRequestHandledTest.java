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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lbc.internal.api.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.RawRequest;
import org.eclipse.passage.lbc.internal.base.BaseFlotingRequestHandled;
import org.eclipse.passage.lbc.internal.base.Failure;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.junit.Test;

public final class BaseFlotingRequestHandledTest {

	@Test
	public void handleMine() {
		testActionSupported(new ConditionAction.Mine());
	}

	@Test
	public void handleAquire() {
		testActionSupported(new ConditionAction.Acquire());
	}

	@Test
	public void handleRelease() {
		testActionSupported(new ConditionAction.Release());
	}

	@Test
	public void doNotHandleNull() {
		assertActionIsNotSupported(new BaseFlotingRequestHandled(new RequestConstructed().get()).get());
	}

	@Test
	public void doNotHandleForeignAction() {
		assertActionIsNotSupported(//
				new BaseFlotingRequestHandled(//
						requestOfAction(new ConditionAction.Of("strange")) //$NON-NLS-1$
				).get());
	}

	private void testActionSupported(ConditionAction action) {
		assertActionIsSupported(new BaseFlotingRequestHandled(requestOfAction(action)).get());
	}

	private void assertActionIsSupported(FloatingResponse response) {
		assertTrue(response.failed());
		assertNotEquals(//
				new Failure.BadRequestUnknownAction("any").error().code(), // //$NON-NLS-1$
				response.error().code());
	}

	private void assertActionIsNotSupported(FloatingResponse response) {
		assertTrue(response.failed());
		assertEquals(//
				new Failure.BadRequestUnknownAction("any").error().code(), // //$NON-NLS-1$
				response.error().code());
	}

	private RawRequest requestOfAction(ConditionAction action) {
		return new RequestConstructed().withAction(action).get();
	}
}
