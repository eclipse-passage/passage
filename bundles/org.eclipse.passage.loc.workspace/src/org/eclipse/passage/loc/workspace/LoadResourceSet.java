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
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Platform;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;
import org.eclipse.passage.loc.internal.api.workspace.ResourceType;

public final class LoadResourceSet {

	private final Supplier<Path> base;
	private final String domain;
	private final ResourceType type;

	public LoadResourceSet(ResourceType type, Supplier<Path> path, String domain) {
		this.base = Objects.requireNonNull(path);
		this.domain = Objects.requireNonNull(domain);
		this.type = Objects.requireNonNull(type);
	}

	public List<ResourceHandle> load() {
		try {
			Path parent = base.get();
			Files.createDirectories(parent);
			Path path = parent.resolve(domain);
			if (!Files.exists(path)) {
				// FIXME: AF: log?
				return Collections.emptyList();
			}
			return Files.readAllLines(path).stream()//
					.map(Paths::get)//
					.map(p -> new LocalFileHandle(type, path))//
					.collect(Collectors.toList());
		} catch (Exception e) {
			Platform.getLog(getClass()).error(e.getMessage(), e);
		}
		return Collections.emptyList();
	}

}
