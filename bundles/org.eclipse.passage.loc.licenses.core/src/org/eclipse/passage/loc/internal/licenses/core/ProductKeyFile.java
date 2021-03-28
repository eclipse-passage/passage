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
package org.eclipse.passage.loc.internal.licenses.core;

import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.UserHomeProductResidence;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;

final class ProductKeyFile {

	private final LicensedProduct product;

	ProductKeyFile(LicensedProduct product) {
		this.product = product;
	}

	Path scr() throws LicensingException {
		return get(//
				new PassageFileExtension.PrivateKey(), //
				LicensesCoreMessages.LicenseOperatorServiceImpl_private_key_not_found);
	}

	Path pub() throws LicensingException {
		return get(//
				new PassageFileExtension.PublicKey(), //
				LicensesCoreMessages.LicenseOperatorServiceImpl_public_key_not_found);
	}

	private Path get(PassageFileExtension ext, String error) throws LicensingException {
		Path key = new UserHomeProductResidence(product).get()
				.resolve(new FileNameFromLicensedProduct(product, ext).get());
		if (!Files.exists(key)) {
			throw new LicensingException(String.format(error, key.toAbsolutePath()));
		}
		return key;
	}

}
