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
package org.eclipse.passage.lbc.internal.base.tobemoved;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.passage.lbc.internal.api.tobemoved.FloatingResponse;

public final class PlainSuceess implements FloatingResponse {

	@Override
	public boolean failed() {
		return false;
	}

	@Override
	public Error error() {
		throw new IllegalStateException("Successful result does not have error information"); //$NON-NLS-1$ dev
	}

	@Override
	public void write(OutputStream output) throws IOException {
		throw new IllegalStateException("Plainn successful result is not intended to contain any payload"); //$NON-NLS-1$ dev
	}

}
