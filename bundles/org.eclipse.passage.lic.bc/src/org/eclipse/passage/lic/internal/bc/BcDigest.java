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
package org.eclipse.passage.lic.internal.bc;

import java.util.function.Supplier;

import org.bouncycastle.crypto.digests.SHA512Digest;

public final class BcDigest implements Supplier<byte[]> {

	private final byte[] source;

	public BcDigest(byte[] source) {
		this.source = source;
	}

	@Override
	public byte[] get() {
		SHA512Digest digest = new SHA512Digest();
		digest.reset();
		digest.update(source, 0, source.length);
		byte[] data = new byte[digest.getDigestSize()];
		digest.doFinal(data, 0);
		return data;
	}

}
