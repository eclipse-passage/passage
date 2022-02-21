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
package org.eclipse.passage.loc.workspace;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.core.runtime.Platform;

public final class StoreResourceSet {

	private final Supplier<Path> base;
	private final String domain;

	public StoreResourceSet(Supplier<Path> path, String domain) {
		this.base = Objects.requireNonNull(path);
		this.domain = Objects.requireNonNull(domain);
	}

	public void store(List<String> locations) {
		try {
			Path parent = base.get();
			Files.createDirectories(parent);
			Path path = parent.resolve(domain);
			if (!Files.exists(path)) {
				Files.createFile(path);
			}
			Files.write(path, locations);
		} catch (Exception e) {
			Platform.getLog(getClass()).error(e.getMessage(), e);
		}
	}

}
