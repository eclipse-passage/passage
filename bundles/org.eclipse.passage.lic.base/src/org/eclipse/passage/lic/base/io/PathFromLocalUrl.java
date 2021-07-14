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
package org.eclipse.passage.lic.base.io;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.function.Supplier;

/**
 * @since 2.1
 */
public final class PathFromLocalUrl implements Supplier<Path> {

	private final URL url;

	public PathFromLocalUrl(URL url) {
		this.url = url;
	}

	@Override
	public Path get() {
		return new File(url.getPath()).toPath();
	}

}
