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
package org.eclipse.passage.lic.api.restrictions;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.access.PermissionExaminer;
import org.eclipse.passage.lic.api.requirements.LicensingRequirement;

/**
 *
 * The data required to execute the restriction, produced by
 * {@link PermissionExaminer} and consumed by {@link RestrictionExecutor}
 *
 * @see RestrictionExecutor
 * @since 0.4.0
 * @deprecated use 1.0 internal Restriction
 */
@Deprecated
public interface RestrictionVerdict {

	/**
	 * General configuration
	 *
	 * @since 0.4.0
	 */
	LicensingConfiguration getLicensingConfiguration();

	/**
	 * <p>
	 * The original {@code LicensingRequirement} which was admitted by
	 * {@code PermissionExaminer} to be not satisfied by existing set of
	 * {@code FeaturePermission}s.
	 * </p>
	 *
	 * @see LicensingRequirement
	 * @see org.eclipse.passage.lic.api.access.FeaturePermission
	 * @see org.eclipse.passage.lic.api.access.PermissionExaminer
	 * @since 0.4.0
	 */
	LicensingRequirement getLicensingRequirement();

	/**
	 * Level of restriction severity, described freely, like "warn" or "fatal"
	 *
	 * @since 0.4.0
	 */
	String getRestrictionLevel();

	/**
	 * Encoded reason of restriction, required for {@link RestrictionExecutor}
	 *
	 * @since 0.4.0
	 */
	int getRestrictionCode();

}
