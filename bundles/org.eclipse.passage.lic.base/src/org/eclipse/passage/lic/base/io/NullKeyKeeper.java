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
package org.eclipse.passage.lic.base.io;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.io.KeyKeeper;

public class NullKeyKeeper implements KeyKeeper {

	public static final NullKeyKeeper INSTANCE = new NullKeyKeeper();

	@Override
	public InputStream openKeyStream(LicensingConfiguration configuration) throws IOException {
		return NullInputStream.INSTANCE;
	}

}
