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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.internal.base.diagnostic.NoSevereErrors;
import org.eclipse.passage.lic.internal.base.diagnostic.SumOfDiagnostics;

public abstract class ServiceAny<T, D extends RemoteServiceData> extends ServiceRemote<T, D> {

	protected ServiceAny(KeyKeeperRegistry keys, StreamCodecRegistry codecs) {
		super(keys, codecs);
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
