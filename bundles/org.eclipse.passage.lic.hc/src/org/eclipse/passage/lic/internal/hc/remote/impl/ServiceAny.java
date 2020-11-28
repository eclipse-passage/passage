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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.internal.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.internal.base.diagnostic.SumOfDiagnostics;
import org.eclipse.passage.lic.internal.hc.remote.Client;
import org.eclipse.passage.lic.internal.hc.remote.Connection;

public abstract class ServiceAny<C extends Connection, T, D extends RemoteServiceData> extends ServiceRemote<C, T, D> {

	protected ServiceAny(KeyKeeperRegistry keys, StreamCodecRegistry codecs, Supplier<Client<C, T>> client,
			Supplier<Path> source) {
		super(keys, codecs, client, source);
	}

	@Override
	protected final ServiceInvocationResult<T> withServers(D parameters, Collection<FloatingLicenseAccess> servers) {
		List<Diagnostic> diagnostics = new ArrayList<>();
		return servers.stream()//
				.map(server -> withServer(parameters, server))//
				.peek(result -> diagnostics.add(result.diagnostic())) //
				.filter(result -> new NoSevereErrors().test(result.diagnostic()))//
				.filter(result -> result.data().isPresent())//
				.findFirst()//
				.orElse(new BaseServiceInvocationResult<>(sum(diagnostics)));
	}

	private Diagnostic sum(List<Diagnostic> particles) {
		return particles.stream()//
				.reduce(new SumOfDiagnostics())//
				.orElseGet(BaseDiagnostic::new);
	}

}
