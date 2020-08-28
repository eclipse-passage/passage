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
package org.eclipse.passage.lbc.internal.base.persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;

/**
 * @since 1.0
 */
public final class LockFolder implements Supplier<Path> {

	private final Supplier<Path> base;

	public LockFolder(Supplier<Path> base) {
		Objects.requireNonNull(base, "LockFolder::base"); //$NON-NLS-1$
		this.base = base;
	}

	public LockFolder() {
		this(new LicensingFolder(new UserHomePath()));
	}

	@Override
	public Path get() {
		return base.get().resolve("locked"); //$NON-NLS-1$
	}

	public boolean exists() {
		return Files.exists(get());
	}

	public void create() throws IOException {
		Files.createDirectories(get());
	}

}
