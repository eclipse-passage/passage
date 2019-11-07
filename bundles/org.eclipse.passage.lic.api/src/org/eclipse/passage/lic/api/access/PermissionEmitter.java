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
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;

/**
 * Interface of a service responsible for {@link LicensingCondition}s evaluation against the current program runtime.
 * As a result of evaluation, for a {@link LicensingCondition} a {@code FeaturePermission} can be emitted,
 * in case all the {@link LicensingCondition}'s terms are met at the current program runtime.
 *
 * @see LicensingCondition
 * @see FeaturePermission
 * @since 0.4.0
 */
public interface PermissionEmitter {
	/**
	 * Evaluates the collection of {@link LicensingCondition} to emit a collection
	 * of {@link FeaturePermission}
	 *
	 */
	Iterable<FeaturePermission> emitPermissions(LicensingConfiguration configuration,
			Iterable<LicensingCondition> conditions) throws LicensingException;

}
