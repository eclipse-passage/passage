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
package org.eclipse.passage.lic.runtime.access;

import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.requirements.LicensingRequirement;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionVerdict;

/**
 * The main entry point to the licensing
 *
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

	Iterable<FeaturePermission> evaluateConditions(Iterable<LicensingCondition> conditions,
			LicensingConfiguration configuration);

	Iterable<RestrictionVerdict> examinePermissons(Iterable<LicensingRequirement> requirements,
			Iterable<FeaturePermission> permissions, LicensingConfiguration configuration);

	LicensingResult executeRestrictions(Iterable<RestrictionVerdict> restrictions);

}
