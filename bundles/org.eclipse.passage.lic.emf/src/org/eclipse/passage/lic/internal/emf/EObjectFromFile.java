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
package org.eclipse.passage.lic.internal.emf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public class EObjectFromFile<T extends EObject> extends EObjectFromStream<T> {

	private final File file;

	public EObjectFromFile(Path path, Class<T> expected, Supplier<Resource> factory) {
		this(path.toFile(), expected, factory);
	}

	public EObjectFromFile(File file, Class<T> expected, Supplier<Resource> factory) {
		super(expected, factory);
		Objects.requireNonNull(file, "EObjectFromFile::file"); //$NON-NLS-1$
		this.file = file;

	}

	@Override
	protected InputStream stream() throws IOException {
		return new FileInputStream(file);
	}

}
