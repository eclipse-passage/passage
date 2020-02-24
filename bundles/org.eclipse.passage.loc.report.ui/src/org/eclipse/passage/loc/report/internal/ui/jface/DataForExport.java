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

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Supplier;

final class DataForExport {

	private final Supplier<Set<String>> products;
	private final Supplier<Path> target;
	private final Supplier<Boolean> open;

	DataForExport(//
			Supplier<Set<String>> products, //
			Supplier<Path> target, //
			Supplier<Boolean> open) {
		this.products = products;
		this.target = target;
		this.open = open;
	}

	boolean complete() {
		Path path = target.get();
		return (!products.get().isEmpty()) //
				&& Files.exists(path) //
				&& Files.isDirectory(path);
	}

	Path target() {
		return target.get();
	}

	Set<String> products() {
		return products.get();
	}

	boolean open() {
		return open.get();
	}
}
