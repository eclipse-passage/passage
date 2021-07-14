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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.internal.emf.EObjectToBytes;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.io.SafePayload;

public final class EObjectTransfer implements NetResponse {

	private final EObject payload;
	private final KeyKeeper key;
	private final Hashes hashes;

	public EObjectTransfer(EObject payload, KeyKeeper key, Hashes hashes) {
		this.payload = payload;
		this.key = key;
		this.hashes = hashes;
	}

	@Override
	public boolean failed() {
		return false;
	}

	@Override
	public boolean carriesPayload() {
		return true;
	}

	@Override
	public Error error() {
		throw new IllegalStateException("Successful response does not possess error information"); //$NON-NLS-1$ // dev
	}

	@Override
	public byte[] payload() throws LicensingException {
		// FIXME:AF: should be done via factory
		return encode(new EObjectToBytes(payload, XMIResourceImpl::new).get());
	}

	@Override
	public ContentType contentType() {
		return new ContentType.Xml();
	}

	private byte[] encode(byte[] plain) throws LicensingException {
		return new SafePayload(key, hashes).encode(plain);
	}

}
