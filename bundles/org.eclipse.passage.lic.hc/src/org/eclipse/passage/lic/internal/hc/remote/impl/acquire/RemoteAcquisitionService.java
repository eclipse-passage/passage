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

import java.net.HttpURLConnection;
import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.acquire.GrantAcqisition;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionService;
import org.eclipse.passage.lic.internal.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;
import org.eclipse.passage.lic.internal.hc.remote.Client;
import org.eclipse.passage.lic.internal.hc.remote.impl.HttpClient;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteServiceData;

public final class RemoteAcquisitionService implements LicenseAcquisitionService {

	private final KeyKeeperRegistry keys;
	private final StreamCodecRegistry codecs;
	private final ConditionMiningTarget target = new ConditionMiningTarget.Remote();
	private final Supplier<Client<HttpURLConnection, GrantAcqisition>> acquire;
	private final Supplier<Client<HttpURLConnection, Boolean>> release;
	private final Supplier<Path> source;

	public RemoteAcquisitionService(KeyKeeperRegistry keys, StreamCodecRegistry codecs,
			Supplier<Client<HttpURLConnection, GrantAcqisition>> acquire,
			Supplier<Client<HttpURLConnection, Boolean>> release, //
			Supplier<Path> source) {
		this.keys = keys;
		this.codecs = codecs;
		this.acquire = acquire;
		this.release = release;
		this.source = source;
	}

	public RemoteAcquisitionService(KeyKeeperRegistry keys, StreamCodecRegistry codecs) {
		this(keys, codecs, HttpClient::new, HttpClient::new, new LicensingFolder(new UserHomePath()));
	}

	@Override
	public ConditionMiningTarget id() {
		return target;
	}

	@Override
	public ServiceInvocationResult<GrantAcqisition> acquire(LicensedProduct product, String feature) {
		return new RemoteAcquire(keys, codecs, acquire, source)
				.request(new RemoteServiceData.OfFeature(product, feature));
	}

	@Override
	public ServiceInvocationResult<Boolean> release(LicensedProduct product, GrantAcqisition acquisition) {
		return new RemoteRelease(keys, codecs, release, source)//
				.request(new RemoteServiceData.WithPayload<>(product, acquisition));
	}

}
