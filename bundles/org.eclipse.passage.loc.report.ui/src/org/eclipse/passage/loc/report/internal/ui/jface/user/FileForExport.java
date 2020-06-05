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
 *      ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.report.internal.ui.jface.user;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.osgi.util.NLS;

final class FileForExport implements Supplier<Path> {

	private final Path parent;

	public FileForExport(Path parent) {
		this.parent = parent;
	}

	@Override
	public Path get() {
		return parent.resolve(//
				NLS.bind(//
						"users-{0}.csv", //$NON-NLS-1$
						Long.toHexString(System.currentTimeMillis())//
				));
	}

}
