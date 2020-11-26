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
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.passage.lbc.internal.api.FloatingResponse;
import org.eclipse.passage.lbc.internal.base.ProductUserRequest;
import org.eclipse.passage.lbc.internal.base.mine.Conditions;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;
import org.eclipse.passage.lic.internal.net.LicenseUser;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.junit.Test;

public final class ConditionsTest {

	@Test
	public void mineAllForRightUser() throws LicensingException, IOException {
		LicenseGrant grant = mineForUser("Albert_Rose@garden.ga", 1).get(0); //$NON-NLS-1$
		assertEquals("hardware", grant.getConditionType()); //$NON-NLS-1$
		assertEquals("os.hwdisk=*777*", grant.getConditionExpression()); //$NON-NLS-1$
		assertEquals("prince-to-frog", grant.getFeatureIdentifier()); //$NON-NLS-1$
		assertEquals("d2b83215-b65d-4031-a8c8-a10421d56260#0", grant.getIdentifier()); //$NON-NLS-1$
		assertEquals("0.1.0", grant.getMatchVersion()); //$NON-NLS-1$
		assertEquals("compatible", grant.getMatchRule()); //$NON-NLS-1$
		// FIXME: test valid period:
		// [featureGrant.from, featureGrant.from + featureGrant.vivid]
	}

	@Test
	public void mineNothingForWrongUser() throws LicensingException, IOException {
		mineForUser("imposter", 0); //$NON-NLS-1$
	}

	private List<LicenseGrant> mineForUser(String user, int conditions) throws IOException, LicensingException {
		FloatingResponse response = new Conditions(request(user), this::licFolder).get();
		assertFalse(response.failed());
		EList<LicenseGrant> grants = new License(response).get().getLicenseGrants();
		assertEquals(conditions, grants.size());
		return grants;
	}

	private ProductUserRequest request(String user) throws LicensingException {
		return new ProductUserRequest(//
				new RequestConstructed()//
						.withParameters(Arrays.asList(//
								new ProductIdentifier("anti-human-magic.product"), //$NON-NLS-1$
								new ProductVersion("0.2.1"), //$NON-NLS-1$
								new LicenseUser(user)) // $NON-NLS-1$
						).get()//
		);
	}

	// FIXME: most probably won't work from jar
	private Path licFolder() {
		return Paths.get("resource").resolve("lics"); //$NON-NLS-1$ //$NON-NLS-2$ \
	}

}
