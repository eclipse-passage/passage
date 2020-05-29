/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.api.access;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;

/**
 * The main entry point to the licensing.
 *
 * @see org.eclipse.passage.lic.api
 * @since 0.4.0
 */
public interface AccessManager {

	/**
	 * <p>
	 * Aggregating method for the full <i>access cycle</i>, which consists of the
	 * following steps:
	 * </p>
	 * <ol>
	 * <li>resolve {@link LicensingRequirement}s</li>
	 * <li>obtain {@link LicensingCondition}s</li>
	 * <li>evaluate {@link LicensingCondition}s to emit
	 * {@link FeaturePermission}s</li>
	 * <li>examine requirements and permissions to compose
	 * {@link RestrictionVerdict}s</li>
	 * <li>execute {@link RestrictionVerdict}s to realize restrictions</li>
	 * </ol>
	 *
	 * @param configuration - general configuration for the licensing process
	 * @return the composite result of execution
	 * @see #resolveRequirements
	 * @see #extractConditions
	 * @see #evaluateConditions
	 * @see #evaluateConditions
	 * @see #examinePermissons
	 * @see #executeRestrictions
	 * @see org.eclipse.passage.lic.api
	 * @since 0.4.0
	 */
	LicensingResult executeAccessRestrictions(LicensingConfiguration configuration);

	/**
	 * Implementation of the first phase of <i>access cycle</i>: <i>requirements
	 * resolution</i>. Here {@link LicensingRequirement}s are retrieved from a
	 * running program physical artifacts.
	 *
	 * @param configuration general configuration for the whole <i>access cycle</i>
	 * @return all the requirements resolved
	 * @see LicensingRequirement
	 * @see org.eclipse.passage.lic.api.requirements.RequirementResolver
	 * @see org.eclipse.passage.lic.api
	 * @since 0.4.0
	 */
	Iterable<LicensingRequirement> resolveRequirements(LicensingConfiguration configuration);

	/**
	 * Implementation of the second phase of <i>access cycle</i>: <i>condition
	 * mining</i>. Here {@link LicensingCondition}s are gained from license
	 * information sources of sorts.
	 *
	 * @param configuration general configuration for the whole <i>access cycle</i>
	 * @return all the conditions mined
	 * @see LicensingCondition
	 * @see org.eclipse.passage.lic.api.conditions.ConditionMiner
	 * @see org.eclipse.passage.lic.api
	 * @since 0.4.0
	 */
	Iterable<LicensingCondition> extractConditions(LicensingConfiguration configuration);

	/**
	 * Implementation of the third phase of <i>access cycle</i>: <i>condition
	 * evaluation</i>, where a {@link LicensingCondition} is analysed on how it's
	 * terms are satisfied in the current program runtime. In case the
	 * {@link LicensingCondition} is fully satisfied, a {@link FeaturePermission} is
	 * emitted.
	 *
	 * @param configuration general configuration for the whole <i>access cycle</i>
	 * @param conditions    all the conditions to be evaluated
	 * @return all the permissions granted
	 * @see PermissionEmitter
	 * @see org.eclipse.passage.lic.api
	 * @since 0.4.0
	 */
	Iterable<FeaturePermission> evaluateConditions(LicensingConfiguration configuration,
			Iterable<LicensingCondition> conditions);

	/**
	 * Implementation of the fourth phase of <i>access cycle</i>: <i>permission
	 * examining</i>.
	 * 
	 * Here {@link FeaturePermission}s are examined on how fully they satisfy all
	 * the {@link LicensingRequirement}s.
	 *
	 * @param configuration general configuration for the whole <i>access cycle</i>
	 * @param permissions   all the {@link FeaturePermission} emitted at the third
	 *                      phase of <i> access cycle</i>
	 * @param requirements  all the {@link LicensingRequirement}s resolved at the
	 *                      first phase of <i> access cycle</i>
	 * @return set of restriction verdicts for all the {@code requirements} that are
	 *         not fully satisfied by the given {@code permissions}
	 * @see RestrictionVerdict
	 * @see org.eclipse.passage.lic.api.conditions.ConditionMiner
	 * @see org.eclipse.passage.lic.api
	 * @since 0.7
	 */
	default Iterable<RestrictionVerdict> examinePermissions(LicensingConfiguration configuration,
			Iterable<LicensingRequirement> requirements, Iterable<FeaturePermission> permissions) {
		return examinePermissons(configuration, requirements, permissions);
	}

	/**
	 * Implementation of the fourth phase of <i>access cycle</i>: <i>permission
	 * examining</i>.
	 * 
	 * Here {@link FeaturePermission}s are examined on how fully they satisfy all
	 * the {@link LicensingRequirement}s.
	 *
	 * @param configuration general configuration for the whole <i>access cycle</i>
	 * @param permissions   all the {@link FeaturePermission} emitted at the third
	 *                      phase of <i> access cycle</i>
	 * @param requirements  all the {@link LicensingRequirement}s resolved at the
	 *                      first phase of <i> access cycle</i>
	 * @return set of restriction verdicts for all the {@code requirements} that are
	 *         not fully satisfied by the given {@code permissions}
	 * @see RestrictionVerdict
	 * @see org.eclipse.passage.lic.api.conditions.ConditionMiner
	 * @see org.eclipse.passage.lic.api
	 * @since 0.4
	 * 
	 * @deprecated use
	 *             {@link #examinePermissions(LicensingConfiguration, Iterable, Iterable)}
	 */
	@Deprecated
	Iterable<RestrictionVerdict> examinePermissons(LicensingConfiguration configuration,
			Iterable<LicensingRequirement> requirements, Iterable<FeaturePermission> permissions);

	/**
	 * Implementation of the final phase of <i>restriction execution</i>:
	 * <i>permission examining</i>, where all {@link RestrictionVerdict}s gained at
	 * the previous phase are applied to the program runtime.
	 *
	 * @param configuration general configuration for the whole <i>access cycle</i>
	 * @param restrictions  restriction verdicts to be applied
	 * @see org.eclipse.passage.lic.api
	 * @since 0.4.0
	 */
	LicensingResult executeRestrictions(LicensingConfiguration configuration,
			Iterable<RestrictionVerdict> restrictions);

}
