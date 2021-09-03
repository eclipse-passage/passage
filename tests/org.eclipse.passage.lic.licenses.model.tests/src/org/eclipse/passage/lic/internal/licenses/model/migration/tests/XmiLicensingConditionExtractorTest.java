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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.internal.licenses.model.EmptyPersonalFeatureGrant;
import org.eclipse.passage.lic.internal.licenses.model.EmptyPersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalFeatureGrant;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.licenses.model.transport.XmiConditionTransport;
import org.eclipse.passage.lic.licenses.model.util.LicensesResourceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class XmiLicensingConditionExtractorTest {

	private static final String COND1_FEATURE_ID = "loc.workbench"; //$NON-NLS-1$
	private static final String COND1_FEATURE_VERSION = "1.0.0"; //$NON-NLS-1$
	private static final String COND1_CONDITION_TYPE = "hardware"; //$NON-NLS-1$
	private static final String COND1_CONDITION_EXPRESSION = "mac=*"; //$NON-NLS-1$

	private static final String COND2_FEATURE_ID = "loc.products.matrix"; //$NON-NLS-1$
	private static final String COND2_FEATURE_VERSION = "2.0.0"; //$NON-NLS-1$
	private static final String COND2_CONDITION_TYPE = "hardware"; //$NON-NLS-1$
	private static final String COND2_CONDITION_EXPRESSION = "hdd=*"; //$NON-NLS-1$

	/**
	 * Passed through maven-surefire-plugin configuration
	 */
	private static final String MVN_PROJECT_OUTPUT_PROPERTY = "project.build.directory"; //$NON-NLS-1$

	private static final String MVN_PROJECT_OUTPUT_VALUE = "target"; //$NON-NLS-1$

	@Rule
	public TemporaryFolder baseFolder = new TemporaryFolder(new File(resolveOutputDirName()));

	public static String resolveOutputDirName() {
		String userDir = System.getProperty("user.dir"); //$NON-NLS-1$
		String defaultValue = userDir + File.separator + MVN_PROJECT_OUTPUT_VALUE;
		String outDir = System.getProperty(MVN_PROJECT_OUTPUT_PROPERTY, defaultValue);
		return outDir;
	}

	@Test
	public void testExtractorPositive() throws Exception {
		XmiConditionTransport extractor = new XmiConditionTransport();

		PersonalLicensePack license = new EmptyPersonalLicensePack().get();
		EList<PersonalFeatureGrant> licenseGrants = license.getGrants();
		PersonalFeatureGrant cond1 = new EmptyPersonalFeatureGrant().get();
		cond1.getFeature().setIdentifier(COND1_FEATURE_ID);
		cond1.getFeature().getVersionMatch().setVersion(COND1_FEATURE_VERSION);
		cond1.getUserAuthentication().setType(COND1_CONDITION_TYPE);
		cond1.getUserAuthentication().setExpression(COND1_CONDITION_EXPRESSION);
		((ValidityPeriodClosed) cond1.getValid()).setFrom(new Date());
		((ValidityPeriodClosed) cond1.getValid()).setUntil(new Date(System.currentTimeMillis() + 1));
		licenseGrants.add(cond1);
		PersonalFeatureGrant cond2 = new EmptyPersonalFeatureGrant().get();
		cond2.getFeature().setIdentifier(COND2_FEATURE_ID);
		cond2.getFeature().getVersionMatch().setVersion(COND2_FEATURE_VERSION);
		cond2.getUserAuthentication().setType(COND2_CONDITION_TYPE);
		cond2.getUserAuthentication().setExpression(COND2_CONDITION_EXPRESSION);
		((ValidityPeriodClosed) cond2.getValid()).setFrom(new Date());
		((ValidityPeriodClosed) cond2.getValid()).setUntil(new Date(System.currentTimeMillis() + 1));
		licenseGrants.add(cond2);

		File file = baseFolder.newFile("some.lic"); //$NON-NLS-1$
		try (FileOutputStream fos = new FileOutputStream(file)) {
			// FIXME:AF: should be done via factory
			Resource saved = new LicensesResourceImpl();
			saved.getContents().add(license);
			saved.save(fos, new HashMap<>());
		}

		List<Condition> actual = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(file)) {
			extractor.read(fis).conditions().forEach(actual::add);
		}
		assertEquals(2, actual.size());
		Condition actual1 = actual.get(0);
		assertEquals(COND1_FEATURE_ID, actual1.feature());
		assertEquals(COND1_FEATURE_VERSION, actual1.versionMatch().version());
		assertEquals(COND1_CONDITION_TYPE, actual1.evaluationInstructions().type().identifier());
		assertEquals(COND1_CONDITION_EXPRESSION, actual1.evaluationInstructions().expression());
		Condition actual2 = actual.get(1);
		assertEquals(COND2_FEATURE_ID, actual2.feature());
		assertEquals(COND2_FEATURE_VERSION, actual2.versionMatch().version());
		assertEquals(COND2_CONDITION_TYPE, actual2.evaluationInstructions().type().identifier());
		assertEquals(COND2_CONDITION_EXPRESSION, actual2.evaluationInstructions().expression());
	}

}
