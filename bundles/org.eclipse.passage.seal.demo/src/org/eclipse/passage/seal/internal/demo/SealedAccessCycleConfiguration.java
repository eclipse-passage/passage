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
package org.eclipse.passage.seal.internal.demo;

import java.util.Arrays;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionService;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.internal.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionEvaluationService;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionEvaluatorsRegistry;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionParsingService;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionPasringRegistry;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionProtocol;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionTokenAssessmentService;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionTokenAssessorsRegistry;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.PermissionEmittersRegistry;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.PermissionEmittingService;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.internal.api.inspection.RuntimeEnvironment;
import org.eclipse.passage.lic.internal.api.inspection.RuntimeEnvironmentRegistry;
import org.eclipse.passage.lic.internal.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirementsRegistry;
import org.eclipse.passage.lic.internal.api.restrictions.PermissionsExaminationService;
import org.eclipse.passage.lic.internal.api.restrictions.PermissionsExaminationServicesRegistry;
import org.eclipse.passage.lic.internal.base.acquire.UserHomeLicenseAcquisitionService;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.BasePermissionEmittingService;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.BerlinProtocolExpressionParseService;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.MunichProtocolExpressionParseService;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.SimpleMapExpressionEvaluationService;
import org.eclipse.passage.lic.internal.base.conditions.mining.PersonalLicenseMiningEquipment;
import org.eclipse.passage.lic.internal.base.conditions.mining.UserHomeResidentConditions;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.internal.base.restrictions.BasePermissionsExaminationService;
import org.eclipse.passage.lic.internal.bc.BcStreamCodec;
import org.eclipse.passage.lic.internal.equinox.acquire.ConfigurationLicenseAcquisitionService;
import org.eclipse.passage.lic.internal.equinox.acquire.InstallationLicenseAcquisitionService;
import org.eclipse.passage.lic.internal.equinox.conditions.ConfigurationResidentConditions;
import org.eclipse.passage.lic.internal.equinox.conditions.InstallationResidentConditions;
import org.eclipse.passage.lic.internal.equinox.io.BundleKeyKeeper;
import org.eclipse.passage.lic.internal.equinox.requirements.BundleRequirements;
import org.eclipse.passage.lic.internal.equinox.requirements.ComponentRequirements;
import org.eclipse.passage.lic.internal.json.JsonConditionTransport;
import org.eclipse.passage.lic.internal.licenses.migration.tobemoved.XmiConditionTransport;
import org.eclipse.passage.lic.internal.oshi.HardwareAssessmentService;
import org.eclipse.passage.lic.internal.oshi.HardwareEnvironment;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

@SuppressWarnings("restriction")
final class SealedAccessCycleConfiguration implements AccessCycleConfiguration {

	private final Registry<StringServiceId, ResolvedRequirements> requirements;
	private final Registry<ConditionMiningTarget, MinedConditions> conditions;
	private final Registry<ContentType, ConditionTransport> transports;
	private final Registry<LicensedProduct, StreamCodec> codecs;
	private final Registry<LicensedProduct, KeyKeeper> keys;
	private final Registry<StringServiceId, PermissionEmittingService> emitters;
	private final Registry<ExpressionProtocol, ExpressionParsingService> expressionParsers;
	private final Registry<ExpressionProtocol, ExpressionEvaluationService> expressionEvaluators;
	private final Registry<EvaluationType, ExpressionTokenAssessmentService> tokenAssessors;
	private final Registry<EvaluationType, RuntimeEnvironment> environments;
	private final Registry<StringServiceId, PermissionsExaminationService> examinators;
	private final Registry<ConditionMiningTarget, LicenseAcquisitionService> acquirers;

	SealedAccessCycleConfiguration(Supplier<LicensedProduct> product) {
		requirements = new ReadOnlyRegistry<>(Arrays.asList(//
				new BundleRequirements(), //
				new ComponentRequirements() //
		));
		conditions = new ReadOnlyRegistry<>(Arrays.asList(//
				// new RemoteConditions(keyKeepers(), codecs(), transports()), //
				new UserHomeResidentConditions(miningEquipment()), //
				new InstallationResidentConditions(miningEquipment()), //
				new ConfigurationResidentConditions(miningEquipment())//
		));
		transports = new ReadOnlyRegistry<>(Arrays.asList(//
				new JsonConditionTransport(), //
				new XmiConditionTransport() //
		));
		codecs = new ReadOnlyRegistry<>(Arrays.asList(//
				new BcStreamCodec(product) //
		));
		keys = new ReadOnlyRegistry<>(Arrays.asList(//
				new BundleKeyKeeper(product, bundle()) //
		));
		emitters = new ReadOnlyRegistry<>(Arrays.asList(//
				new BasePermissionEmittingService(//
						expressionParsers(), //
						expressionAssessors(), //
						expressionEvaluators()) //
		));
		expressionParsers = new ReadOnlyRegistry<>(Arrays.asList(//
				new BerlinProtocolExpressionParseService(), //
				new MunichProtocolExpressionParseService() //
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
		examinators = new ReadOnlyRegistry<>(Arrays.asList(//
				new BasePermissionsExaminationService()//
		));
		acquirers = new ReadOnlyRegistry<>(Arrays.asList(//
				// new RemoteAcquisitionService(keyKeepers(), codecs()), //
				new UserHomeLicenseAcquisitionService(), //
				new InstallationLicenseAcquisitionService(), //
				new ConfigurationLicenseAcquisitionService()//
		));
	}

	MiningEquipment miningEquipment() {
		return new PersonalLicenseMiningEquipment(keyKeepers(), codecs(), transports());
	}

	private Bundle bundle() {
		return FrameworkUtil.getBundle(getClass());
	}

	@Override
	public ResolvedRequirementsRegistry requirementResolvers() {
		return () -> requirements;
	}

	@Override
	public MinedConditionsRegistry conditionMiners() {
		return () -> conditions;
	}

	@Override
	public StreamCodecRegistry codecs() {
		return () -> codecs;
	}

	@Override
	public KeyKeeperRegistry keyKeepers() {
		return () -> keys;
	}

	@Override
	public ConditionTransportRegistry transports() {
		return () -> transports;
	}

	@Override
	public PermissionEmittersRegistry permissionEmitters() {
		return () -> emitters;
	}

	@Override
	public ExpressionPasringRegistry expressionParsers() {
		return () -> expressionParsers;
	}

	@Override
	public ExpressionEvaluatorsRegistry expressionEvaluators() {
		return () -> expressionEvaluators;
	}

	@Override
	public ExpressionTokenAssessorsRegistry expressionAssessors() {
		return () -> tokenAssessors;
	}

	@Override
	public RuntimeEnvironmentRegistry environments() {
		return () -> environments;
	}

	@Override
	public PermissionsExaminationServicesRegistry examinators() {
		return () -> examinators;
	}

	@Override
	public LicenseAcquisitionServicesRegistry acquirers() {
		return () -> acquirers;
	}

}
