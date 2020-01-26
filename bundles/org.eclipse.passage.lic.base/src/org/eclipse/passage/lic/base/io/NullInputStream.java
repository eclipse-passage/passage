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
package org.eclipse.passage.lic.base.io;

import java.io.IOException;
import java.io.InputStream;

public class NullInputStream extends InputStream {

	public static final NullInputStream INSTANCE = new NullInputStream();

	@Override
	public int available() throws IOException {
		return 0;
	}

	@Override
	public int read() throws IOException {
		return -1;
	}

	@Override
	public int read(byte[] b) throws IOException {
		return -1;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return -1;
	}

	@Override
	public void close() throws IOException {
	}

}
