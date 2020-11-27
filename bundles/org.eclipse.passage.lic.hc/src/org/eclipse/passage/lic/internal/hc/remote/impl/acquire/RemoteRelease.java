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

import java.net.HttpURLConnection;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.floating.model.convert.EGrantAcquisition;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.emf.EObjectToBytes;
import org.eclipse.passage.lic.internal.hc.remote.Configuration;
import org.eclipse.passage.lic.internal.hc.remote.ResponseHandler;
import org.eclipse.passage.lic.internal.hc.remote.impl.BaseConfiguration;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteRequest;
import org.eclipse.passage.lic.internal.hc.remote.impl.RequestParameters;
import org.eclipse.passage.lic.internal.hc.remote.impl.ResultsTransfered;
import org.eclipse.passage.lic.internal.hc.remote.impl.ServiceAny;

/**
 * FIXME: release is a 'post' request with XML Input and without Output. Both
 * RemoteRequest and ResponseHandler interfaces must be revised for the purpose
 */
final class RemoteRelease extends ServiceAny<Boolean, ReleaseServiceData> {

	RemoteRelease(KeyKeeperRegistry keys, StreamCodecRegistry codecs) {
		super(keys, codecs);
	}

	@Override
	protected RemoteRequest request(ReleaseServiceData params, FloatingLicenseAccess access) {
		return new Request(params, access);
	}

	@Override
	protected ResponseHandler<Boolean> handler(FloatingLicenseAccess access) {
		return new ReleaseResponseHandler();
	}

	private static final class ReleaseResponseHandler implements ResponseHandler<Boolean> {

		@Override
		public Boolean read(ResultsTransfered results) throws LicensingException {
			return results.successful();
		}

	}

	private final class Request extends RemoteRequest {

		private final ReleaseServiceData data;

		Request(ReleaseServiceData data, FloatingLicenseAccess access) {
			super(data.product(), access);
			this.data = data;
		}

		@Override
		public Configuration<HttpURLConnection> config() throws LicensingException {
			return new BaseConfiguration.Post(payload());
		}

		@Override
		protected RequestParameters parameters() {
			return new ReleaseRequestParameters(product, data.acqisition().feature(), access);
		}

		private byte[] payload() throws LicensingException {
			return new EObjectToBytes(new EGrantAcquisition(data.acqisition()).get()).get();
		}

	}

}