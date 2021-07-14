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
package org.eclipse.passage.lic.base.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.KeyKeeper;

/**
 * @since 2.1
 */
public final class KeyContent {

	private final KeyKeeper keeper;

	public KeyContent(KeyKeeper keeper) {
		this.keeper = keeper;
	}

	public byte[] get() throws LicensingException {
		try (InputStream in = keeper.productPublicKey(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			int acquired = -1;
			byte[] buffer = new byte[1024];
			while ((acquired = in.read(buffer)) != -1) {
				out.write(buffer, 0, acquired);
			}
			return out.toByteArray();
		} catch (IOException e) {
			throw new LicensingException("Failed to read public key ", e); //$NON-NLS-1$
		}
	}

}
