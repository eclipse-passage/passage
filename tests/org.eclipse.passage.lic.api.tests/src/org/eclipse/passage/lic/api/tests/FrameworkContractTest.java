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
package org.eclipse.passage.lic.api.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakeExpressionEvaluationService;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakeConditionExpressionParser;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakeExpressionTokenAssessmentService;
import org.eclipse.passage.lic.api.tests.fakes.conditions.mining.FakeMinedConditions;
import org.eclipse.passage.lic.api.tests.fakes.io.FakeConditionTransport;
import org.eclipse.passage.lic.api.tests.fakes.io.FakeKeyKeeper;
import org.eclipse.passage.lic.api.tests.fakes.io.FakeStreamCodec;
import org.eclipse.passage.lic.api.tests.fakes.requirements.FakeResolvedRequirements;
import org.eclipse.passage.lic.internal.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionProtocol;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.Service;
import org.eclipse.passage.lic.internal.api.registry.ServiceId;
import org.junit.Ignore;
import org.junit.Test;

/**
 * <p>
 * Here we check severe assumptions Passage does about instance of
 * {@linkplain Framework} interface it operates with.
 * </p>
 * <p>
 * Each sealed {@linkplain Framework} implementation must follow the contract:
 * extend the test, supply a framework instance and succeed at every test.
 * </p>
 */
@SuppressWarnings("restriction")
public abstract class FrameworkContractTest {

	@Test
	public final void exists() {
		assertNotNull(framework());
		assertTrue(framework().isPresent());
	}

	@Test
	public final void suppliesLicensedProductInformation() {
		assertNotNull(framework().get().product());
	}

	@Test
	public final void canResolveRequirements() {
		assertServiceRegistryIsFunctional(config().requirementResolvers().get());
	}

	@Test
	public final void prohibitsRequirementsResolutionExtension() {
		assertTrue(readOnly(config().requirementResolvers().get()));
	}

	@Test
	public final void prohibitsInjectionIntoRequirementResolutionServices() {
		assertServiceInjectionsIsProhibited(config().requirementResolvers().get(), new FakeResolvedRequirements());
	}

	@Test
	public final void canMineConditions() {
		assertServiceRegistryIsFunctional(config().conditionMiners().get());
	}

	@Test
	public final void prohibitsConditionMiningExtension() {
		assertTrue(readOnly(config().conditionMiners().get()));
	}

	@Test
	public final void prohibitsInjectionIntoConditionMiningServices() {
		assertServiceInjectionsIsProhibited(config().conditionMiners().get(), new FakeMinedConditions());
	}

	@Test
	public final void canEncodeAndDecodeStreams() {
		assertServiceRegistryIsFunctional(config().codecs().get());
	}

	@Test
	public final void prohibitsStreamCodecServicesExtension() {
		assertTrue(readOnly(config().codecs().get()));
	}

	@Test
	public final void prohibitsInjectionIntoStreamCodecServices() {
		assertServiceInjectionsIsProhibited(config().codecs().get(), new FakeStreamCodec());
	}

	@Test
	public final void canEncodeAndDecodeForProduct() {
		assertTrue(config().codecs().get().hasService(framework().get().product()));
	}

	@Test
	public final void hasKeyKeepers() {
		assertServiceRegistryIsFunctional(config().keyKeepers().get());
	}

	@Test
	public final void prohibitsKeyKeeperServicesExtension() {
		assertTrue(readOnly(config().keyKeepers().get()));
	}

	@Test
	public final void prohibitsInjectionIntoKeyKeeperServices() {
		assertServiceInjectionsIsProhibited(config().keyKeepers().get(), new FakeKeyKeeper());
	}

	@Test
	public final void keepsKeyForProduct() {
		assertTrue(config().keyKeepers().get().hasService(framework().get().product()));
	}

	@Test
	public final void hasConditionTransports() {
		assertServiceRegistryIsFunctional(config().transports().get());
	}

	@Test
	public final void prohibitsConditionTransportServicesExtension() {
		assertTrue(readOnly(config().transports().get()));
	}

	@Test
	public final void prohibitsInjectionIntoConditionTransportServices() {
		assertServiceInjectionsIsProhibited(config().transports().get(), new FakeConditionTransport());
	}

	@Test
	public final void hasTransportForXml() {
		assertTrue(config().transports().get().hasService(new ContentType.Xml()));
	}

	@Test
	public final void canParseConditionExpressions() {
		assertServiceRegistryIsFunctional(config().expressionParsers().get());
	}

	@Test
	public final void prohibitsExpressionParseServicesExtension() {
		assertTrue(readOnly(config().expressionParsers().get()));
	}

	@Test
	public final void prohibitsInjectionIntoExpressionParseServices() {
		assertServiceInjectionsIsProhibited(config().expressionParsers().get(), new FakeConditionExpressionParser());
	}

	@Test
	public final void hasParserForDefaultExpressionFormat() {
		assertTrue(config().expressionParsers().get().hasService(new ExpressionProtocol.Default()));
	}

	@Test
	public final void canEvaluateConditionExpressions() {
		assertServiceRegistryIsFunctional(config().expressionEvaluators().get());
	}

	@Test
	public final void prohibitsExpressionEvaluationServicesExtension() {
		assertTrue(readOnly(config().expressionEvaluators().get()));
	}

	@Test
	public final void prohibitsInjectionIntoExpressionEvaluationServices() {
		assertServiceInjectionsIsProhibited(config().expressionEvaluators().get(),
				new FakeExpressionEvaluationService());
	}

	@Test
	public final void hasEvaluatorForDefaultExpressionFormat() {
		assertTrue(config().expressionEvaluators().get().hasService(new ExpressionProtocol.Default()));
	}

	@Test
	@Ignore // yet to be implemented (OSHI-based)
	public final void canAssessConditionExpressionsSegment() {
		assertServiceRegistryIsFunctional(config().expressionAssessors().get());
	}

	@Test
	public final void prohibitsExpressionSegmentAssessmentServicesExtension() {
		assertTrue(readOnly(config().expressionAssessors().get()));
	}

	@Test
	public final void prohibitsInjectionIntoSegmentAssessmentServices() {
		assertServiceInjectionsIsProhibited(config().expressionAssessors().get(),
				new FakeExpressionTokenAssessmentService());
	}

	private <I extends ServiceId, S extends Service<I>> void assertServiceRegistryIsFunctional(
			Registry<I, S> registry) {
		assertNotNull(registry);
		assertNotNull(registry.services());
		assertTrue(registry.services().size() > 0);
	}

	private <I extends ServiceId, S extends Service<I>> void assertServiceInjectionsIsProhibited(
			Registry<I, S> registry, S intruder) {
		int before = registry.services().size();
		try {
			registry.services().add(intruder);
		} catch (UnsupportedOperationException unsupported) {
			// then (hooray! unsupported! no injections!)
			return;
		}
		assertTrue(before == registry.services().size());
	}

	private AccessCycleConfiguration config() {
		return framework().get().accessCycleConfiguration();
	}

	protected abstract Optional<Framework> framework();

	protected abstract boolean readOnly(Registry<?, ?> registry);

}
