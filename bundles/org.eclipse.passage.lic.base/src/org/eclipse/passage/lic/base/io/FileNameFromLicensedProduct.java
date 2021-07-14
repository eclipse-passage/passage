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
package org.eclipse.passage.lic.base.io;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.base.BaseLicensedProduct;

/**
 * @since 2.1
 */
public final class FileNameFromLicensedProduct implements Supplier<String> {

	private final LicensedProduct product;
	private final PassageFileExtension extension;

	public FileNameFromLicensedProduct(String product, String version, PassageFileExtension extension) {
		this(new BaseLicensedProduct(product, version), extension);
	}

	public FileNameFromLicensedProduct(LicensedProduct product, PassageFileExtension extension) {
		Objects.requireNonNull(product, "FileNameFromLicensedProduct::product"); //$NON-NLS-1$
		Objects.requireNonNull(extension, "FileNameFromLicensedProduct::extension"); //$NON-NLS-1$
		this.product = product;
		this.extension = extension;
	}

	public FileNameFromLicensedProduct(LicensedProduct product, Supplier<String> extension) {
		this(product, new PassageFileExtension.Of(extension));
	}

	@Override
	public String get() {
		return String.format("%s_%s%s", //$NON-NLS-1$
				product.identifier(), //
				product.version(), //
				extension.get());
	}

}
