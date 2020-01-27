/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.licenses.migration.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.junit.Test;

public class LicenseMigratorTest {

	@Test
	public void testMigratorPositive() throws Exception {
		File legacy = new File(
				System.getProperty("user.dir") + File.separator + "model/org.eclipse.passage.lic.lic_licenses"); //$NON-NLS-1$//$NON-NLS-2$
		URI uri = URI.createFileURI(legacy.getPath());
		Resource resource = new XMIResourceImpl(uri);
		resource.load(null);
		EList<EObject> contents = resource.getContents();
		EObject eObject = contents.get(0);

		LicensePack licensePack = LicensePack.class.cast(eObject);
		assertEquals("org.eclipse.passage.lic.evaluation", licensePack.getIdentifier()); //$NON-NLS-1$
		assertEquals(null, licensePack.getIssueDate());
		assertEquals("org.eclipse.passage.lic.product", licensePack.getProductIdentifier()); //$NON-NLS-1$
		assertEquals("0.4.0", licensePack.getProductVersion()); //$NON-NLS-1$
		assertEquals("", licensePack.getUserIdentifier()); //$NON-NLS-1$

		EList<LicenseGrant> grants = licensePack.getLicenseGrants();
		assertEquals(1, grants.size());

		LicenseGrant licencecGrant = grants.get(0);
		assertEquals(1, licencecGrant.getCapacity());
		assertEquals("os.family=*", licencecGrant.getConditionExpression()); //$NON-NLS-1$
		assertEquals("hardware", licencecGrant.getConditionType()); //$NON-NLS-1$
		assertEquals("org.eclipse.passage.lic.product", licencecGrant.getFeatureIdentifier()); //$NON-NLS-1$
		assertEquals("perfect", licencecGrant.getMatchRule()); //$NON-NLS-1$
		assertEquals("0.4.0", licencecGrant.getMatchVersion()); //$NON-NLS-1$
		assertEquals(LicensingProperties.getLicensingDateFormat().parse("2019-03-14T00:00:00.000+0300"), //$NON-NLS-1$
				licencecGrant.getValidFrom());
		assertEquals(LicensingProperties.getLicensingDateFormat().parse("2019-06-14T00:00:00.000+0300"), //$NON-NLS-1$
				licencecGrant.getValidUntil());
	}
}
