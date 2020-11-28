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

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.floating.model.convert.PGrantAcquisition;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.acquire.GrantAcqisition;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.hc.remote.Client;
import org.eclipse.passage.lic.internal.hc.remote.Configuration;
import org.eclipse.passage.lic.internal.hc.remote.Connection;
import org.eclipse.passage.lic.internal.hc.remote.ResponseHandler;
import org.eclipse.passage.lic.internal.hc.remote.impl.BaseConfiguration;
import org.eclipse.passage.lic.internal.hc.remote.impl.EObjectFromXmiResponse;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteRequest;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData.OfFeature;
import org.eclipse.passage.lic.internal.hc.remote.impl.RequestParameters;
import org.eclipse.passage.lic.internal.hc.remote.impl.ResultsTransfered;
import org.eclipse.passage.lic.internal.hc.remote.impl.ServiceAny;

final class RemoteAcquire<C extends Connection> extends ServiceAny<C, GrantAcqisition, RemoteServiceData.OfFeature> {

	RemoteAcquire(KeyKeeperRegistry keys, StreamCodecRegistry codecs, Supplier<Client<C, GrantAcqisition>> client,
			Supplier<Path> source) {
		super(keys, codecs, client, source);
	}

	@Override
	protected RemoteRequest<C> request(OfFeature params, FloatingLicenseAccess access) {
		return new Request(params, access);
	}

	@Override
	protected ResponseHandler<GrantAcqisition> handler(FloatingLicenseAccess access) {
		return new Response(
				new EObjectFromXmiResponse<>(org.eclipse.passage.lic.floating.model.api.GrantAcqisition.class));
	}

	private final static class Response implements ResponseHandler<GrantAcqisition> {

		private final ResponseHandler<org.eclipse.passage.lic.floating.model.api.GrantAcqisition> delegate;

		private Response(ResponseHandler<org.eclipse.passage.lic.floating.model.api.GrantAcqisition> delegate) {
			this.delegate = delegate;
		}

		@Override
		public GrantAcqisition read(ResultsTransfered results) throws LicensingException {
			return apiGrant(delegate.read(results));
		}

		private GrantAcqisition apiGrant(org.eclipse.passage.lic.floating.model.api.GrantAcqisition source) {
			return new PGrantAcquisition(source).get();
		}

	}

	private final class Request extends RemoteRequest<C> {

		private final OfFeature data;

		Request(OfFeature data, FloatingLicenseAccess access) {
			super(data.product(), access);
			this.data = data;
		}

		@Override
		protected RequestParameters parameters() {
			return new AcquireRequestParameters(data.product(), data.feature(), access);
		}

		@Override
		public Configuration<C> config() {
			return new BaseConfiguration.Get<C>();
		}

	}

}
