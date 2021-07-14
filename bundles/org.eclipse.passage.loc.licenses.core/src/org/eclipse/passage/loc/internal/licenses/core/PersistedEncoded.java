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
package org.eclipse.passage.loc.internal.licenses.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.loc.internal.equinox.OperatorGearAware;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;
import org.eclipse.passage.loc.internal.products.core.ProductKeys;

final class PersistedEncoded {

	private final LicensedProduct product;
	private final Path decrypted;
	private final Function<LicensedProduct, String> password;

	PersistedEncoded(LicensedProduct product, Path decrypted, Function<LicensedProduct, String> password) {
		this.product = product;
		this.decrypted = decrypted;
		this.password = password;
	}

	Path write(String file) throws LicensingException {
		Path encrypted = decrypted.getParent().resolve(file);
		try (FileInputStream input = new FileInputStream(decrypted.toFile());
				FileOutputStream output = new FileOutputStream(encrypted.toFile());
				InputStream key = key()) {
			codec().encode(input, output, key, product.identifier(), password.apply(product));
			return encrypted;
		} catch (IOException e) {
			throw new LicensingException(
					String.format(LicensesCoreMessages.LicenseOperatorServiceImpl_floating_save_encoded_file_error,
							encrypted.toAbsolutePath()),
					e);
		}
	}

	private InputStream key() throws LicensingException {
		return new ProductKeys(product).scrStream();
	}

	private StreamCodec codec() throws LicensingException {
		Optional<StreamCodec> codec = new OperatorGearAware().withGear(gear -> gear.codec(product));
		if (!codec.isPresent()) {
			throw new LicensingException(
					String.format(LicensesCoreMessages.LicenseOperatorServiceImpl_w_no_encoding, decrypted));
		}
		return codec.get();
	}
}
