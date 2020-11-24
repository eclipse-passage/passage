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

import org.eclipse.passage.lbc.internal.api.tobemoved.RawRequest;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.FeatureIdentifier;

public final class FeatureRequest {

	private final ProductUserRequest delegate;
	private final Optional<String> feature;

	FeatureRequest(ProductUserRequest request) throws LicensingException {
		this.delegate = request;
		this.feature = feature(request.raw());
	}

	private Optional<String> feature(RawRequest raw) {
		return new FeatureIdentifier(raw::parameter).get();
	}

	public RawRequest raw() {
		return delegate.raw();
	}

	public Optional<LicensedProduct> product() {
		return delegate.product();
	}

	public Optional<String> user() {
		return delegate.user();
	}

	public Optional<String> feature() {
		return feature;
	}

}
