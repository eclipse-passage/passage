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
package org.eclipse.passage.lic.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.eclipse.passage.lic.base.access.BaseAccessManager;
import org.eclipse.passage.lic.base.conditions.BaseLicensingCondition;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.access.FeaturePermission;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionVerdict;
import org.junit.After;
import org.junit.Test;

public class BaseAccessManagerTest {

	private static final String PRODUCT_ID = "product.id"; //$NON-NLS-1$
	private static final String PRODUCT_VERSION = "0.1.0"; //$NON-NLS-1$
	private static final String FEATURE_ID = "feature.id"; //$NON-NLS-1$
	private static final String FEATURE_VERSION = "0.1.0"; //$NON-NLS-1$

	private List<String> posted = new ArrayList<>();
	private List<String> logged = new ArrayList<>();

	private LicensingConfiguration conf = LicensingConfigurations.create(PRODUCT_ID, PRODUCT_VERSION);

	private BaseAccessManager manager = new BaseAccessManager() {

		@Override
		protected void postEvent(String topic, Object data) {
			posted.add(topic);
		}

		@Override
		protected void logError(String message, Throwable e) {
			logged.add(message);
		}
	};

	@After
	public void tearDown() {
		posted.clear();
		logged.clear();
	}

	@Test
	public void testExecuteAccessRestrictionsNegative() {
		manager.executeAccessRestrictions(null);
		int errors = 1;
		int events = 5;
		checkMaps(errors, events);
	}

	@Test
	public void testEvaluateConditionsNegative() {
		int logSize = 0;
		int eventSize = 0;
		checkMaps(logSize, eventSize);

		Iterable<FeaturePermission> permissions = Collections.emptyList();
		permissions = manager.evaluateConditions(null, null);
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		permissions = manager.evaluateConditions(new ArrayList<>(), null);
		assertFalse(permissions.iterator().hasNext());
		eventSize++;
		checkMaps(logSize, eventSize);

		permissions = manager.evaluateConditions(Collections.singleton(null), null);
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);
	}

	@Test
	public void testEvaluateConditionDates() {
		int logSize = 0;
		int eventSize = 0;
		Iterable<FeaturePermission> permissions = Collections.emptyList();
		permissions = manager.evaluateConditions(Collections.singleton(createCondition(null, null)), null);
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		permissions = manager.evaluateConditions(Collections.singleton(createCondition(new Date(), null)), null);
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		permissions = manager.evaluateConditions(Collections.singleton(createCondition(null, new Date())), null);
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		Date before = new Date(System.currentTimeMillis() - 100500);
		Date after = new Date(System.currentTimeMillis() + 100500);
		permissions = manager.evaluateConditions(Collections.singleton(createCondition(after, after)), null);
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		permissions = manager.evaluateConditions(Collections.singleton(createCondition(before, before)), null);
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		permissions = manager.evaluateConditions(Collections.singleton(createCondition(after, before)), null);
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		permissions = manager.evaluateConditions(Collections.singleton(createCondition(before, after)), null);
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);
	}

	protected BaseLicensingCondition createCondition(Date from, Date until) {
		return LicensingConditions.create(FEATURE_ID, FEATURE_VERSION, null, from, until, null, null);
	}

	@Test
	public void testExaminePermissionsNegative() {
		int logSize = 0;
		int eventSize = 0;
		Iterable<RestrictionVerdict> verdicts = Collections.emptyList();
		verdicts = manager.examinePermissons(null, null, null);
		assertFalse(verdicts.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		verdicts = manager.examinePermissons(null, null, conf);
		assertFalse(verdicts.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		verdicts = manager.examinePermissons(new ArrayList<>(), null, conf);
		assertFalse(verdicts.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		verdicts = manager.examinePermissons(Collections.singleton(null), null, conf);
		assertFalse(verdicts.iterator().hasNext());
		logSize++;
		checkMaps(++logSize, ++eventSize);
	}

	protected void checkMaps(int logSize, int eventSize) {
		assertEquals(logSize, logged.size());
		assertEquals(eventSize, posted.size());
	}

}
