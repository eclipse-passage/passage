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
package org.eclipse.passage.lbc.internal.base.tobemoved;

import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.internal.api.tobemoved.RawRequest;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;

final class ProductFromRequest implements Supplier<Optional<LicensedProduct>> {
	private final RawRequest request;

	ProductFromRequest(RawRequest request) {
		this.request = request;
	}

	@Override
	public Optional<LicensedProduct> get() {
		Optional<String> id = new ProductIdentifier(key -> request.parameter(key)).get();
		Optional<String> version = new ProductVersion(key -> request.parameter(key)).get();
		if (!id.isPresent() || !version.isPresent()) {
			return Optional.empty();
		}
		return Optional.of(new BaseLicensedProduct(id.get(), version.get()));
	}

}
