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
package org.eclipse.passage.loc.workspace;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.eclipse.passage.lic.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.base.io.LicensingFolder;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.eclipse.passage.lic.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.base.io.UserHomePath;
import org.eclipse.passage.loc.internal.api.workspace.Keys;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;

final class HomeBasedKeys implements Keys {

	private final Path residence;
	private final PassageFileExtension keys = new DomainFileExtension.Keys();

	HomeBasedKeys() {
		this.residence = new LicensingFolder(new UserHomePath()).get();
	}

	@Override
	public Optional<String> existing(String product, String version) {
		return reportExistance(residentFile(product, version, keys));
	}

	@Override
	public ResourceHandle located(String product, String version) {
		return new LocalFileHandle(xmi, residentFile(product, version, keys));
	}

	@Override
	public ResourceHandle locatedPub(String product, String version) {
		return new LocalFileHandle(pub, residentFile(product, version, new PassageFileExtension.PublicKey()));
	}

	private Path residentFile(String product, String version, PassageFileExtension ext) {
		return productResidence(product, version).resolve(//
				fileForProduct(product, version, ext));
	}

	private Optional<String> reportExistance(Path file) {
		return Files.exists(file) //
				? Optional.of(file.toAbsolutePath().toString()) //
				: Optional.empty();
	}

	private String fileForProduct(String product, String version, PassageFileExtension ext) {
		return new FileNameFromLicensedProduct(product, version, ext).get();
	}

	private Path productResidence(String product, String version) {
		return new PathFromLicensedProduct(() -> residence, product, version).get();
	}

}
