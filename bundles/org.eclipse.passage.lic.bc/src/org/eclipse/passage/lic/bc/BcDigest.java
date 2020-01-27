/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.bc;

import org.bouncycastle.crypto.digests.SHA512Digest;

public final class BcDigest {

	private BcDigest() {
		// block
	}

	public static byte[] calculateDigest(byte[] source) {
		final SHA512Digest dig = new SHA512Digest();
		dig.reset();
		dig.update(source, 0, source.length);
		final byte[] digest = new byte[dig.getDigestSize()];
		dig.doFinal(digest, 0);
		return digest;
	}

}
