/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
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
package org.eclipse.passage.lic.execute;

import java.util.Arrays;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.acquire.GrantsTraceService;
import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluationService;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluatorsRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionParsingService;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionPasringRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionProtocol;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionTokenAssessmentService;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionTokenAssessorsRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.PermissionEmittersRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.PermissionEmittingService;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironment;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironmentRegistry;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.lic.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.api.requirements.ResolvedRequirementsRegistry;
import org.eclipse.passage.lic.api.restrictions.PermissionsExaminationService;
import org.eclipse.passage.lic.api.restrictions.PermissionsExaminationServicesRegistry;
import org.eclipse.passage.lic.base.agreements.BaseAgreementAcceptanceService;
import org.eclipse.passage.lic.base.conditions.evaluation.BasePermissionEmittingService;
import org.eclipse.passage.lic.base.conditions.evaluation.BerlinProtocolExpressionParseService;
import org.eclipse.passage.lic.base.conditions.evaluation.SimpleMapExpressionEvaluationService;
import org.eclipse.passage.lic.base.conditions.mining.PersonalLicenseMiningEquipment;
import org.eclipse.passage.lic.base.io.MD5Hashes;
import org.eclipse.passage.lic.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.base.restrictions.BasePermissionsExaminationService;
import org.eclipse.passage.lic.bc.BcStreamCodec;
import org.eclipse.passage.lic.equinox.io.BundleKeyKeeper;
import org.eclipse.passage.lic.equinox.io.ConfigurationPath;
import org.eclipse.passage.lic.equinox.requirements.BundleRequirements;
import org.eclipse.passage.lic.equinox.requirements.ComponentRequirements;
import org.eclipse.passage.lic.internal.base.access.StoringGrantTraceService;
import org.eclipse.passage.lic.licenses.model.transport.XmiConditionTransport;
import org.eclipse.passage.lic.oshi.HardwareAssessmentService;
import org.eclipse.passage.lic.oshi.HardwareEnvironment;
import org.osgi.framework.Bundle;

@SuppressWarnings("restriction")
public abstract class BaseAccessCycleConfiguration implements AccessCycleConfiguration {

	private final Registry<StringServiceId, ResolvedRequirements> requirements;
	private final Registry<ContentType, ConditionTransport> transports;
	private final Registry<LicensedProduct, StreamCodec> codecs;
	private final Registry<LicensedProduct, KeyKeeper> keys;
	private final Registry<StringServiceId, Hashes> hashes;
	private final Registry<StringServiceId, PermissionEmittingService> emitters;
	private final Registry<ExpressionProtocol, ExpressionParsingService> expressionParsers;
	private final Registry<ExpressionProtocol, ExpressionEvaluationService> expressionEvaluators;
	private final Registry<EvaluationType, ExpressionTokenAssessmentService> tokenAssessors;
	private final Registry<EvaluationType, RuntimeEnvironment> environments;
	private final Registry<StringServiceId, PermissionsExaminationService> examinators;
	private final AgreementAcceptanceService acceptance;
	private final GrantsTraceService forsakenGrants;

	protected BaseAccessCycleConfiguration(Supplier<LicensedProduct> product, Supplier<Bundle> bundle) {
		requirements = new ReadOnlyRegistry<>(Arrays.asList(//
				new BundleRequirements(), //
				new ComponentRequirements() //
		));
		transports = new ReadOnlyRegistry<>(Arrays.asList(//
				new XmiConditionTransport() //
		));
		codecs = new ReadOnlyRegistry<>(Arrays.asList(//
				new BcStreamCodec(product) //
		));
		keys = new ReadOnlyRegistry<>(Arrays.asList(//
				new BundleKeyKeeper(product, bundle.get()) //
		));
		hashes = new ReadOnlyRegistry<>(new MD5Hashes());
		emitters = new ReadOnlyRegistry<>(Arrays.asList(//
				new BasePermissionEmittingService(//
						expressionParsers(), //
						expressionAssessors(), //
						expressionEvaluators()) //
		));
		expressionParsers = new ReadOnlyRegistry<>(Arrays.asList(//
				new BerlinProtocolExpressionParseService() //
		));
		expressionEvaluators = new ReadOnlyRegistry<>(Arrays.asList(//
				new SimpleMapExpressionEvaluationService() //
		));
		tokenAssessors = new ReadOnlyRegistry<>(Arrays.asList(//
				new HardwareAssessmentService(environments())//
		));
		environments = new ReadOnlyRegistry<>(Arrays.asList(//
				new HardwareEnvironment() //
		));
		acceptance = new BaseAgreementAcceptanceService(hashes(), product);
		examinators = new ReadOnlyRegistry<>(new BasePermissionsExaminationService(acceptance, product));
		forsakenGrants = new StoringGrantTraceService(product, new ConfigurationPath(), this::acquirers);
	}

	@Override
	public final MiningEquipment miningEquipment() {
		return new PersonalLicenseMiningEquipment(keyKeepers(), codecs(), transports());
	}

	@Override
	public final ResolvedRequirementsRegistry requirementResolvers() {
		return () -> requirements;
	}

	@Override
	public final StreamCodecRegistry codecs() {
		return () -> codecs;
	}

	@Override
	public final KeyKeeperRegistry keyKeepers() {
		return () -> keys;
	}

	@Override
	public final ConditionTransportRegistry transports() {
		return () -> transports;
	}

	@Override
	public final PermissionEmittersRegistry permissionEmitters() {
		return () -> emitters;
	}

	@Override
	public final ExpressionPasringRegistry expressionParsers() {
		return () -> expressionParsers;
	}

	@Override
	public final ExpressionEvaluatorsRegistry expressionEvaluators() {
		return () -> expressionEvaluators;
	}

	@Override
	public final ExpressionTokenAssessorsRegistry expressionAssessors() {
		return () -> tokenAssessors;
	}

	@Override
	public final RuntimeEnvironmentRegistry environments() {
		return () -> environments;
	}

	@Override
	public final PermissionsExaminationServicesRegistry examinators() {
		return () -> examinators;
	}

	@Override
	public final HashesRegistry hashes() {
		return () -> hashes;
	}

	@Override
	public AgreementAcceptanceService acceptance() {
		return acceptance;
	}

	@Override
	public GrantsTraceService grantsTrace() {
		return forsakenGrants;
	}

}
