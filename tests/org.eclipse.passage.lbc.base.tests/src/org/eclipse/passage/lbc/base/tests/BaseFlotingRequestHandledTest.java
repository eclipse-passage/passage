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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lbc.internal.base.FlotingRequestHandled;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.PassageAction;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.handle.Failure;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class BaseFlotingRequestHandledTest {

	@Test
	public void handleMine() throws LicensingException {
		testActionSupported(new PassageAction.Mine());
	}

	@Test
	public void handleAquire() throws LicensingException {
		testActionSupported(new PassageAction.Acquire());
	}

	@Test
	public void handleRelease() throws LicensingException {
		testActionSupported(new PassageAction.Release());
	}

	@Test
	public void doNotHandleNull() throws LicensingException {
		assertActionIsNotSupported(new FlotingRequestHandled(new RequestConstructed().getPure()).get());
	}

	@Test
	public void doNotHandleForeignAction() throws LicensingException {
		assertActionIsNotSupported(//
				new FlotingRequestHandled(//
						requestOfAction(new PassageAction.Of("strange")) //$NON-NLS-1$
				).get());
	}

	private void testActionSupported(PassageAction action) throws LicensingException {
		assertActionIsSupported(new FlotingRequestHandled(requestOfAction(action)).get());
	}

	private void assertActionIsSupported(NetResponse response) {
		assertTrue(response.failed());
		assertNotEquals(//
				new Failure.BadRequestUnknownAction("any").error().code(), // //$NON-NLS-1$
				response.error().code());
	}

	private void assertActionIsNotSupported(NetResponse response) {
		assertTrue(response.failed());
		assertEquals(//
				new Failure.BadRequestUnknownAction("any").error().code(), // //$NON-NLS-1$
				response.error().code());
	}

	private RawRequest requestOfAction(PassageAction action) throws LicensingException {
		return new RequestConstructed().withAction(action).getPure();
	}

}
