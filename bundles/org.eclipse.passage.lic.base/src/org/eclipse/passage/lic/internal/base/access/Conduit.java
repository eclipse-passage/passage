/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.base.access;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.acquire.GrantAcquisition;
import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionService;
import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.base.access.GrantReleased;

final class Conduit {

	private final Supplier<LicensedProduct> product;
	private final Supplier<LicenseAcquisitionServicesRegistry> acquirers;

	Conduit(Supplier<LicensedProduct> product, Supplier<LicenseAcquisitionServicesRegistry> acquirers) {
		this.product = product;
		this.acquirers = acquirers;
	}

	/**
	 * @return actually released grants
	 */
	List<GrantAcquisition> release(List<GrantAcquisition> grants) {
		List<GrantAcquisition> released = new ArrayList<>();
		for (GrantAcquisition grant : grants) {
			if (release(grant)) {
				released.add(grant);
			}
		}
		return released;
	}

	private boolean release(GrantAcquisition grant) {
		LicensedProduct licproduct = product.get();
		for (LicenseAcquisitionService service : acquirers.get().get().services()) {
			ServiceInvocationResult<Boolean> result = service.release(licproduct, grant);
			if (new GrantReleased().test(result)) {
				return true;
			}
		}
		return false;
	}

}
