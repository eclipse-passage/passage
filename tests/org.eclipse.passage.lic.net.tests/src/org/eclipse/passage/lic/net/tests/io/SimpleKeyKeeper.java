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

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.base.io.FileKeyKeeper;

final class SimpleKeyKeeper extends TestKeyKeeper {

	@Override
	public KeyKeeper get() throws LicensingException, IOException {
		return new FileKeyKeeper(publicKey());
	}

}
