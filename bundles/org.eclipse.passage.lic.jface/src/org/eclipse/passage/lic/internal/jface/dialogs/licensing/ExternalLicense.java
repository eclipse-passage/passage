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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;

@SuppressWarnings("restriction")
final class ExternalLicense {

	private final LicensedProduct product;

	ExternalLicense(LicensedProduct product) {
		this.product = product;
	}

	void install(Path license) throws IOException {
		Path target = new PathFromLicensedProduct(new LicensingFolder(new UserHomePath()), product).get()
				.resolve(license.getFileName());
		target.toFile().getParentFile().mkdirs();
		Files.copy(license, target);
	}

}
