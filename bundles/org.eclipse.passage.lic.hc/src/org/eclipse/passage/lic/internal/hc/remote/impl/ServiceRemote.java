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
package org.eclipse.passage.lic.internal.hc.remote.impl;

import java.util.Collection;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.internal.base.diagnostic.code.AbsentLicenseAttendantFile;
import org.eclipse.passage.lic.internal.hc.i18n.AccessMessages;
import org.eclipse.passage.lic.internal.hc.remote.ResponseHandler;

public abstract class ServiceRemote<T, D extends RemoteServiceData> {

	private final KeyKeeperRegistry keys;
	private final StreamCodecRegistry codecs;

	protected ServiceRemote(KeyKeeperRegistry keys, StreamCodecRegistry codecs) {
		this.keys = keys;
		this.codecs = codecs;
	}

	public final ServiceInvocationResult<T> request(D parameters) {
		ServiceInvocationResult<Collection<FloatingLicenseAccess>> accesses = accesses(parameters.product());
		if (!new NoSevereErrors().test(accesses.diagnostic())) {
			return new BaseServiceInvocationResult<>(accesses.diagnostic());
		}
		if (accesses.data().get().isEmpty()) {
			new BaseServiceInvocationResult<>(noServers());
		}
		return withServers(parameters, accesses.data().get());
	}

	protected final ServiceInvocationResult<T> withServer(D params, FloatingLicenseAccess access) {
		return new HttpClient<T>().request(//
				request(params, access), //
				handler(access));
	}

	private Trouble noServers() {
		return new Trouble(new AbsentLicenseAttendantFile(), AccessMessages.RemoteService_no_server);
	}

	private ServiceInvocationResult<Collection<FloatingLicenseAccess>> accesses(LicensedProduct product) {
		return new AccessPacks(product, keys, codecs).get();
	}

	protected abstract ServiceInvocationResult<T> withServers(D parameters, Collection<FloatingLicenseAccess> servers);

	protected abstract RemoteRequest request(D params, FloatingLicenseAccess access);

	protected abstract ResponseHandler<T> handler(FloatingLicenseAccess access);

}
