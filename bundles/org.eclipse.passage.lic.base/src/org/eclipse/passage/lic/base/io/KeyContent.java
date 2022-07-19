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
 *     IILS mbH (Hannes Wellmann) - Harden KeyContent against different line-delimiters
 *******************************************************************************/
package org.eclipse.passage.lic.base.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

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
		try (InputStream in = keeper.productPublicKey();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.US_ASCII))) {
			return reader.lines() //
					.collect(Collectors.joining()) // filter out line-delimiters
					.getBytes(StandardCharsets.US_ASCII);
		} catch (IOException e) {
			throw new LicensingException("Failed to read public key ", e); //$NON-NLS-1$
		}
	}

}
