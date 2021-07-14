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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.registry.StringServiceId;

/**
 * @since 2.1
 */
public final class MD5Hashes implements Hashes {

	private final String algorithm = "MD5"; //$NON-NLS-1$

	@Override
	public StringServiceId id() {
		return new StringServiceId(algorithm);
	}

	@Override
	public byte[] get(byte[] source) throws LicensingException {
		try {
			return MessageDigest.getInstance(algorithm).digest(source);
		} catch (NoSuchAlgorithmException e) {
			throw new LicensingException("Failed to build a hash ", e); //$NON-NLS-1$
		}
	}

}
