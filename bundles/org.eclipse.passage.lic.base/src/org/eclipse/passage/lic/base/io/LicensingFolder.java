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
package org.eclipse.passage.lic.base.io;

import java.nio.file.Path;
import java.util.function.Supplier;

/**
 * @since 2.1
 */
public final class LicensingFolder implements Supplier<Path> {

	private final Supplier<Path> base;

	public LicensingFolder(Supplier<Path> base) {
		this.base = base;
	}

	public LicensingFolder(Path base) {
		this(() -> base);
	}

	@Override
	public Path get() {
		return base.get().resolve(".passage"); //$NON-NLS-1$
	}

}
