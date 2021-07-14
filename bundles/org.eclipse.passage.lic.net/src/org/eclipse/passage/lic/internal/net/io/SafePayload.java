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
package org.eclipse.passage.lic.internal.net.io;

import java.util.Base64;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.base.io.KeyContent;

public final class SafePayload {

	private final Hashes hashes;
	private final KeyKeeper keeper;

	public SafePayload(KeyKeeper keeper, Hashes hashes) {
		this.keeper = keeper;
		this.hashes = hashes;
	}

	public byte[] encode(byte[] raw) throws LicensingException {
		return transportable(coded(raw));
	}

	public byte[] decode(byte[] raw) throws LicensingException {
		return coded(transported(raw));
	}

	private byte[] transportable(byte[] content) {
		return Base64.getEncoder().encode(content);
	}

	private byte[] transported(byte[] content) {
		return Base64.getDecoder().decode(content);
	}

	private byte[] coded(byte[] source) throws LicensingException {
		return new SymmetricCode(key()).get(source);
	}

	private byte[] key() throws LicensingException {
		return hashes.get(new KeyContent(keeper).get());
	}

}
