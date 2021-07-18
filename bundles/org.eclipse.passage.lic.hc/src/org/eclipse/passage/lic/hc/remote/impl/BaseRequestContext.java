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
package org.eclipse.passage.lic.hc.remote.impl;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.hc.remote.RequestContext;

/**
 * 
 * @since 1.1
 */
public final class BaseRequestContext implements RequestContext {

	private final LicensedProduct product;
	private final String hash;

	public BaseRequestContext(LicensedProduct product, String hash) {
		this.product = product;
		this.hash = hash;
	}

	@Override
	public LicensedProduct product() {
		return product;
	}

	@Override
	public String hash() {
		return hash;
	}

}
