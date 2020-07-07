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

import java.util.Date;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;

/**
 * Permission expresses runtime state of a satisfied {@link LicensingCondition}:
 * all terms that {@link LicensingCondition} establishes are fulfilled and the
 * permission to use the feature of given name and version range is issues by
 * {@link PermissionEmitter}.
 * <p>
 * In other words, {@link PermissionEmitter} <i>evaluates</i> a
 * {@link LicensingCondition} and <i>emits or not</i> corresponding
 * {@link FeaturePermission}.
 *
 * @since 0.4.0
 * @deprecated use 1.0 {@linkplain Permission}
 */
@Deprecated
public interface FeaturePermission {

	LicensingConfiguration getLicensingConfiguration();

	/**
	 * The original {@code LicensingCondition} for which this
	 * {@link FeaturePermission} was emitted.
	 *
	 * @since 0.4.0
	 */
	LicensingCondition getLicensingCondition();

	/**
	 * In general case a {@link FeaturePermission} is time-limited.
	 * {@code LeaseDate} is timestamp of the permission emission.
	 *
	 * @see #getExpireDate()
	 * @since 0.4.0
	 */
	Date getLeaseDate();

	/**
	 * The date of the permission expiration. It is no longer valid after this date.
	 *
	 * @see #getLeaseDate()
	 * @since 0.4.0
	 */
	Date getExpireDate();
}
