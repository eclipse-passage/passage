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

import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Supplier;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.internal.base.diagnostic.code.AbsentLicenseAttendantFile;
import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;
import org.eclipse.passage.lic.internal.hc.i18n.AccessMessages;
import org.eclipse.passage.lic.internal.hc.remote.Client;
import org.eclipse.passage.lic.internal.hc.remote.Connection;
import org.eclipse.passage.lic.internal.hc.remote.ResponseHandler;

public abstract class ServiceRemote<C extends Connection, T, D extends RemoteServiceData> {

	private final KeyKeeperRegistry keys;
	private final StreamCodecRegistry codecs;
	private final Supplier<Path> source;
	private final Supplier<Client<C, T>> client;

	protected ServiceRemote(KeyKeeperRegistry keys, StreamCodecRegistry codecs, Supplier<Client<C, T>> client,
			Supplier<Path> source) {
		this.keys = keys;
		this.codecs = codecs;
		this.source = source;
		this.client = client;
	}

	protected ServiceRemote(KeyKeeperRegistry keys, StreamCodecRegistry codecs, Supplier<Client<C, T>> client) {
		this(keys, codecs, client, new LicensingFolder(new UserHomePath()));
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
		return client.get().request(//
				request(params, access), //
				handler(access));
	}

	private Trouble noServers() {
		return new Trouble(new AbsentLicenseAttendantFile(),
				String.format(AccessMessages.RemoteService_no_server, source.get().toAbsolutePath()));
	}

	private ServiceInvocationResult<Collection<FloatingLicenseAccess>> accesses(LicensedProduct product) {
		return new AccessPacks(product, keys, codecs, source).get();
	}

	protected abstract ServiceInvocationResult<T> withServers(D parameters, Collection<FloatingLicenseAccess> servers);

	protected abstract RemoteRequest<C> request(D params, FloatingLicenseAccess access);

	protected abstract ResponseHandler<T> handler(FloatingLicenseAccess access);

}
