/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
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
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.base.i18n.AccessCycleMessages;

/**
 * @since 2.1
 */
public final class PathKeyKeeper implements KeyKeeper {

	private final LicensedProduct product;
	private final Supplier<Path> base;

	public PathKeyKeeper(LicensedProduct product, Supplier<Path> base) {
		Objects.requireNonNull(product, "PathKeyKeeper::product"); //$NON-NLS-1$
		Objects.requireNonNull(base, "PathKeyKeeper::base"); //$NON-NLS-1$
		this.product = product;
		this.base = new PathFromLicensedProduct(base, product);
	}

	@Override
	public LicensedProduct id() {
		return product;
	}

	@Override
	public InputStream productPublicKey() throws LicensingException {
		Path path = base.get().resolve(keyFile());
		try {
			return Files.newInputStream(path);
		} catch (Exception e) {
			throw new LicensingException(//
					String.format(//
							AccessCycleMessages.getString("PathKeyKeeper_input_stream_error"), //$NON-NLS-1$
							base.get().toAbsolutePath()), //
					e);
		}
	}

	private String keyFile() {
		return new FileNameFromLicensedProduct(product, new PassageFileExtension.PublicKey()).get();
	}

}
