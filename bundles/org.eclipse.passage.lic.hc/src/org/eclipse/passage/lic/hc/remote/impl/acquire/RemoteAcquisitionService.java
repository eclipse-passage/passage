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
package org.eclipse.passage.lic.hc.remote.impl.acquire;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.acquire.GrantAcquisition;
import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionService;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.base.io.LicensingFolder;
import org.eclipse.passage.lic.base.io.UserHomePath;
import org.eclipse.passage.lic.hc.remote.Client;
import org.eclipse.passage.lic.hc.remote.Connection;
import org.eclipse.passage.lic.hc.remote.impl.Equipment;
import org.eclipse.passage.lic.hc.remote.impl.HttpClient;
import org.eclipse.passage.lic.hc.remote.impl.RemoteServiceData;

/**
 * 
 * @param <C>
 * 
 * @since 1.1
 */
public final class RemoteAcquisitionService<C extends Connection> implements LicenseAcquisitionService {

	private final Equipment equipment;
	private final ConditionMiningTarget target = new ConditionMiningTarget.Remote();
	private final Supplier<Client<C, GrantAcquisition>> acquire;
	private final Supplier<Client<C, Boolean>> release;
	private final Supplier<Path> source;

	public RemoteAcquisitionService(Equipment equipment, Supplier<Client<C, GrantAcquisition>> acquire,
			Supplier<Client<C, Boolean>> release, //
			Supplier<Path> source) {
		this.equipment = equipment;
		this.acquire = acquire;
		this.release = release;
		this.source = source;
	}

	public RemoteAcquisitionService(Equipment equipment) {
		this(equipment, HttpClient::new, HttpClient::new, new LicensingFolder(new UserHomePath()));
	}

	@Override
	public ConditionMiningTarget id() {
		return target;
	}

	@Override
	public ServiceInvocationResult<GrantAcquisition> acquire(LicensedProduct product, String feature) {
		return new RemoteAcquire<>(equipment, acquire, source)
				.request(new RemoteServiceData.OfFeature(product, feature));
	}

	@Override
	public ServiceInvocationResult<Boolean> release(LicensedProduct product, GrantAcquisition acquisition) {
		return new RemoteRelease<>(equipment, release, source)//
				.request(new RemoteServiceData.WithPayload<>(product, acquisition));
	}

}
