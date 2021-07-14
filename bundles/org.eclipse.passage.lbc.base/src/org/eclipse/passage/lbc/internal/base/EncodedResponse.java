/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lbc.internal.base;

import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.passage.lbc.internal.base.api.FlsGear;
import org.eclipse.passage.lbc.internal.base.api.FlsGearAwre;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.io.Hashes;
import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.handle.EObjectTransfer;
import org.eclipse.passage.lic.internal.net.handle.Failure;
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;

public final class EncodedResponse<T extends EObject> {

	private final T payload;
	private final ProductUserRequest<RawRequest> data;

	public EncodedResponse(T payload, ProductUserRequest<RawRequest> data) {
		this.payload = payload;
		this.data = data;
	}

	public NetResponse get() {
		try {
			return new FlsGearAwre()//
					.withGear(this::transferable) //
					.orElse(new Failure.OperationFailed("mine", "Failed exploiting gear")); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (LicensingException e) {
			// unreachable, development mistake
			throw new IllegalStateException("FLS configurational is not complete", e); //$NON-NLS-1$
		}
	}

	private Optional<NetResponse> transferable(FlsGear gear) {
		return Optional.of(new EObjectTransfer(payload, keyKeeper(gear), hashes(gear)));
	}

	private KeyKeeper keyKeeper(FlsGear gear) {
		return gear.keyKeper(data.product().get(), data.raw().state()::source);
	}

	private Hashes hashes(FlsGear gear) {
		return gear.hashes().get().service(new StringServiceId(data.algorithm().get()));
	}

}
