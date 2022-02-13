/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.base.access;

import java.util.List;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.acquire.ForsakenGrantsService;
import org.eclipse.passage.lic.api.acquire.GrantAcquisition;
import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionServicesRegistry;

public final class UnreleasedGrantsService implements ForsakenGrantsService {

	private final Residence residence;
	private final Storage storage;
	private final Conduit conduit;

	public UnreleasedGrantsService(Supplier<LicensedProduct> product, LicenseAcquisitionServicesRegistry acquirers) {
		this.residence = new Residence();
		this.storage = new Storage(residence.read());
		this.conduit = new Conduit(product, acquirers);
	}

	@Override
	public synchronized void takeCare(GrantAcquisition grant) {
		synchronized (storage) {
			storage.oneMoreLeftBehind(grant);
			residence.write(storage.forsaken());
		}
	}

	@Override
	public void settle() {
		List<GrantAcquisition> forsaken;
		synchronized (storage) {
			forsaken = storage.forsaken(); // fresh copy
		}
		conduit.release(forsaken);
	}

}
