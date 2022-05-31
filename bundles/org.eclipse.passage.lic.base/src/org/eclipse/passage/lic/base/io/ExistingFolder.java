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
package org.eclipse.passage.lic.base.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.base.Cached;

public final class ExistingFolder implements Supplier<Path> {

	private final Cached<Supplier<Path>, Path> folder;

	public ExistingFolder(Supplier<Path> folder) {
		this.folder = new Cached<>(folder, this::existing);
	}

	@Override
	public Path get() {
		return folder.get();
	}

	private Path existing(Supplier<Path> origin) {
		Path path = origin.get();
		if (!Files.exists(path)) {
			path.toFile().mkdirs(); // do not use nio
		}
		return path;
	}

}
