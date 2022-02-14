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

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.acquire.GrantAcquisition;
import org.eclipse.passage.lic.api.acquire.GrantsTraceService;
import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionServicesRegistry;

public final class StoringGrantTraceService implements GrantsTraceService {

	private final Residence residence;
	private final Storage storage;
	private final Conduit conduit;
	private final AtomicBoolean fresh = new AtomicBoolean(true);

	public StoringGrantTraceService(Supplier<LicensedProduct> product, Supplier<Path> srotage,
			Supplier<LicenseAcquisitionServicesRegistry> acquirers) {
		this.residence = new Residence(srotage);
		this.storage = new Storage(residence.read());
		this.conduit = new Conduit(product, acquirers);
	}

	@Override
	public synchronized void trace(GrantAcquisition grant) {
		if (fresh.getAndSet(false)) {
			releaseForsaken();
		}
		synchronized (storage) {
			storage.keep(grant);
			residence.write(storage.active());
		}
	}

	@Override
	public synchronized void forget(GrantAcquisition grant) {
		synchronized (storage) {
			storage.forget(grant);
			residence.write(storage.active());
		}
	}

	private void releaseForsaken() {
		List<GrantAcquisition> forsaken;
		synchronized (storage) {
			forsaken = storage.active(); // fresh copy
		}
		conduit.release(forsaken);
	}

}
