/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.net.handle;

import org.eclipse.passage.lic.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;

public final class PlainSuceess implements NetResponse {

	@Override
	public boolean failed() {
		return false;
	}

	@Override
	public boolean carriesPayload() {
		return false;
	}

	@Override
	public Error error() {
		throw new IllegalStateException("Successful result does not have error information"); //$NON-NLS-1$ dev
	}

	@Override
	public byte[] payload() {
		throw new IllegalStateException("Plain successful result is not intended to contain any payload"); //$NON-NLS-1$ dev
	}

	@Override
	public ContentType contentType() {
		return new ContentType.Xml();
	}

}
