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
package org.eclipse.passage.lic.equinox.access;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.base.io.ExternalLicense;

final class OptionImport extends BaseOption<CoverageCheckOptionDecision> {

	private final LicensedProduct product;

	OptionImport(Interaction.Smart interaction, LicensedProduct product) {
		super('i', //
				"Import", //$NON-NLS-1$
				"Import a license for this application", //$NON-NLS-1$
				interaction);
		this.product = product;
	}

	@Override
	public CoverageCheckOptionDecision run() {
		interaction.head("import license"); //$NON-NLS-1$
		interaction.prompt("Please enter the path to a license file"); //$NON-NLS-1$
		readPath().ifPresent(this::install);
		return CoverageCheckOptionDecision.reassess;
	}

	private Optional<Path> readPath() {
		String path = interaction.input().trim();
		Path file = Paths.get(path);
		if (!Files.exists(file)) {
			interaction.prompt(String.format("Path [%s] does not exist", file)); //$NON-NLS-1$
			return Optional.empty();
		}
		if (!Files.isRegularFile(file)) {
			interaction.prompt(String.format("Path [%s] does not point to a file", file)); //$NON-NLS-1$
			return Optional.empty();
		}
		return Optional.of(file);
	}

	private void install(Path file) {
		try {
			Path installed = new ExternalLicense(product).install(file);
			interaction.prompt(String.format("License [%s] has been successfully installed in [%s]", file, installed)); //$NON-NLS-1$
		} catch (IOException e) {
			interaction.swear(e);
		}

	}

}
