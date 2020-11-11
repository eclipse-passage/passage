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
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData.OfFeature;

final class RemoteAcquire extends SignRequestService {

	RemoteAcquire(KeyKeeperRegistry keys, StreamCodecRegistry codecs) {
		super(keys, codecs);
	}

	@Override
	protected RemoteRequest request(OfFeature params, FloatingLicenseAccess access) {
		return new OfFeatureRequest(params, access, //
				(data, server) -> new AcquireRequestParameters(data.product(), data.feature(), server));
	}

	@Override
	protected ResponseHandler<Boolean> handler() {
		return new Response();
	}

	private static final class Response implements ResponseHandler<Boolean> {

		@Override
		public Boolean read(byte[] raw, String contentType) throws LicensingException {
			// TODO parse the bytes to E-'acquire response'-Object and read boolean result
			return null;
		}

	}

}
