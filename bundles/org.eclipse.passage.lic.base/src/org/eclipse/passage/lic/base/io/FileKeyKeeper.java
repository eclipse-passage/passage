/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
package org.eclipse.passage.lic.base.io;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.KeyKeeper;

/**
 * @since 2.1
 */
public final class FileKeyKeeper implements KeyKeeper {

	private final Path key;

	public FileKeyKeeper(Path key) throws LicensingException {
		Objects.requireNonNull(key, "FileKeyKeeper::key"); //$NON-NLS-1$
		this.key = key;
		validateKeyFile();
	}

	@Override
	public LicensedProduct id() {
		throw new UnsupportedOperationException(
				"Reads the only key from the given location, product is unknown. Is not intended to be used."); //$NON-NLS-1$
	}

	@Override
	public InputStream productPublicKey() throws LicensingException {
		try {
			return Files.newInputStream(key);
		} catch (Exception e) {
			throw new LicensingException(e);
		}
	}

	private void validateKeyFile() throws LicensingException {
		if (!Files.exists(key)) {
			throw new IllegalArgumentException(String.format("%s is expected to exist", key.toString())); //$NON-NLS-1$
		}
		if (!Files.isRegularFile(key)) {
			throw new IllegalArgumentException(String.format("%s is expected to be a regular file", key.toString())); //$NON-NLS-1$
		}
		if (!Files.isReadable(key)) {
			throw new IllegalArgumentException(
					String.format("Lack of access rights: %s cannot be read", key.toString())); //$NON-NLS-1$
		}
		if (!key.getFileName().toString().endsWith(new PassageFileExtension.PublicKey().get())) {
			throw new IllegalArgumentException(String.format("%s has incorrect format ", key.toString())); //$NON-NLS-1$
		}
	}

}
