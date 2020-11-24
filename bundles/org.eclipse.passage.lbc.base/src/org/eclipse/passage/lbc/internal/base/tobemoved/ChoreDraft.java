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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Optional;

import org.eclipse.passage.lbc.internal.api.tobemoved.Chore;
import org.eclipse.passage.lbc.internal.api.tobemoved.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.tobemoved.RawRequest;
import org.eclipse.passage.lbc.internal.base.i18n.BaseMessages;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;
import org.eclipse.passage.lic.internal.net.LicenseUser;

abstract class ChoreDraft implements Chore {

	protected final RawRequest data;

	protected ChoreDraft(RawRequest request) {
		this.data = request;
	}

	@Override
	public final FloatingResponse getDone() {
		Optional<LicensedProduct> product;
		try {
			product = product();
		} catch (LicensingException e) {
			return failed(e.getMessage());
		}
		if (!product.isPresent()) {
			return new Failure.BadRequestInvalidProduct();
		}
		Optional<String> user = new LicenseUser(data::parameter).get();
		if (!user.isPresent()) {
			return new Failure.BadRequestNoUser();
		}
		return withProductAndUser(product.get(), user.get());
	}

	protected abstract FloatingResponse withProductAndUser(LicensedProduct product, String user);

	private Optional<LicensedProduct> product() throws LicensingException {
		Optional<String> id = new ProductIdentifier(data::parameter).get();
		Optional<String> version = new ProductVersion(data::parameter).get();
		if (!id.isPresent() || !version.isPresent()) {
			return Optional.empty();
		}
		return Optional.of(new BaseLicensedProduct(decode(id.get()), decode(version.get())));
	}

	protected final String decode(String value) throws LicensingException {
		try {
			return URLDecoder.decode(value, "UTF-8"); //$NON-NLS-1$
		} catch (UnsupportedEncodingException e) {
			throw new LicensingException(String.format(BaseMessages.ChoreDraft_decode_failed, value), e);
		}
	}

	protected final FloatingResponse failed(String details) {
		return new Failure.OperationFailed(getClass().getSimpleName(), details);
	}

}
