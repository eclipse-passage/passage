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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.base.SystemReporter;
import org.eclipse.passage.lic.base.access.BaseAccessManager;
import org.eclipse.passage.lic.base.conditions.BaseConditionMinerRegistry;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.base.requirements.LicensingRequirements;
import org.eclipse.passage.lic.base.tests.LicensningBaseTests;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingReporter;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.eclipse.passage.lic.runtime.access.FeaturePermission;
import org.eclipse.passage.lic.runtime.access.PermissionEmitter;
import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.requirements.LicensingRequirement;
import org.eclipse.passage.lic.runtime.requirements.RequirementResolver;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionVerdict;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BaseAccessManagerTest {

	private static final String PRODUCT_ID = "product.id"; //$NON-NLS-1$
	private static final String PRODUCT_VERSION = "0.1.0"; //$NON-NLS-1$
	private static final String FEATURE_ID = "feature.id"; //$NON-NLS-1$
	private static final String FEATURE_VERSION = "0.1.0"; //$NON-NLS-1$
	private static final String CONDITION_TYPE = "condition.type"; //$NON-NLS-1$
	private static final String CONDITION_EXPRESSION = "condition.type=*"; //$NON-NLS-1$

	private List<LicensingResult> sent = new ArrayList<>();
	private List<LicensingResult> posted = new ArrayList<>();
	private List<LicensingResult> logged = new ArrayList<>();

	private LicensingConfiguration conf = LicensingConfigurations.create(PRODUCT_ID, PRODUCT_VERSION);

	private LicensingReporter reporter = new LicensingReporter() {

		@Override
		public void sendResult(LicensingResult result) {
			sent.add(result);
		}

		@Override
		public void postResult(LicensingResult result) {
			posted.add(result);
		}

		@Override
		public void logResult(LicensingResult result) {
			logged.add(result);
		}
	};

	private BaseAccessManager manager = new BaseAccessManager() {
	};

	@Before
	public void setUp() {
		manager.bindLicensingReporter(reporter);
	}

	@After
	public void tearDown() {
		sent.clear();
		posted.clear();
		logged.clear();
		manager.unbindLicensingReporter(reporter);
	}

	@Test
	public void testLicensingReporter() {
		assertEquals(reporter, manager.getLicensingReporter());
		manager.unbindLicensingReporter(SystemReporter.INSTANCE);
		assertEquals(reporter, manager.getLicensingReporter());
		manager.bindLicensingReporter(SystemReporter.INSTANCE);
		assertEquals(SystemReporter.INSTANCE, manager.getLicensingReporter());
		manager.unbindLicensingReporter(SystemReporter.INSTANCE);
		assertEquals(SystemReporter.INSTANCE, manager.getLicensingReporter());
	}

	@Test
	public void testResolveRequirementsNullConfiguration() {
		List<LicensingRequirement> resolved = new ArrayList<>();
		manager.resolveRequirements(null).forEach(resolved::add);
		assertEquals(1, resolved.size());
		LicensingRequirement req0 = resolved.get(0);
		assertEquals(LicensingConfigurations.INVALID.getProductIdentifier(), req0.getFeatureIdentifier());
		assertEquals(LicensingProperties.LICENSING_FEATURE_NAME_DEFAULT, req0.getFeatureName());
		assertEquals(LicensingProperties.LICENSING_FEATURE_PROVIDER_DEFAULT, req0.getFeatureProvider());
		assertEquals(LicensingConfigurations.INVALID.getProductVersion(), req0.getFeatureVersion());
		assertEquals(manager.getClass().getName(), req0.getRequirementSource());
		assertEquals(LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR, req0.getRestrictionLevel());

		int errors = 0;
		int events = 1;
		checkMaps(errors, events);

	}

	@Test
	public void testResolveRequirementsInvalidConfiguration() {
		List<LicensingRequirement> resolved = new ArrayList<>();
		manager.resolveRequirements(LicensingConfigurations.INVALID).forEach(resolved::add);
		assertEquals(1, resolved.size());
		LicensingRequirement req0 = resolved.get(0);
		assertEquals(LicensingConfigurations.INVALID.getProductIdentifier(), req0.getFeatureIdentifier());
		assertEquals(LicensingProperties.LICENSING_FEATURE_NAME_DEFAULT, req0.getFeatureName());
		assertEquals(LicensingProperties.LICENSING_FEATURE_PROVIDER_DEFAULT, req0.getFeatureProvider());
		assertEquals(LicensingConfigurations.INVALID.getProductVersion(), req0.getFeatureVersion());
		assertEquals(manager.getClass().getName(), req0.getRequirementSource());
		assertEquals(LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR, req0.getRestrictionLevel());

		int errors = 0;
		int events = 1;
		checkMaps(errors, events);

	}

	@Test
	public void testResolveRequirementsPositive() {
		String source = getClass().getName();
		String featureId = "some.feature.id"; //$NON-NLS-1$
		LicensingRequirement error = LicensingRequirements.createConfigurationError(featureId, source);
		RequirementResolver resolver = LicensningBaseTests.createRequirementResolver(Collections.singleton(error));
		manager.bindRequirementResolver(resolver);
		String productId = "some.product.id"; //$NON-NLS-1$
		String productVersion = "X3"; //$NON-NLS-1$

		List<LicensingRequirement> resolved = new ArrayList<>();
		manager.resolveRequirements(LicensingConfigurations.create(productId, productVersion)).forEach(resolved::add);

		assertEquals(1, resolved.size());
		LicensingRequirement req0 = resolved.get(0);
		assertEquals(featureId, req0.getFeatureIdentifier());
		assertEquals(LicensingProperties.LICENSING_FEATURE_NAME_DEFAULT, req0.getFeatureName());
		assertEquals(LicensingProperties.LICENSING_FEATURE_PROVIDER_DEFAULT, req0.getFeatureProvider());
		assertEquals(LicensingVersions.VERSION_DEFAULT, req0.getFeatureVersion());
		assertEquals(getClass().getName(), req0.getRequirementSource());
		assertEquals(LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR, req0.getRestrictionLevel());

		int errors = 0;
		int events = 1;
		checkMaps(errors, events);
	}

	@Test
	public void testResolveRequirementsUnbind() {
		String source = getClass().getName();
		String featureId = "some.feature.id"; //$NON-NLS-1$
		LicensingRequirement error = LicensingRequirements.createConfigurationError(featureId, source);
		RequirementResolver resolver = LicensningBaseTests.createRequirementResolver(Collections.singleton(error));
		manager.bindRequirementResolver(resolver);
		String productId = "some.product.id"; //$NON-NLS-1$
		String productVersion = "X3"; //$NON-NLS-1$

		List<LicensingRequirement> resolved = new ArrayList<>();
		manager.unbindRequirementResolver(resolver);
		manager.resolveRequirements(LicensingConfigurations.create(productId, productVersion)).forEach(resolved::add);

		assertEquals(1, resolved.size());
		LicensingRequirement req0 = resolved.get(0);
		assertEquals(productId, req0.getFeatureIdentifier());
		assertEquals(LicensingProperties.LICENSING_FEATURE_NAME_DEFAULT, req0.getFeatureName());
		assertEquals(LicensingProperties.LICENSING_FEATURE_PROVIDER_DEFAULT, req0.getFeatureProvider());
		assertEquals(LicensingVersions.VERSION_DEFAULT, req0.getFeatureVersion());
		assertEquals(manager.getClass().getName(), req0.getRequirementSource());
		assertEquals(LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR, req0.getRestrictionLevel());

		int errors = 0;
		int events = 1;
		checkMaps(errors, events);
	}

	@Test
	public void testExtractConditionsNullConfiguration() {
		BaseConditionMinerRegistry registry = new BaseConditionMinerRegistry();
		registry.registerConditionMiner(LicensningBaseTests.createConditionMiner(null), null);
		LicensingCondition condition = LicensingConditions.create("identifier", //$NON-NLS-1$
				"version", //$NON-NLS-1$
				"rule", //$NON-NLS-1$
				new Date(), new Date(), "type", //$NON-NLS-1$
				"expression"); //$NON-NLS-1$
		Iterable<LicensingCondition> create = Collections.singleton(condition);
		ConditionMiner miner = LicensningBaseTests.createConditionMiner(create);
		registry.registerConditionMiner(miner, null);
		manager.unbindConditionMinerRegistry(registry);
		manager.bindConditionMinerRegistry(registry);
		List<LicensingCondition> resolved = new ArrayList<>();
		manager.extractConditions(null).forEach(resolved::add);
		assertEquals(1, resolved.size());
		int errors = 0;
		int events = 1;
		checkMaps(errors, events);
		registry.unregisterConditionMiner(miner, null);
		manager.unbindConditionMinerRegistry(registry);
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

		permissions = manager.evaluateConditions(null, new ArrayList<>());
		assertFalse(permissions.iterator().hasNext());
		eventSize++;
		checkMaps(logSize, eventSize);

		permissions = manager.evaluateConditions(null, Collections.singleton(null));
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);
	}

	@Test
	public void testEvaluateConditionDates() {
		int logSize = 0;
		int eventSize = 0;
		Iterable<FeaturePermission> permissions = Collections.emptyList();
		permissions = manager.evaluateConditions(null, Collections.singleton(createCondition(null, null)));
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		permissions = manager.evaluateConditions(null, Collections.singleton(createCondition(new Date(), null)));
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		permissions = manager.evaluateConditions(null, Collections.singleton(createCondition(null, new Date())));
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		Date before = new Date(System.currentTimeMillis() - 100500);
		Date after = new Date(System.currentTimeMillis() + 100500);
		permissions = manager.evaluateConditions(null, Collections.singleton(createCondition(after, after)));
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		permissions = manager.evaluateConditions(null, Collections.singleton(createCondition(before, before)));
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		permissions = manager.evaluateConditions(null, Collections.singleton(createCondition(after, before)));
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		permissions = manager.evaluateConditions(null, Collections.singleton(createCondition(before, after)));
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);
	}

	@Test
	public void testEvaluateConditionBindUnbind() {
		int logSize = 0;
		int eventSize = 0;
		Map<String, Object> properties = Collections.singletonMap(LicensingProperties.LICENSING_CONDITION_TYPE_ID,
				CONDITION_TYPE);
		Map<String, String> values = Collections.singletonMap(CONDITION_TYPE, "*"); //$NON-NLS-1$
		Iterable<FeaturePermission> permissions = Collections.emptyList();
		Date before = new Date(System.currentTimeMillis() - 100500);
		Date after = new Date(System.currentTimeMillis() + 100500);

		PermissionEmitter emitter = LicensningBaseTests.createPermissionEmitter(values);
		LicensingCondition condition = createCondition(before, after);

		permissions = manager.evaluateConditions(null, Collections.singleton(condition));
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);
		manager.bindPermissionEmitter(emitter, properties);

		LicensingConfiguration configuration = LicensingConfigurations.INVALID;
		permissions = manager.evaluateConditions(configuration, Collections.singleton(condition));
		FeaturePermission next = permissions.iterator().next();
		assertEquals(condition, next.getLicensingCondition());
		assertEquals(configuration, next.getLicensingConfiguration());
		assertTrue(next.getLeaseDate().before(after));
		assertTrue(next.getExpireDate().after(before));
		checkMaps(logSize, ++eventSize);

		manager.unbindPermissionEmitter(emitter, properties);
		permissions = manager.evaluateConditions(null, Collections.singleton(condition));
		assertFalse(permissions.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);
	}

	protected LicensingCondition createCondition(Date from, Date until) {
		return LicensingConditions.create(FEATURE_ID, FEATURE_VERSION, null, from, until, CONDITION_TYPE,
				CONDITION_EXPRESSION);
	}

	@Test
	public void testExaminePermissionsNegative() {
		int logSize = 0;
		int eventSize = 0;
		Iterable<RestrictionVerdict> verdicts = Collections.emptyList();
		verdicts = manager.examinePermissons(null, null, null);
		assertFalse(verdicts.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		verdicts = manager.examinePermissons(conf, null, null);
		assertFalse(verdicts.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		verdicts = manager.examinePermissons(conf, new ArrayList<>(), null);
		assertFalse(verdicts.iterator().hasNext());
		checkMaps(++logSize, ++eventSize);

		verdicts = manager.examinePermissons(conf, Collections.singleton(null), null);
		assertFalse(verdicts.iterator().hasNext());
		logSize++;
		checkMaps(++logSize, ++eventSize);
	}

	protected void checkMaps(int logSize, int eventSize) {
		assertEquals(logSize, logged.size());
		assertEquals(eventSize, posted.size());
	}

}
