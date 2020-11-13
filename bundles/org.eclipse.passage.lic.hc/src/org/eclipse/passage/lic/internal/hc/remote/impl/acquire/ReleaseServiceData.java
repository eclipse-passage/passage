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
package org.eclipse.passage.lic.internal.hc.remote.impl.acquire;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.acquire.GrantAcqisition;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData;

final class ReleaseServiceData extends RemoteServiceData {
	private GrantAcqisition acqisition;

	ReleaseServiceData(LicensedProduct product, GrantAcqisition acqisition) {
		super(product);
		this.acqisition = acqisition;
	}

	GrantAcqisition acqisition() {
		return acqisition;
	}

}
