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
package org.eclipse.passage.lic.base.tests.access;

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_FEATURE_PROVIDER_DEFAULT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.base.access.BasePermissionExaminer;
import org.eclipse.passage.lic.base.access.FeaturePermissions;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.base.requirements.LicensingRequirements;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.access.FeaturePermission;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.requirements.LicensingRequirement;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionVerdict;
import org.junit.Test;

public class BasePermissionExaminerTest {

	private static final String FOO_FEATURE_ID = "foo"; //$NON-NLS-1$
	private static final String FOO_FEATURE_VERSION = "0.4.1"; //$NON-NLS-1$
	private static final String BAR_FEATURE_ID = "bar"; //$NON-NLS-1$
	private static final String BAR_FEATURE_VERSION = "1.2.9"; //$NON-NLS-1$
	private static final String BAZ_FEATURE_ID = "baz"; //$NON-NLS-1$

	@Test
	public void testExamine() {
		BasePermissionExaminer examiner = new BasePermissionExaminer() {

			@Override
			protected void postEvent(String topic, Object data) {
				// do nothing
			}

		};
		Object source = new Object();
		LicensingConfiguration configuration = LicensingConfigurations.create(null, null);

		String provider = LICENSING_FEATURE_PROVIDER_DEFAULT;

		LicensingRequirement fooRequirement = LicensingRequirements.createDefault(FOO_FEATURE_ID, FOO_FEATURE_VERSION,
				FOO_FEATURE_ID, provider, source);
		LicensingRequirement barRequirement = LicensingRequirements.createDefault(BAR_FEATURE_ID, BAR_FEATURE_VERSION,
				BAR_FEATURE_ID, provider, source);
		Iterable<LicensingRequirement> requirements = Arrays.asList(fooRequirement, barRequirement);

		Date fooFrom = null;
		Date foorUntil = null;
		Date bazFrom = null;
		Date bazUntil = null;
		LicensingCondition fooCondition = LicensingConditions.create(FOO_FEATURE_ID, "0.4.0", //$NON-NLS-1$
				LicensingVersions.RULE_GREATER_OR_EQUAL, fooFrom, foorUntil, null, null);
		LicensingCondition bazCondition = LicensingConditions.create(BAZ_FEATURE_ID, "1.0.0", //$NON-NLS-1$
				LicensingVersions.RULE_GREATER_OR_EQUAL, bazFrom, bazUntil, null, null);
		FeaturePermission fooPermission = FeaturePermissions.createDefault(fooCondition, configuration);
		FeaturePermission bazPermission = FeaturePermissions.create(bazCondition, configuration, new Date(0),
				new Date(0));
		Iterable<FeaturePermission> permissions = Arrays.asList(fooPermission, bazPermission);

		Iterable<RestrictionVerdict> verdicts = examiner.examine(requirements, permissions);
		Iterator<RestrictionVerdict> iterator = verdicts.iterator();
		RestrictionVerdict next = iterator.next();
		assertNotNull(next);
		assertEquals(barRequirement, next.getLicensingRequirement());
		assertEquals(LicensingProperties.LICENSING_RESTRICTION_LEVEL_DEFAULT, next.getRestrictionLevel());
		assertFalse(iterator.hasNext());
	}

}
