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
package org.eclipse.passage.lic.hc.remote.impl;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.base.diagnostic.code.AbsentLicenseAttendantFile;
import org.eclipse.passage.lic.base.io.LicensingFolder;
import org.eclipse.passage.lic.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.base.io.UserHomePath;
import org.eclipse.passage.lic.hc.remote.Client;
import org.eclipse.passage.lic.hc.remote.Connection;
import org.eclipse.passage.lic.hc.remote.ResponseHandler;
import org.eclipse.passage.lic.internal.hc.i18n.AccessMessages;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;

/**
 * 
 * @since 1.1
 */
public abstract class ServiceRemote<C extends Connection, T, D extends RemoteServiceData> {

	protected final Equipment equipment;
	private final Supplier<Path> source;
	private final Supplier<Client<C, T>> client;

	protected ServiceRemote(Equipment equipment, Supplier<Client<C, T>> client, Supplier<Path> source) {
		this.equipment = equipment;
		this.source = source;
		this.client = client;
	}

	protected ServiceRemote(Equipment equipment, Supplier<Client<C, T>> client) {
		this(equipment, client, new LicensingFolder(new UserHomePath()));
	}

	public final ServiceInvocationResult<T> request(D parameters) {
		ServiceInvocationResult<Collection<FloatingLicenseAccess>> accesses = accesses(parameters.product());
		if (!new NoSevereErrors().test(accesses.diagnostic())) {
			return new BaseServiceInvocationResult<>(accesses.diagnostic());
		}
		if (accesses.data().get().isEmpty()) {
			return noServers(parameters.product());
		}
		return withServers(parameters, accesses.data().get());
	}

	protected final ServiceInvocationResult<T> withServer(D params, FloatingLicenseAccess access) {
		return client.get().request(//
				request(params, access), //
				handler(access));
	}

	private ServiceInvocationResult<T> noServers(LicensedProduct product) {
		return new BaseServiceInvocationResult<>(//
				new BaseDiagnostic(Collections.singletonList(//
						new Trouble(//
								new AbsentLicenseAttendantFile(), //
								String.format(//
										AccessMessages.RemoteService_no_server, //
										new PathFromLicensedProduct(source, product).get().toAbsolutePath())))));
	}

	private ServiceInvocationResult<Collection<FloatingLicenseAccess>> accesses(LicensedProduct product) {
		return new AccessPacks(product, equipment.keys(), equipment.codecs(), source).get();
	}

	protected abstract ServiceInvocationResult<T> withServers(D parameters, Collection<FloatingLicenseAccess> servers);

	protected abstract RemoteRequest<C> request(D params, FloatingLicenseAccess access);

	protected abstract ResponseHandler<T> handler(FloatingLicenseAccess access);

}
