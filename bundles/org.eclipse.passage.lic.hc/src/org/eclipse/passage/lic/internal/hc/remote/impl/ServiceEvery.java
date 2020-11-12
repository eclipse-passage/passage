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
import java.util.function.BinaryOperator;

import org.eclipse.passage.lic.floating.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;

public abstract class ServiceEvery<T, D extends RemoteServiceData> extends ServiceRemote<T, D> {

	protected ServiceEvery(KeyKeeperRegistry keys, StreamCodecRegistry codecs) {
		super(keys, codecs);
	}

	@Override
	protected ServiceInvocationResult<T> withServers(D params, Collection<FloatingLicenseAccess> servers) {
		return servers.stream()//
				.map(access -> withServer(params, access))//
				.reduce(new BaseServiceInvocationResult.Sum<>(sum()))//
				.orElse(new BaseServiceInvocationResult<>(noResult()));
	}

	protected abstract BinaryOperator<T> sum();

	protected abstract T noResult();

}
