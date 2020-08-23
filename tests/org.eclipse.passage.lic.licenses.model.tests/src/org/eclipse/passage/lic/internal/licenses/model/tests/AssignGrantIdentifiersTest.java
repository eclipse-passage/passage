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
package org.eclipse.passage.lic.internal.licenses.model.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.common.util.EList;
import org.eclipse.passage.lic.internal.licenses.model.AssignGrantIdentifiers;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.junit.Test;

public class AssignGrantIdentifiersTest {

	@Test
	public void positive() {
		LicensesFactory factory = LicensesFactory.eINSTANCE;
		LicensePack pack = factory.createLicensePack();
		pack.setIdentifier("pack-id"); //$NON-NLS-1$
		EList<LicenseGrant> grants = pack.getLicenseGrants();
		LicenseGrant nullId = factory.createLicenseGrant();
		nullId.setIdentifier(null);
		grants.add(nullId);
		LicenseGrant emptyId = factory.createLicenseGrant();
		emptyId.setIdentifier(""); //$NON-NLS-1$
		grants.add(emptyId);
		LicenseGrant hasId = factory.createLicenseGrant();
		hasId.setIdentifier("grant-id"); //$NON-NLS-1$
		grants.add(hasId);
		new AssignGrantIdentifiers().accept(pack);
		assertEquals("pack-id#0", grants.get(0).getIdentifier()); //$NON-NLS-1$
		assertEquals("pack-id#1", grants.get(1).getIdentifier()); //$NON-NLS-1$
		assertEquals("grant-id", grants.get(2).getIdentifier()); //$NON-NLS-1$
	}

}
