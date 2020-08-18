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
package org.eclipse.passage.lbc.internal.base;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.eclipse.passage.lbc.internal.api.BackendLicenseVault;
import org.eclipse.passage.lbc.internal.api.ProductLicensesRequest;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.conditions.BaseConditionPack;

/**
 * @since 1.0
 */
public final class BaseLicenseVault implements BackendLicenseVault {

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> availableLicenses(ProductLicensesRequest request) {
		return new BaseServiceInvocationResult<>(
				Optional.of(Collections.singletonList(new BaseConditionPack("floating", Collections.emptyList())))); //$NON-NLS-1$
	}

}
