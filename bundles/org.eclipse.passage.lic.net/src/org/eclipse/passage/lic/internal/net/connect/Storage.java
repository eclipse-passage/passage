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
package org.eclipse.passage.lic.internal.net.connect;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.eclipse.passage.lic.base.io.LicensingFolder;
import org.eclipse.passage.lic.base.io.UserHomePath;

/**
 * @since 1.1
 */
public final class Storage extends CliParameter<Path> {

	public Storage() {
		super(new LicensingFolder(new UserHomePath()).get());
	}

	public Storage(Path lazy) {
		super(lazy);
	}

	public Storage(String[] sources, Path lazy) {
		super(sources, lazy);
	}

	@Override
	public String key() {
		return "server.storage"; //$NON-NLS-1$
	}

	@Override
	protected Optional<Path> parse(String value) {
		Path path = Paths.get(value);
		if (!Files.exists(path)) {
			boolean created = path.toFile().mkdirs();
			if (!created) {
				log.error(String.format("Failed to create absent license storage directory %s", path.toAbsolutePath())); //$NON-NLS-1$ ;
				return Optional.empty();
			}
		}
		if (!Files.isDirectory(path)) {
			log.error(String.format("License storage must be a directory: %s ", path.toAbsolutePath())); //$NON-NLS-1$ ;
			return Optional.empty();
		}
		if (!Files.isExecutable(path)) {
			log.error(String.format("Lack of access rights - directory is not executable: %s ", path.toAbsolutePath())); //$NON-NLS-1$ ;
			return Optional.empty();
		}
		return Optional.of(path);
	}

}
