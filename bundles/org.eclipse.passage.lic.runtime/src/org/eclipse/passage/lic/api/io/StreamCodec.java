/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.api.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.passage.lic.runtime.conditions.ConditionMiner;

/**
 * Coder and decoder for licensing data, used by {@link ConditionMiner}
 *
 */
public interface StreamCodec {
	
	String getKeyAlgo();

	int getKeySize();

	void createKeyPair(String publicKeyPath, String privateKeyPath, String username, String password)
			throws IOException;

	void encodeStream(InputStream input, OutputStream output, InputStream key, String username, String password)
			throws IOException;

	Object decodeStream(InputStream input, OutputStream output, InputStream key, byte[] digest) throws IOException;

}
