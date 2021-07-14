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
import org.eclipse.passage.lic.hc.remote.impl.EObjectFromXmiResponse;
import org.eclipse.passage.lic.hc.remote.impl.Equipment;
import org.eclipse.passage.lic.hc.remote.impl.RemoteRequest;
import org.eclipse.passage.lic.hc.remote.impl.RemoteServiceData;
import org.eclipse.passage.lic.hc.remote.impl.RequestParameters;
import org.eclipse.passage.lic.hc.remote.impl.ResultsTransfered;
import org.eclipse.passage.lic.hc.remote.impl.ServiceAny;
import org.eclipse.passage.lic.hc.remote.impl.RemoteServiceData.OfFeature;
import org.eclipse.passage.lic.internal.licenses.convert.PGrantAcquisition;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;

final class RemoteAcquire<C extends Connection> extends ServiceAny<C, GrantAcquisition, RemoteServiceData.OfFeature> {

	RemoteAcquire(Equipment equipment, Supplier<Client<C, GrantAcquisition>> client, Supplier<Path> source) {
		super(equipment, client, source);
	}

	@Override
	protected RemoteRequest<C> request(OfFeature params, FloatingLicenseAccess access) {
		return new Request(params, access, equipment.hashes());
	}

	@Override
	protected ResponseHandler<GrantAcquisition> handler(FloatingLicenseAccess access) {
		return new Response(//
				new EObjectFromXmiResponse<>(//
						org.eclipse.passage.lic.licenses.model.api.GrantAcqisition.class, //
						equipment));
	}

	private final static class Response implements ResponseHandler<GrantAcquisition> {

		private final ResponseHandler<org.eclipse.passage.lic.licenses.model.api.GrantAcqisition> delegate;

		private Response(ResponseHandler<org.eclipse.passage.lic.licenses.model.api.GrantAcqisition> delegate) {
			this.delegate = delegate;
		}

		@Override
		public GrantAcquisition read(ResultsTransfered results, RequestContext context) throws LicensingException {
			return apiGrant(delegate.read(results, context));
		}

		private GrantAcquisition apiGrant(org.eclipse.passage.lic.licenses.model.api.GrantAcqisition source) {
			return new PGrantAcquisition(source).get();
		}

	}

	private final class Request extends RemoteRequest<C> {

		private final OfFeature data;

		Request(OfFeature data, FloatingLicenseAccess access, HashesRegistry hashes) {
			super(data.product(), access, hashes);
			this.data = data;
		}

		@Override
		public RequestParameters parameters() {
			return new AcquireRequestParameters(data.product(), data.feature(), access, hash);
		}

		@Override
		public Configuration<C> config() {
			return new BaseConfiguration.Get<C>();
		}

	}

}
