/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.base.acquire;

import java.util.Date;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.acquire.GrantAcquisition;
import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionService;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;

// FIXME: just stub for now. Implement properly. #568791
/**
 * 
 * @since 2.1
 */
public abstract class LocalLicenseAcquisitionService implements LicenseAcquisitionService {

	@Override
	public final ServiceInvocationResult<GrantAcquisition> acquire(LicensedProduct product, String feature) {
		return new BaseServiceInvocationResult<>(//
				new BaseGrantAcquisition(//
						"local", //$NON-NLS-1$
						"temp", //$NON-NLS-1$
						feature, //
						"user", //$NON-NLS-1$
						new Date())//
		);
	}

	@Override
	public final ServiceInvocationResult<Boolean> release(LicensedProduct product, GrantAcquisition acquisition) {
		return new BaseServiceInvocationResult<>(Boolean.TRUE);
	}

}
