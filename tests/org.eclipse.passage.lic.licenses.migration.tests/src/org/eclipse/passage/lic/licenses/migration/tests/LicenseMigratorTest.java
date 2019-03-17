/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.licenses.migration.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.junit.Test;

public class LicenseMigratorTest {
	
	@Test
	public void testMigratorPositive() throws Exception {
		File legacy = new File(System.getProperty("user.dir") + File.separator + "model/org.eclipse.passage.lic.lic_licenses");
		URI uri = URI.createFileURI(legacy.getPath());
		Resource resource = new XMIResourceImpl(uri);
		resource.load(null);
		EList<EObject> contents = resource.getContents();
		EObject eObject = contents.get(0);

		LicensePack licensePack = LicensePack.class.cast(eObject);
		assertEquals("org.eclipse.passage.lic.evaluation", licensePack.getIdentifier());
		assertEquals(null, licensePack.getIssueDate());
		assertEquals("org.eclipse.passage.lic.product", licensePack.getProductIdentifier());
		assertEquals("0.4.0", licensePack.getProductVersion());
		assertEquals("", licensePack.getUserIdentifier());

		EList<LicenseGrant> grants = licensePack.getLicenseGrants();
		assertEquals(1, grants.size());

		LicenseGrant g0 = grants.get(0);
		assertEquals(1, g0.getCapacity());
		assertEquals("os.family=*", g0.getConditionExpression());
		assertEquals("hardware", g0.getConditionType());
		assertEquals("org.eclipse.passage.lic.product", g0.getFeatureIdentifier());
		assertEquals("perfect", g0.getMatchRule());
		assertEquals("0.4.0", g0.getMatchVersion());
		final DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSSZ", Locale.ENGLISH);
		assertEquals(dateFormat.parse("2019-03-14T00:00:00.000+0300"), g0.getValidFrom());
		assertEquals(dateFormat.parse("2019-06-14T00:00:00.000+0300"), g0.getValidUntil());
	}
}
