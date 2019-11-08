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
 * Interface of a service responsible for the third step of <i> access cycle</i>: condition evaluation.
 * According to the contract, a service must be able to evaluate given {@link LicensingCondition}s against the current program runtime.
 * As a result of evaluation, for a {@link LicensingCondition} a {@link FeaturePermission} must be emitted
 * in the case all of the {@link LicensingCondition}'s terms are met at the current program runtime.
 *
 * @see org.eclipse.passage.lic.api.conditions.ConditionMiner
 * @since 0.4.0
 */
public interface PermissionEmitter {
	/**
	 * Evaluates the collection of {@link LicensingCondition}s to emit a collection  of {@link FeaturePermission}.
	 *
	 * @param configuration general configuration for the whole <i>access cycle</i>
	 * @param conditions source conditions to be evaluated against the current program runtime
	 * @since 0.4.0
	 */
	Iterable<FeaturePermission> emitPermissions(LicensingConfiguration configuration,
			Iterable<LicensingCondition> conditions) throws LicensingException;

}
