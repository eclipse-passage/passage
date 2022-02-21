/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;
import org.eclipse.passage.loc.internal.api.workspace.ResourceType;

/**
 * temporary
 * 
 */
class LocalFileHandle implements ResourceHandle {

	private final ResourceType type;
	private final Path path;

	public LocalFileHandle(ResourceType type, Path path) {
		this.type = Objects.requireNonNull(type);
		this.path = Objects.requireNonNull(path);
	}

	@Override
	public String info() {
		return path.toAbsolutePath().toString();
	}

	@Override
	public void write(byte[] content) throws Exception {
		File file = path.toFile();
		file.getParentFile().mkdirs();
		try (OutputStream out = new FileOutputStream(file)) {
			out.write(content);
			out.flush();
		}
	}

	@Override
	public byte[] content() throws Exception {
		try (FileInputStream stream = new FileInputStream(path.toFile())) {
			return stream.readAllBytes();
		}
	}

	@Override
	public ResourceType type() {
		return type;
	}

	@Override
	public String uri() {
		return URI.createFileURI(path.toFile().toString()).toString();
	}

}
