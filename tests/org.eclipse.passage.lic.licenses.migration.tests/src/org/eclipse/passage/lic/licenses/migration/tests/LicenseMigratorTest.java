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
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Locale;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.internal.licenses.migration.LicensesResourceHandler;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.junit.Test;

public class LicenseMigratorTest {

	private final String pattern = "yyyy-MM-dd'T'HH:mm:ss'.'SSSZ"; //$NON-NLS-1$

	/**
	 * Creates the licensing date format by pattern
	 * <code>"yyyy-MM-dd'T'HH:mm:ss'.'SSSZ"</code>
	 * 
	 * @return {@link SimpleDateFormat} object as result
	 */
	private DateFormat getLicensingDateFormat() {
		return new SimpleDateFormat(pattern, Locale.ENGLISH);
	}

	@Test
	public void from0_3_3() throws Exception {
		loaded("model/0_3_3.lic_licenses"); //$NON-NLS-1$
	}

	@Test
	public void from0_4_0() throws Exception {
		loaded("model/0_4_0.lic"); //$NON-NLS-1$
	}

	@Test
	public void from0_5_0() throws Exception {
		loaded("model/0_5_0.lic"); //$NON-NLS-1$
	}

	private void loaded(String path) throws IOException, ParseException {
		File legacy = new File(System.getProperty("user.dir") + File.separator + path); //$NON-NLS-1$
		URI uri = URI.createFileURI(legacy.getPath());
		Resource resource = new XMIResourceImpl(uri);
		resource.load(Collections.singletonMap(XMLResource.OPTION_RESOURCE_HANDLER, new LicensesResourceHandler()));
		EList<EObject> contents = resource.getContents();
		EObject eObject = contents.get(0);

		LicensePack pack = LicensePack.class.cast(eObject);
		assertEquals("org.eclipse.passage.lic.evaluation", pack.getIdentifier()); //$NON-NLS-1$
		assertEquals(null, pack.getIssueDate());
		assertEquals("org.eclipse.passage.lic.product", pack.getProductIdentifier()); //$NON-NLS-1$
		assertEquals("0.4.0", pack.getProductVersion()); //$NON-NLS-1$
		assertEquals("", pack.getUserIdentifier()); //$NON-NLS-1$

		EList<LicenseGrant> grants = pack.getLicenseGrants();
		assertEquals(1, grants.size());

		LicenseGrant grant = grants.get(0);
		assertEquals("org.eclipse.passage.lic.evaluation#0", grant.getIdentifier()); //$NON-NLS-1$
		assertEquals(1, grant.getCapacity());
		assertEquals("os.family=*", grant.getConditionExpression()); //$NON-NLS-1$
		assertEquals("hardware", grant.getConditionType()); //$NON-NLS-1$
		assertEquals("org.eclipse.passage.lic.product", grant.getFeatureIdentifier()); //$NON-NLS-1$
		assertEquals("perfect", grant.getMatchRule()); //$NON-NLS-1$
		assertEquals("0.4.0", grant.getMatchVersion()); //$NON-NLS-1$
		assertEquals(getLicensingDateFormat().parse("2019-03-14T00:00:00.000+0300"), //$NON-NLS-1$
				grant.getValidFrom());
		assertEquals(getLicensingDateFormat().parse("2019-06-14T00:00:00.000+0300"), //$NON-NLS-1$
				grant.getValidUntil());
	}
}
