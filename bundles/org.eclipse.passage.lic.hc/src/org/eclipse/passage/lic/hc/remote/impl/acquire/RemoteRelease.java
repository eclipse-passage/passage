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
package org.eclipse.passage.lic.hc.remote.impl.acquire;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.acquire.GrantAcquisition;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.hc.remote.Client;
import org.eclipse.passage.lic.hc.remote.Configuration;
import org.eclipse.passage.lic.hc.remote.Connection;
import org.eclipse.passage.lic.hc.remote.RequestContext;
import org.eclipse.passage.lic.hc.remote.ResponseHandler;
import org.eclipse.passage.lic.hc.remote.impl.BaseConfiguration;
import org.eclipse.passage.lic.hc.remote.impl.Equipment;
import org.eclipse.passage.lic.hc.remote.impl.RemoteRequest;
import org.eclipse.passage.lic.hc.remote.impl.RemoteServiceData;
import org.eclipse.passage.lic.hc.remote.impl.RequestParameters;
import org.eclipse.passage.lic.hc.remote.impl.ResultsTransfered;
import org.eclipse.passage.lic.hc.remote.impl.ServiceAny;
import org.eclipse.passage.lic.internal.emf.EObjectToBytes;
import org.eclipse.passage.lic.internal.licenses.convert.EGrantAcquisition;
import org.eclipse.passage.lic.internal.net.io.SafePayload;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.licenses.model.util.LicensesResourceImpl;

final class RemoteRelease<C extends Connection>
		extends ServiceAny<C, Boolean, RemoteServiceData.WithPayload<GrantAcquisition>> {

	RemoteRelease(Equipment equipment, Supplier<Client<C, Boolean>> client, Supplier<Path> source) {
		super(equipment, client, source);
	}

	@Override
	protected RemoteRequest<C> request(RemoteServiceData.WithPayload<GrantAcquisition> params,
			FloatingLicenseAccess access) {
		return new Request(params, access, equipment.hashes());
	}

	@Override
	protected ResponseHandler<Boolean> handler(FloatingLicenseAccess access) {
		return new ReleaseResponseHandler();
	}

	private static final class ReleaseResponseHandler implements ResponseHandler<Boolean> {

		@Override
		public Boolean read(ResultsTransfered results, RequestContext context) throws LicensingException {
			return results.successful();
		}

	}

	private final class Request extends RemoteRequest<C> {

		private final RemoteServiceData.WithPayload<GrantAcquisition> data;

		Request(RemoteServiceData.WithPayload<GrantAcquisition> data, FloatingLicenseAccess access,
				HashesRegistry hashes) {
			super(data.product(), access, hashes);
			this.data = data;
		}

		@Override
		public Configuration<C> config() throws LicensingException {
			return new BaseConfiguration.Post<C>(encoded(payload()));
		}

		private byte[] encoded(byte[] payload) throws LicensingException {
			return new SafePayload(equipment.keeper(data.product()), equipment.hash(hash)).encode(payload);
		}

		@Override
		public RequestParameters parameters() {
			return new ReleaseRequestParameters(product, data.payload().feature(), access, hash);
		}

		private byte[] payload() throws LicensingException {
			// FIXME:AF: should be done via factory
			return new EObjectToBytes(new EGrantAcquisition(data.payload()).get(), LicensesResourceImpl::new).get();
		}

	}

}