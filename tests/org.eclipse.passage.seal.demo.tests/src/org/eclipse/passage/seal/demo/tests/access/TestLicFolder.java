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
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.seal.demo.tests.access;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;

final class TestLicFolder implements Supplier<Path> {

	@Override
	public Path get() {
		return Paths.get("resource").resolve("lics"); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
