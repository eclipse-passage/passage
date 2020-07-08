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
package org.eclipse.passage.lic.internal.api;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.PermissionEmittersRegistry;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionEvaluatorsRegistry;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionPasringRegistry;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionTokenAssessorsRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirementsRegistry;

/**
 * Supplies all the service that runtime <i>access cycle</i> can count on.
 */
public interface AccessCycleConfiguration {

	ResolvedRequirementsRegistry requirementResolvers();

	MinedConditionsRegistry conditionMiners();

	StreamCodecRegistry codecs();

	KeyKeeperRegistry keyKeepers();

	ConditionTransportRegistry transports();

	PermissionEmittersRegistry permissionEmitters();

	ExpressionPasringRegistry expressionParsers();

	ExpressionEvaluatorsRegistry expressionEvaluators();

	ExpressionTokenAssessorsRegistry expressionAssessors();

}
