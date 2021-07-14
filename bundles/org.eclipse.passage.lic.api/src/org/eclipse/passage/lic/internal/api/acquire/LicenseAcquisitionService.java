/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.api.acquire;

import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.registry.Service;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;

public interface LicenseAcquisitionService extends Service<ConditionMiningTarget> {

	ServiceInvocationResult<GrantAcquisition> acquire(LicensedProduct product, String feature);

	ServiceInvocationResult<Boolean> release(LicensedProduct product, GrantAcquisition acquisition);

}
