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
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.passage.lbc.internal.base.FlotingRequestHandled;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.PassageAction;
import org.eclipse.passage.lic.base.InvalidLicensedProduct;
import org.eclipse.passage.lic.base.ProductIdentifier;
import org.eclipse.passage.lic.base.ProductVersion;
import org.eclipse.passage.lic.internal.net.LicenseUser;
import org.eclipse.passage.lic.internal.net.LicensingAction;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.handle.Failure;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class ConditionsTest {

	private final TestData data = new TestData();

	@Test
	public void mineAllForRightUser() throws LicensingException, IOException {
		PersonalFeatureGrant grant = mineForUserAndProduct(data.albert().id(), data.product().identifier(), 2).get(1);
		data.assertGrantIsValid(grant);
	}

	@Test
	public void mineNothingForWrongUser() throws LicensingException, IOException {
		mineForUserAndProduct("imposter", data.product().identifier(), 0); //$NON-NLS-1$
	}

	@Test
	public void getErrorForWrongProduct() throws LicensingException, IOException {
		NetResponse response = new FlotingRequestHandled(//
				request(data.albert().id(), "foreign-product")).get(); //$NON-NLS-1$
		assertTrue(response.failed());
		assertEquals(//
				new Failure.BadRequestUnkonwnProduct(new InvalidLicensedProduct()).error().code(), //
				response.error().code());
	}

	private List<PersonalFeatureGrant> mineForUserAndProduct(String user, String product, int conditions)
			throws IOException, LicensingException {
		RawRequest request = request(user, product);
		NetResponse response = new FlotingRequestHandled(request).get();
		assertFalse(response.failed());
		EList<PersonalFeatureGrant> grants = new DecodedResponse.License(response, request).get().getGrants();
		assertEquals(conditions, grants.size());
		return grants;
	}

	private RawRequest request(String user, String product) throws LicensingException {
		return new RequestConstructed()//
				.withParameters(Arrays.asList(//
						new LicensingAction(new PassageAction.Mine()), //
						new ProductIdentifier(product), //
						new ProductVersion("0.2.1"), //$NON-NLS-1$
						new LicenseUser(user)))//
				.getValid();//
	}

}
