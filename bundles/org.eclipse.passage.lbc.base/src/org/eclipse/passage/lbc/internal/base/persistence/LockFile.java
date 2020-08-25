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

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.internal.api.persistence.BoundLicense;
import org.eclipse.passage.lic.internal.api.conditions.Condition;

/**
 * @since 1.0
 */
public final class LockFile implements Supplier<Path> {

	private final Supplier<Path> folder;
	private final String identifier;

	private LockFile(Supplier<Path> folder, String identifier) {
		Objects.requireNonNull(folder, "LockFile::folder"); //$NON-NLS-1$
		Objects.requireNonNull(identifier, "LockFile::identifier"); //$NON-NLS-1$
		this.folder = folder;
		this.identifier = identifier;
	}

	public LockFile(Supplier<Path> folder, BoundLicense license) {
		this(folder, license.identifier().get().get());
	}

	public LockFile(Supplier<Path> folder, Condition license) {
		this(folder, license.identifier());
	}

	public LockFile(BoundLicense license) {
		this(new LockFolder(), license);
	}

	public LockFile(Condition license) {
		this(new LockFolder(), license);
	}

	@Override
	public Path get() {
		return folder.get().resolve(identifier);
	}

}
