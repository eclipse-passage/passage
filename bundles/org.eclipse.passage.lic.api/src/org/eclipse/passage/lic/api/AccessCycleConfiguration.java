/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.api;

import org.eclipse.passage.lic.api.acquire.GrantsTraceService;
import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluatorsRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionPasringRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionTokenAssessorsRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.PermissionEmittersRegistry;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironmentRegistry;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.api.requirements.ResolvedRequirementsRegistry;
import org.eclipse.passage.lic.api.restrictions.PermissionsExaminationServicesRegistry;

/**
 * Supplies all the service that runtime <i>access cycle</i> can count on.
 * 
 * @since 2.1
 */
public interface AccessCycleConfiguration {

	ResolvedRequirementsRegistry requirementResolvers();

	MinedConditionsRegistry conditionMiners();

	MiningEquipment miningEquipment();

	StreamCodecRegistry codecs();

	KeyKeeperRegistry keyKeepers();

	ConditionTransportRegistry transports();

	PermissionEmittersRegistry permissionEmitters();

	ExpressionPasringRegistry expressionParsers();

	ExpressionEvaluatorsRegistry expressionEvaluators();

	ExpressionTokenAssessorsRegistry expressionAssessors();

	RuntimeEnvironmentRegistry environments();

	PermissionsExaminationServicesRegistry examinators();

	LicenseAcquisitionServicesRegistry acquirers();

	HashesRegistry hashes();

	AgreementAcceptanceService acceptance();

	/**
	 * @since 2.3
	 */
	GrantsTraceService grantsTrace();

}
