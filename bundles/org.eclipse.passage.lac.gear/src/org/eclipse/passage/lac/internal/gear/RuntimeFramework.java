/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lac.internal.gear;

import org.eclipse.passage.lic.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.base.io.UserHomePath;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;

final class RuntimeFramework implements Framework {

	private final ProductUserRequest<? extends NetRequest> request;

	public RuntimeFramework(ProductUserRequest<? extends NetRequest> request) {
		this.request = request;
	}

	@Override
	public LicensedProduct product() {
		return request.product().get();
	}

	@Override
	public AccessCycleConfiguration accessCycleConfiguration() {
		return new RuntimeConfiguration(new UserHomePath(), request);
	}

}
