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
package org.eclipse.passage.lic.api.restrictions;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.access.PermissionExaminer;
import org.eclipse.passage.lic.api.requirements.LicensingRequirement;

/**
 *
 * The data required to execute the restriction, produced by
 * {@link PermissionExaminer} and consumed by {@link RestrictionExecutor}
 *
 * @since 0.4.0
 */
public interface RestrictionVerdict {

	/**
	 * General configuration
	 */
	LicensingConfiguration getLicensingConfiguration();

	/**
	 * <p>The original {@code LicensingRequirement} which was admitted by {@code PermissionExaminer}
	 * to be not satisfied  by existing set of {@code FeaturePermission}s.</p>
	 *
	 * @see LicensingRequirement
	 * @see org.eclipse.passage.lic.api.access.FeaturePermission
	 * @see org.eclipse.passage.lic.api.access.PermissionExaminer
	 */
	LicensingRequirement getLicensingRequirement();

	/**
	 * Level of restriction severity, described freely, like "warn" or "fatal"
	 */
	String getRestrictionLevel();

	int getRestrictionCode();

}
