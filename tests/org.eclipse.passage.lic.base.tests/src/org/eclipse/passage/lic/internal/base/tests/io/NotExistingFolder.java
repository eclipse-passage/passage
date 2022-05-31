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
 *******************************************************************************/package org.eclipse.passage.lic.internal.base.tests.io;

import java.nio.file.Path;
import java.util.function.Supplier;

final class NotExistingFolder implements Supplier<Path> {

	private final Path base;

	NotExistingFolder(Path base) {
		this.base = base;
	}

	@Override
	public Path get() {
		return base.resolve(Long.toHexString(System.currentTimeMillis()));
	}

}
