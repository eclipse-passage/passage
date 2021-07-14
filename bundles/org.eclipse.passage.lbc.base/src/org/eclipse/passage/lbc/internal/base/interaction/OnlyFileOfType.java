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
package org.eclipse.passage.lbc.internal.base.interaction;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.LicensingException;

final class OnlyFileOfType {

	private final Path folder;
	private final String extension;

	public OnlyFileOfType(Path folder, String extension) {
		this.folder = folder;
		this.extension = extension;
	}

	Path get() throws Exception {
		List<Path> keys = Files.find(folder, 1, this::ofType).collect(Collectors.toList());
		if (keys.size() != 1) {
			throw new LicensingException(String.format("%s is expected to contain exactly one file of type %s", //$NON-NLS-1$
					folder.toString(), extension));
		}
		return keys.get(0);
	}

	private boolean ofType(Path file, @SuppressWarnings("unused") BasicFileAttributes any) {
		return file.getFileName().toString().endsWith(extension);
	}

}
