/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.base.io.PassageFileExtension;

final class AllLicensesFromFolder implements Supplier<List<Path>> {

	private final Optional<String> folder;

	AllLicensesFromFolder(String folder) {
		this(Optional.ofNullable(folder));
	}

	AllLicensesFromFolder(Optional<String> folder) {
		this.folder = folder;
	}

	@Override
	public List<Path> get() {
		if (!folder.isPresent()) {
			return Collections.emptyList();
		}
		return Arrays.stream(licenses())//
				.map(File::toPath)//
				.collect(Collectors.toList());
	}

	private File[] licenses() {
		File host = new File(folder.get());
		if (!host.isDirectory()) {
			return new File[0];
		}
		return host.listFiles(licen());
	}

	private FileFilter licen() {
		return new FileFilter() {
			private final PassageFileExtension licen = new PassageFileExtension.LicenseEncrypted();

			@Override
			public boolean accept(File file) {
				return licen.ends(file);
			}
		};
	}

}
