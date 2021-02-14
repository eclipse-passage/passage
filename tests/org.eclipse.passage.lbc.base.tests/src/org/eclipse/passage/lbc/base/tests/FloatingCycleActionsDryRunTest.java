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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lbc.internal.base.FlotingRequestHandled;
import org.eclipse.passage.lbc.internal.base.acquire.NoGrantsAvailable;
import org.eclipse.passage.lbc.internal.base.acquire.NotReleased;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.floating.model.api.GrantAcqisition;
import org.eclipse.passage.lic.floating.model.meta.FloatingFactory;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.PassageAction;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.junit.Test;

public final class FloatingCycleActionsDryRunTest {

	private final LicensedProduct product = new BaseLicensedProduct("proj", "1.2.3"); //$NON-NLS-1$ //$NON-NLS-2$
	private final String feature = "erutaef"; //$NON-NLS-1$
	private final String user = "resu"; //$NON-NLS-1$

	@Test
	public void mineNothing() throws LicensingException, IOException {
		NetResponse response = new FlotingRequestHandled(request(new PassageAction.Mine())).get();
		assertFalse(response.failed());
		assertTrue(new License(response).get().getLicenseGrants().isEmpty());
	}

	@Test
	public void acquireNothing() throws LicensingException, IOException {
		NetResponse response = new FlotingRequestHandled(request(new PassageAction.Acquire())).get();
		assertTrue(response.failed());
		assertEquals(new NoGrantsAvailable(product, feature).error().code(), response.error().code());
	}

	@Test
	public void releaseInVain() throws LicensingException, IOException {
		GrantAcqisition acqisition = acquisition();
		NetResponse response = new FlotingRequestHandled(//
				request(new PassageAction.Release(), Optional.of(acqisition))).get();
		assertTrue(response.failed());
		assertEquals(new NotReleased(product, acqisition).error().code(), response.error().code());
	}

	private RawRequest request(PassageAction action) throws LicensingException {
		return request(action, Optional.empty());
	}

	private RawRequest request(PassageAction action, Optional<EObject> payload) throws LicensingException {
		return new FeatureRequest(action, product, feature, user, payload).get();
	}

	private GrantAcqisition acquisition() {
		GrantAcqisition acqisition = FloatingFactory.eINSTANCE.createGrantAcqisition();
		acqisition.setIdentifier("fake-acquisition-id"); //$NON-NLS-1$
		acqisition.setFeature(feature);
		acqisition.setGrant("fake-grant-id"); //$NON-NLS-1$
		acqisition.setCreated(new Date());
		acqisition.setUser(user);
		return acqisition;
	}

}
