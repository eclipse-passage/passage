/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.api.acquire;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.registry.Service;

/**
 *
 * @since 2.1
 */
public interface LicenseAcquisitionService extends Service<ConditionMiningTarget> {

	/**
	 * @since 4.0
	 */
	ServiceInvocationResult<GrantAcquisition> acquire(LicensedProduct product, FeatureIdentifier feature);

	ServiceInvocationResult<Boolean> release(LicensedProduct product, GrantAcquisition acquisition);

}
