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
package org.eclipse.passage.lic.internal.hc.remote.impl.acquire;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.hc.remote.ResponseHandler;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteRequest;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData.OfFeature;
import org.eclipse.passage.lic.internal.hc.remote.impl.ServiceAny;

/**
 * FIXME: release is a 'post' request with XML Input and without Output. Both
 * RemoteRequest and ResponseHandler interfaces must be revised for the purpose
 */
final class RemoteRelease extends ServiceAny<Boolean, RemoteServiceData.OfFeature> {

	RemoteRelease(KeyKeeperRegistry keys, StreamCodecRegistry codecs) {
		super(keys, codecs);
	}

	@Override
	protected RemoteRequest request(OfFeature params, FloatingLicenseAccess access) {
		return null; // YTBD
	}

	@Override
	protected ResponseHandler<Boolean> handler(FloatingLicenseAccess access) {
		return new ReleaseResponseHandler();
	}

	private static final class ReleaseResponseHandler implements ResponseHandler<Boolean> {

		@Override
		public Boolean read(byte[] raw, String contentType) throws LicensingException {
			return null;
		}

	}

}