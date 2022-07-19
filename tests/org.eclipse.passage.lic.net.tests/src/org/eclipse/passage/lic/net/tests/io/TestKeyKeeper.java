/*******************************************************************************
 * Copyright (c) 2022 IILS mbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IILS mbH (Hannes Wellmann) - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.net.tests.io;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.KeyKeeper;

abstract class TestKeyKeeper {

	protected abstract KeyKeeper get() throws LicensingException, IOException;

	protected final Path publicKey() {
		return Paths.get("resource").resolve("io").resolve("key.pub"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
}
