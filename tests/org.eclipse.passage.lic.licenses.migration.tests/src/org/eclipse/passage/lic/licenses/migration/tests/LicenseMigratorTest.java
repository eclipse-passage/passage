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
import java.util.HashMap;
import java.util.Locale;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.util.LicensesResourceImpl;
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
		LicensePack pack = loaded("model/0_3_3.lic_licenses"); //$NON-NLS-1$
		checkPack0xx(pack);
		checkGrants(pack);
	}

	@Test
	public void from0_4_0() throws Exception {
		LicensePack pack = loaded("model/0_4_0.lic"); //$NON-NLS-1$
		checkPack0xx(pack);
		checkGrants(pack);
	}

	@Test
	public void from0_5_0() throws Exception {
		LicensePack pack = loaded("model/0_5_0.lic"); //$NON-NLS-1$
		checkPack0xx(pack);
		checkGrants(pack);
	}

	@Test
	public void from1_0_0() throws Exception {
		LicensePack pack = loaded("model/1_0_0.lic"); //$NON-NLS-1$
		checkPack100(pack);
		checkGrants(pack);
	}

	private LicensePack loaded(String path) throws IOException, ParseException {
		File legacy = new File(System.getProperty("user.dir") + File.separator + path); //$NON-NLS-1$
		URI uri = URI.createFileURI(legacy.getPath());
		Resource resource = new LicensesResourceImpl(uri);
		resource.load(new HashMap<>());
		EList<EObject> contents = resource.getContents();
		EObject eObject = contents.get(0);
		return LicensePack.class.cast(eObject);
	}

	private void checkPack0xx(LicensePack pack) throws ParseException {
		assertEquals("org.eclipse.passage.lic.evaluation", pack.getIdentifier()); //$NON-NLS-1$
		assertEquals(null, pack.getIssueDate());
		assertEquals("org.eclipse.passage.lic.product", pack.getProduct().getProduct()); //$NON-NLS-1$
		assertEquals("0.4.0", pack.getProduct().getVersion()); //$NON-NLS-1$
		assertEquals("", pack.getUserIdentifier()); //$NON-NLS-1$
	}

	private void checkPack100(LicensePack pack) throws ParseException {
		assertEquals("org.eclipse.passage.lic.evaluation", pack.getIdentifier()); //$NON-NLS-1$
		assertEquals(getLicensingDateFormat().parse("2020-12-02T16:30:50.176+0300"), //$NON-NLS-1$
				pack.getIssueDate());
		assertEquals("org.eclipse.passage.lic.product", pack.getProduct().getProduct()); //$NON-NLS-1$
		assertEquals("0.4.0", pack.getProduct().getVersion()); //$NON-NLS-1$
		assertEquals("elder@magic.com", pack.getUserIdentifier()); //$NON-NLS-1$
	}

	private void checkGrants(LicensePack pack) throws ParseException {
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
