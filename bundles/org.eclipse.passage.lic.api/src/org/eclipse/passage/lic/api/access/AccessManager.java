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
package org.eclipse.passage.lic.api.access;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;

/**
 * The main entry point to the licensing
 *
 * @since 0.4.0
 */
public interface AccessManager {

	/**
	 * Aggregated method to:
	 * <li>resolve {@link LicensingRequirement}(s)</li>
	 * <li>obtain {@link LicensingCondition}(s)</li>
	 * <li>evaluate {@link LicensingCondition}(s) to emit
	 * {@link FeaturePermission}(s)</li>
	 * <li>examine requirements and permissions to compose
	 * {@link RestrictionVerdict}(s)</li>
	 * <li>execute {@link RestrictionVerdict}(s) to realize restrictions</li>
	 * 
	 * @param configuration
	 * @return the composite result of execution
	 */
	LicensingResult executeAccessRestrictions(LicensingConfiguration configuration);

	Iterable<LicensingRequirement> resolveRequirements(LicensingConfiguration configuration);

	Iterable<LicensingCondition> extractConditions(LicensingConfiguration configuration);

	Iterable<FeaturePermission> evaluateConditions(LicensingConfiguration configuration,
			Iterable<LicensingCondition> conditions);

	Iterable<RestrictionVerdict> examinePermissons(LicensingConfiguration configuration,
			Iterable<LicensingRequirement> requirements, Iterable<FeaturePermission> permissions);

	LicensingResult executeRestrictions(LicensingConfiguration configuration,
			Iterable<RestrictionVerdict> restrictions);

}
