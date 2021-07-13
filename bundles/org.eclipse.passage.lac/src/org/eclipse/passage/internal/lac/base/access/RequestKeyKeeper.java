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
package org.eclipse.passage.internal.lac.base.access;

import java.io.InputStream;

import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;

final class RequestKeyKeeper<R extends NetRequest> implements KeyKeeper {

	// TODO: stated request
	private final ProductUserRequest<R> request;

	public RequestKeyKeeper(ProductUserRequest<R> request) {
		this.request = request;
	}

	@Override
	public LicensedProduct id() {
		return request.product().get();
	}

	@Override
	public InputStream productPublicKey() throws LicensingException {
		// TODO: read from request.content or state, if already sent
		return null;
	}

}
