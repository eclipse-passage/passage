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
package org.eclipse.passage.loc.report.internal.ui.jface;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.osgi.util.NLS;

public final class FileForExport implements Supplier<Path> {

	private final Path parent;
	private final String dedication;

	public FileForExport(Path parent, String dedication) {
		this.parent = parent;
		this.dedication = dedication;
	}

	@Override
	public Path get() {
		return parent.resolve(//
				NLS.bind(//
						"{0}-{1}.csv", //$NON-NLS-1$
						dedication, //
						Long.toHexString(System.currentTimeMillis())//
				));
	}

}
