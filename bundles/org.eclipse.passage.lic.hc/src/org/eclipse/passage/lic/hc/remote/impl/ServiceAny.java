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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.base.diagnostic.SumOfDiagnostics;
import org.eclipse.passage.lic.hc.remote.Client;
import org.eclipse.passage.lic.hc.remote.Connection;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;

/**
 * 
 * @since 1.1
 */
public abstract class ServiceAny<C extends Connection, T, D extends RemoteServiceData> extends ServiceRemote<C, T, D> {

	protected ServiceAny(Equipment equipment, Supplier<Client<C, T>> client, Supplier<Path> source) {
		super(equipment, client, source);
	}

	@Override
	protected final ServiceInvocationResult<T> withServers(D parameters, Collection<FloatingLicenseAccess> servers) {
		List<Diagnostic> diagnostics = new ArrayList<>();
		return servers.stream()//
				.map(server -> withServer(parameters, server))//
				.peek(result -> diagnostics.add(result.diagnostic())) //
				.filter(result -> new NoSevereErrors().test(result.diagnostic()))//
				.filter(result -> result.data().isPresent())//
				.findAny()//
				.orElse(new BaseServiceInvocationResult<>(sum(diagnostics)));
	}

	private Diagnostic sum(List<Diagnostic> particles) {
		return particles.stream()//
				.reduce(new SumOfDiagnostics())//
				.orElseGet(BaseDiagnostic::new);
	}

}
