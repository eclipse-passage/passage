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
package org.eclipse.passage.lic.equinox;

import java.net.URL;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.core.runtime.FileLocator;
import org.osgi.framework.Bundle;

/**
 * @since 2.1
 */
public final class BundleResource implements Supplier<Optional<URL>> {
	private final Bundle bundle;
	private final Path path;

	public BundleResource(Bundle bundle, Path path) {
		this.bundle = bundle;
		this.path = path;
	}

	@Override
	public Optional<URL> get() {
		return Optional.ofNullable(FileLocator.find(bundle, new org.eclipse.core.runtime.Path(path.toString())));
	}

}
