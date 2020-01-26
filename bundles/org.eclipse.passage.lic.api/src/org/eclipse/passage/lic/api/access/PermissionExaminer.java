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
import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionExecutor;
import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;

/**
 * The contract for a service responsible for the forth phase of <i>access cycle</i>: examining set of {@link FeaturePermission}s
 * (runtime-evaluated {@code LicensingCondition}s)
 * against all {@link LicensingRequirement}s for the same set of features.
 * Examining reports which {@link LicensingRequirement}s are left unsatisfied.
 *
 * @see LicensingRequirement
 * @see PermissionEmitter
 * @see FeaturePermission
 * @see RestrictionVerdict
 * @since 0.4.0
 */
public interface PermissionExaminer {

	/**
	 * Examines how {@link FeaturePermission}s cover the
	 * {@link LicensingRequirement}s and produce {@link RestrictionVerdict}s to
	 * be consumed by {@link RestrictionExecutor}s.
	 * Each {@link RestrictionVerdict} describes uncovered {@link LicensingRequirement}
	 *
	 * @param configuration overall configuration
	 * @param requirements  program licensing requirements to be covered by the {@code permissions}
	 * @param permissions   feature permissions collected for the appropriate set of features
	 * @return not null iterable structure of {@link RestrictionVerdict}s begotten by all the {@code requirements} unsatisfied
	 * by the given {@code permissions}
	 * @since 0.4.0
	 */
	Iterable<RestrictionVerdict> examine(LicensingConfiguration configuration,
			Iterable<LicensingRequirement> requirements, Iterable<FeaturePermission> permissions);

}
