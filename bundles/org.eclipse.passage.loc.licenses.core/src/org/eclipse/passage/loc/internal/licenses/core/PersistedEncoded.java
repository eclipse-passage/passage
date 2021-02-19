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
package org.eclipse.passage.loc.internal.licenses.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.io.StreamCodec;
import org.eclipse.passage.lic.internal.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.UserHomeProductResidence;
import org.eclipse.passage.loc.internal.equinox.OperatorGearAware;
import org.eclipse.passage.loc.internal.licenses.core.i18n.LicensesCoreMessages;

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
				FileInputStream key = new FileInputStream(key().toFile())) {
			codec().encode(input, output, key, product.identifier(), password.apply(product));
			return encrypted;
		} catch (IOException e) {
			throw new LicensingException(
					String.format(LicensesCoreMessages.LicenseOperatorServiceImpl_floating_save_encoded_file_error,
							encrypted.toAbsolutePath()),
					e);
		}
	}

	private Path key() throws LicensingException {
		Path key = new UserHomeProductResidence(product).get()
				.resolve(new FileNameFromLicensedProduct(product, new PassageFileExtension.PrivateKey()).get());
		if (!Files.exists(key)) {
			throw new LicensingException(String.format(//
					LicensesCoreMessages.LicenseOperatorServiceImpl_private_key_not_found, key.toAbsolutePath()));
		}
		return key;

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
