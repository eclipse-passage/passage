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

final class SymmetricCode {

	private final byte[] key;

	SymmetricCode(byte[] key) {
		this.key = key;
	}

	byte[] get(byte[] source) {
		int length = source.length;
		byte[] cypher = enoughKey(length);
		byte[] coded = new byte[length];
		for (int i = 0; i < length; i++) {
			coded[i] = (byte) (source[i] ^ cypher[i]);
		}
		return coded;
	}

	private byte[] enoughKey(int length) {
		byte[] elongated = new byte[length];
		for (int i = 0; i < length; i++) {
			elongated[i] = key[i % key.length];
		}
		return elongated;
	}

}
