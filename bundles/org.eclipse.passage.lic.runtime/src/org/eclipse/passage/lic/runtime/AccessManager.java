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
package org.eclipse.passage.lic.runtime;

/**
 * The main entry point to the licensing
 *
 */
public interface AccessManager {
	
	void registerConditionMiner(ConditionMiner conditionMiner);

	void unregisterConditionMiner(ConditionMiner conditionMiner);

	/**
	 * Aggregated method to:
	 * <li>resolve {@link ConfigurationRequirement}(s)</li>
	 * <li>obtain {@link LicensingCondition}(s)</li>
	 * <li>evaluate {@link LicensingCondition}(s) to receive
	 * {@link FeaturePermission}(s)</li>
	 * <li>examine requirements and permissions to compose
	 * {@link RestrictionVerdict}(s)</li>
	 * <li>execute {@link RestrictionVerdict}(s) to realize restrictions</li>
	 * 
	 * @param configuration
	 * @return the composite result of execution
	 */
	void executeAccessRestrictions(LicensingConfiguration configuration);

	Iterable<ConfigurationRequirement> resolveRequirements(LicensingConfiguration configuration);

	Iterable<ConfigurationRequirement> resolveFeatureRequirements(String featureId, LicensingConfiguration configuration);

	Iterable<LicensingCondition> extractConditions(LicensingConfiguration configuration);

	Iterable<FeaturePermission> evaluateConditions(Iterable<LicensingCondition> conditions, LicensingConfiguration configuration);

	Iterable<RestrictionVerdict> examinePermissons(Iterable<ConfigurationRequirement> requirements, Iterable<FeaturePermission> permissions, LicensingConfiguration configuration);

	Iterable<RestrictionVerdict> examineFeaturePermissons(String featureId, LicensingConfiguration configuration);

	void executeRestrictions(Iterable<RestrictionVerdict> restrictions);

}
