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
package org.eclipse.passage.lic.internal.net.handle;

import java.util.Optional;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.ProductIdentifier;
import org.eclipse.passage.lic.base.ProductVersion;
import org.eclipse.passage.lic.internal.net.EncodingAlgorithm;
import org.eclipse.passage.lic.internal.net.LicenseUser;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;

public final class ProductUserRequest<R extends NetRequest> {

	private final R raw;
	private final Optional<LicensedProduct> product;
	private final Optional<String> user;
	private final Optional<String> algorithm;

	public ProductUserRequest(R raw) throws LicensingException {
		this.raw = raw;
		this.product = extractProduct();
		this.user = extractUser();
		this.algorithm = extractAlgorithm();
	}

	private Optional<LicensedProduct> extractProduct() throws LicensingException {
		Optional<String> id = new ProductIdentifier(raw::parameter).get();
		Optional<String> version = new ProductVersion(raw::parameter).get();
		if (!id.isPresent() || !version.isPresent()) {
			return Optional.empty();
		}
		return Optional.of(new BaseLicensedProduct(//
				new DecodedParam(id.get()).get(), //
				new DecodedParam(version.get()).get()));
	}

	private Optional<String> extractUser() {
		return new LicenseUser(raw::parameter).get();
	}

	private Optional<String> extractAlgorithm() {
		return new EncodingAlgorithm(raw::parameter).get();
	}

	public R raw() {
		return raw;
	}

	public Optional<LicensedProduct> product() {
		return product;
	}

	public Optional<String> user() {
		return user;
	}

	public Optional<String> algorithm() {
		return algorithm;
	}
}
