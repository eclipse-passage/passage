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
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lbc.internal.base.FlotingRequestHandled;
import org.eclipse.passage.lbc.internal.base.acquire.NoGrantsAvailable;
import org.eclipse.passage.lbc.internal.base.acquire.NotReleased;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.PassageAction;
import org.eclipse.passage.lic.base.FeatureIdentifier;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.licenses.model.api.GrantAcqisition;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class FloatingCycleActionsDryRunTest {

	private final TestData data = new TestData();

	@Test
	public void mineNothing() throws LicensingException, IOException {
		RawRequest request = request(new PassageAction.Mine());
		NetResponse response = new FlotingRequestHandled(request).get();
		assertFalse(response.failed());
		EList<PersonalFeatureGrant> grants = new DecodedResponse.License(response, request).get().getGrants();
		assertTrue(ofFeature(grants, request).isEmpty());
	}

	private List<PersonalFeatureGrant> ofFeature(EList<PersonalFeatureGrant> grants, RawRequest request) {
		String feature = new FeatureIdentifier((Function<String, String>) request::parameter).get().get();
		Predicate<PersonalFeatureGrant> featured = grant -> feature.equals(grant.getFeature().getIdentifier());
		return grants.stream().filter(featured).collect(Collectors.toList());
	}

	@Test
	public void acquireNothing() throws LicensingException, IOException {
		NetResponse response = new FlotingRequestHandled(request(new PassageAction.Acquire())).get();
		assertTrue(response.failed());
		assertEquals(new NoGrantsAvailable(data.product(), data.feature()).error().code(), response.error().code());
	}

	@Test
	public void releaseInVain() throws LicensingException, IOException {
		GrantAcqisition acqisition = acquisition();
		NetResponse response = new FlotingRequestHandled(//
				request(new PassageAction.Release(), Optional.of(acqisition))).get();
		assertTrue(response.failed());
		assertEquals(new NotReleased(data.product(), acqisition).error().code(), response.error().code());
	}

	private RawRequest request(PassageAction action) throws LicensingException {
		return request(action, Optional.empty());
	}

	private RawRequest request(PassageAction action, Optional<EObject> payload) throws LicensingException {
		return new FeatureRequest(//
				action, //
				data.product(), //
				feature(), //
				data.albert().id(), //
				payload//
		).get();
	}

	private GrantAcqisition acquisition() {
		GrantAcqisition acqisition = LicensesFactory.eINSTANCE.createGrantAcqisition();
		acqisition.setIdentifier("fake-acquisition-id"); //$NON-NLS-1$
		acqisition.setFeature(feature());
		acqisition.setGrant("fake-grant-id"); //$NON-NLS-1$
		acqisition.setCreated(new Date());
		acqisition.setUser(data.albert().id());
		return acqisition;
	}

	private String feature() {
		return data.feature() + ".not"; //$NON-NLS-1$
	}

}
