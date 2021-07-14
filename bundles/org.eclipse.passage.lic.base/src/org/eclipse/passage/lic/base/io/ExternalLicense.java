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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.eclipse.passage.lic.api.LicensedProduct;

/**
 * @since 2.1
 */
public final class ExternalLicense {

	private final Path destination;

	public ExternalLicense(LicensedProduct product) {
		this(new PathFromLicensedProduct(new LicensingFolder(new UserHomePath()), product).get());
	}

	public ExternalLicense(Path base, LicensedProduct product) {
		this(new PathFromLicensedProduct(() -> base, product).get());
	}

	public ExternalLicense(Path destination) {
		this.destination = destination;
	}

	public Path install(Path... pack) throws IOException {
		for (Path file : pack) {
			installLicenseFile(file);
		}
		return destination;
	}

	private void installLicenseFile(Path file) throws IOException {
		Path target = destination.resolve(file.getFileName());
		target.toFile().getParentFile().mkdirs();
		Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
	}

}
