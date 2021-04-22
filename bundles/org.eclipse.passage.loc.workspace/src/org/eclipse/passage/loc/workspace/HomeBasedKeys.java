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
package org.eclipse.passage.loc.workspace;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import org.eclipse.passage.lic.internal.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;
import org.eclipse.passage.loc.internal.api.workspace.Keys;

@SuppressWarnings("restriction")
final class HomeBasedKeys implements Keys {

	private final Path residence;

	HomeBasedKeys() {
		this.residence = new LicensingFolder(new UserHomePath()).get();
	}

	@Override
	public Optional<String> existing(String product, String version) {
		Optional<String> pub = existing(product, version, new PassageFileExtension.PublicKey());
		if (pub.isPresent()) {
			return pub;
		}
		return existing(product, version, new PassageFileExtension.PrivateKey());
	}

	public Optional<String> existing(String product, String version, PassageFileExtension ext) {
		return reportExistance(//
				productResidence(product, version).resolve(//
						productFile(product, version, ext)));
	}

	private Optional<String> reportExistance(Path file) {
		return Files.exists(file) //
				? Optional.of(file.toAbsolutePath().toString()) //
				: Optional.empty();
	}

	private String productFile(String product, String version, PassageFileExtension ext) {
		return new FileNameFromLicensedProduct(product, version, ext).get();
	}

	private Path productResidence(String product, String version) {
		return new PathFromLicensedProduct(() -> residence, product, version).get();
	}

}
