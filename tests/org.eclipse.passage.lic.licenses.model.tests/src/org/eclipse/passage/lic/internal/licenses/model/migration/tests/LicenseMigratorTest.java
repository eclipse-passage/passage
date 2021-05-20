/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.licenses.model.migration.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.passage.lic.licenses.ValidityPeriodClosedDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.util.LicensesResourceImpl;
import org.junit.Test;

public final class LicenseMigratorTest {

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

	@Test
	public void from1_0_0() throws Exception {
		loadedTwoGrants("model/1_0_0.lic"); //$NON-NLS-1$
	}

	private void loaded(String path) throws IOException, ParseException {
		PersonalLicensePack pack = pack(path);
		assertEquals("org.eclipse.passage.lic.evaluation", pack.getLicense().getIdentifier()); //$NON-NLS-1$
		assertEquals(null, pack.getLicense().getIssueDate());
		assertEquals("org.eclipse.passage.lic.product", pack.getLicense().getProduct().getIdentifier()); //$NON-NLS-1$
		assertEquals("0.4.0", pack.getLicense().getProduct().getVersion()); //$NON-NLS-1$
		assertEquals("", pack.getLicense().getUser().getIdentifier()); //$NON-NLS-1$

		EList<LicenseGrant> grants = pack.getGrants();
		assertEquals(1, grants.size());

		LicenseGrant grant = grants.get(0);
		assertEquals("org.eclipse.passage.lic.evaluation#0", grant.getIdentifier()); //$NON-NLS-1$
		assertEquals(1, grant.getCapacity());
		assertEquals("os.family=*", grant.getUserAuthentication().getExpression()); //$NON-NLS-1$
		assertEquals("hardware", grant.getUserAuthentication().getType()); //$NON-NLS-1$
		assertEquals("org.eclipse.passage.lic.product", grant.getFeature().getIdentifier()); //$NON-NLS-1$
		assertEquals("perfect", grant.getFeature().getMatchingRule()); //$NON-NLS-1$
		assertEquals("0.4.0", grant.getFeature().getVersion()); //$NON-NLS-1$
		assertEquals(getLicensingDateFormat().parse("2019-03-14T00:00:00.000+0300"), //$NON-NLS-1$
				((ValidityPeriodClosedDescriptor) grant.getValid()).getFrom());
		assertEquals(getLicensingDateFormat().parse("2019-06-14T00:00:00.000+0300"), //$NON-NLS-1$
				((ValidityPeriodClosedDescriptor) grant.getValid()).getUntil());
	}

	private PersonalLicensePack pack(String path) throws IOException {
		File legacy = new File(System.getProperty("user.dir") + File.separator + path); //$NON-NLS-1$
		URI uri = URI.createFileURI(legacy.getPath());
		// FIXME:AF: should be done via factory
		Resource resource = new LicensesResourceImpl(uri);
		resource.load(new HashMap<>());
		EList<EObject> contents = resource.getContents();
		EObject eObject = contents.get(0);

		PersonalLicensePack pack = PersonalLicensePack.class.cast(eObject);
		return pack;
	}

	private void loadedTwoGrants(String path) throws IOException, ParseException {
		PersonalLicensePack pack = pack(path);
		String identifier = pack.getLicense().getIdentifier();
		assertEquals("3251ddf1-bd2c-48e4-993a-26fbf7eb3a42", identifier); //$NON-NLS-1$
		assertEquals(getLicensingDateFormat().parse("2020-12-02T16:30:50.176+0300"), //$NON-NLS-1$
				pack.getLicense().getIssueDate());
		assertEquals(issueDate().getTime(), pack.getLicense().getIssueDate().getTime()); // $NON-NLS-1$
		assertEquals("anti-human-magic.product", pack.getLicense().getProduct().getIdentifier()); //$NON-NLS-1$
		assertEquals("0.2.1", pack.getLicense().getProduct().getVersion()); //$NON-NLS-1$
		assertEquals("elder@magic.com", pack.getLicense().getUser().getIdentifier()); //$NON-NLS-1$
		assertEquals("The Elder", pack.getLicense().getUser().getName()); //$NON-NLS-1$
		assertEquals("magic-field-license-plan", pack.getLicense().getPlan()); //$NON-NLS-1$

		EList<LicenseGrant> grants = pack.getGrants();
		assertEquals(2, grants.size());

		assertGrant(grant(identifier, grants, 0), "prince-to-frog", "0.1.0"); //$NON-NLS-1$//$NON-NLS-2$
		assertGrant(grant(identifier, grants, 1), "anti-human-magic.product", "0.2.1"); //$NON-NLS-1$//$NON-NLS-2$
	}

	private LicenseGrant grant(String pack, EList<LicenseGrant> grants, int no) {
		String id = String.format("%s#%d", pack, no); //$NON-NLS-1$
		for (LicenseGrant grant : grants) {
			if (id.equals(grant.getIdentifier())) {
				return grant;
			}
		}
		fail(String.format("There is no grant with id %s", id)); //$NON-NLS-1$
		return null;// unreachable
	}

	private void assertGrant(LicenseGrant grant, String feature, String version) throws ParseException {
		assertEquals(feature, grant.getFeature().getIdentifier());
		assertEquals(version, grant.getFeature().getVersion());
		assertEquals(null, grant.getFeature().getMatchingRule());
		assertEquals("hardware", grant.getUserAuthentication().getType()); //$NON-NLS-1$
		assertEquals("os.family=*", grant.getUserAuthentication().getExpression()); //$NON-NLS-1$
		assertEquals(getLicensingDateFormat().parse("2020-12-02T00:00:00.000+0300"), //$NON-NLS-1$
				((ValidityPeriodClosedDescriptor) grant.getValid()).getFrom());
		assertEquals(getLicensingDateFormat().parse("2021-12-02T00:00:00.000+0300"), //$NON-NLS-1$
				((ValidityPeriodClosedDescriptor) grant.getValid()).getUntil());
	}

	private final Date issueDate() {
		return Date.from(//
				ZonedDateTime
						.of(2020, Month.DECEMBER.getValue(), 2, 16, 30, 50, 176000000,
								ZoneId.ofOffset("", ZoneOffset.ofHours(3))) //$NON-NLS-1$
						.toInstant());
	}

}
