/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.equinox.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.osgi.framework.Bundle;

/**
 * @since 2.1
 */
public final class FileFromBundle {

	private final Supplier<Bundle> bundle;
	private final String path;

	public FileFromBundle(Supplier<Bundle> bundle, String path) {
		this.bundle = bundle;
		this.path = path;
	}

	public FileFromBundle(Bundle bundle, String path) {
		this(() -> bundle, path);
	}

	public InputStream get() throws IOException {
		return FileLocator.find(bundle.get(), new Path(path), null).openStream();
	}

}
