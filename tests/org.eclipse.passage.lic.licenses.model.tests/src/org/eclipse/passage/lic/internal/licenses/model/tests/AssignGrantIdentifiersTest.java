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
package org.eclipse.passage.lic.internal.licenses.model.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.util.EList;
import org.eclipse.passage.lic.internal.licenses.model.AssignGrantIdentifiers;
import org.eclipse.passage.lic.internal.licenses.model.EmptyPersonalFeatureGrant;
import org.eclipse.passage.lic.internal.licenses.model.EmptyPersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites;
import org.junit.Test;

public class AssignGrantIdentifiersTest {

	private final String id = "pack-id"; //$NON-NLS-1$

	@Test
	public void positive() {
		// given
		PersonalLicensePack pack = pack();
		addGrantOfIdentifier(pack, null);
		addGrantOfIdentifier(pack, ""); //$NON-NLS-1$
		addGrantOfIdentifier(pack, "grant-id"); //$NON-NLS-1$
		// when
		new AssignGrantIdentifiers().accept(pack);
		// then
		expect("pack-id#0", pack, 0); //$NON-NLS-1$
		expect("pack-id#1", pack, 1); //$NON-NLS-1$
		expect("grant-id", pack, 2); //$NON-NLS-1$
	}

	private void expect(String expected, PersonalLicensePack pack, int no) {
		assertEquals(expected, pack.getGrants().get(no).getIdentifier());
	}

	private PersonalLicensePack pack() {
		PersonalLicensePack pack = new EmptyPersonalLicensePack().get();
		PersonalLicenseRequisites license = pack.getLicense();
		license.setIdentifier(id);
		return pack;
	}

	private void addGrantOfIdentifier(PersonalLicensePack pack, String identifier) {
		EList<PersonalFeatureGrant> grants = pack.getGrants();
		PersonalFeatureGrant grant = new EmptyPersonalFeatureGrant().get();
		grant.setIdentifier(identifier);
		grants.add(grant);
	}

}
