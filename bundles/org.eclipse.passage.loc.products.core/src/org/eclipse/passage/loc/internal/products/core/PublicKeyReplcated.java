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
package org.eclipse.passage.loc.internal.products.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.base.io.PassageFileExtension;

public final class PublicKeyReplcated {

	private final LicensedProduct product;
	public final Path folder;

	public PublicKeyReplcated(LicensedProduct product, Path folder) {
		this.product = product;
		this.folder = folder;
	}

	public Path store() throws IOException, LicensingException {
		Path destination = folder
				.resolve(new FileNameFromLicensedProduct(product, new PassageFileExtension.PublicKey()).get());
		if (Files.exists(destination)) {
			return destination;
		}
		Files.write(destination, new ProductKeys(product).pubBytes(), StandardOpenOption.CREATE_NEW);
		return destination;
	}

}
