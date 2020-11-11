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

import java.util.Collection;
import java.util.function.BiFunction;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.internal.hc.remote.ResponseHandler;
import org.eclipse.passage.lic.internal.hc.remote.impl.HttpClient;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteRequest;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteService;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData.OfFeature;
import org.eclipse.passage.lic.internal.hc.remote.impl.RequestParameters;

abstract class SignRequestService extends RemoteService<Boolean, RemoteServiceData.OfFeature> {

	SignRequestService(KeyKeeperRegistry keys, StreamCodecRegistry codecs) {
		super(keys, codecs);
	}

	@Override
	protected final ServiceInvocationResult<Boolean> withServers(OfFeature parameters,
			Collection<FloatingLicenseAccess> servers) {
		return servers.stream()//
				.map(access -> acquire(parameters, access))//
				.filter(result -> new NoSevereErrors().test(result.diagnostic()))//
				.filter(result -> result.data().isPresent())//
				.findFirst()//
				.orElse(new BaseServiceInvocationResult<>(Boolean.FALSE));
	}

	private ServiceInvocationResult<Boolean> acquire(OfFeature parameters, FloatingLicenseAccess access) {
		return new HttpClient<Boolean>()//
				.request(//
						request(parameters, access), //
						handler()//
				);

	}

	protected abstract RemoteRequest request(RemoteServiceData.OfFeature params, FloatingLicenseAccess access);

	protected abstract ResponseHandler<Boolean> handler();

	protected static final class OfFeatureRequest extends RemoteRequest {

		private final OfFeature data;
		private final BiFunction<RemoteServiceData.OfFeature, FloatingLicenseAccess, RequestParameters> parameters;

		OfFeatureRequest(OfFeature data, FloatingLicenseAccess access,
				BiFunction<RemoteServiceData.OfFeature, FloatingLicenseAccess, RequestParameters> parameters) {
			super(data.product(), access);
			this.data = data;
			this.parameters = parameters;
		}

		@Override
		protected RequestParameters parameters() {
			return parameters.apply(data, access);
		}

	}
}
