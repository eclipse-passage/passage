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
package org.eclipse.passage.lic.internal.net.handle;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.internal.net.api.handle.Chore;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;

public abstract class ChoreDraft<R extends NetRequest> implements Chore {

	protected final R data;
	protected final Logger log = LogManager.getLogger(getClass());

	protected ChoreDraft(R request) {
		this.data = request;
	}

	@Override
	public final NetResponse getDone() {
		Optional<NetResponse> raw = rawInvalid();
		if (raw.isPresent()) {
			return raw.get();
		}
		ProductUserRequest<R> request;
		try {
			request = new ProductUserRequest<>(data);
		} catch (LicensingException e) {
			log.error("failed: ", e); //$NON-NLS-1$ ;
			return failed(e.getMessage());
		}
		if (!request.product().isPresent()) {
			return new Failure.BadRequestInvalidProduct();
		}

		if (!request.user().isPresent()) {
			return new Failure.BadRequestNoUser();
		}
		if (!request.algorithm().isPresent()) {
			return new Failure.BadRequestNoAlgo();
		}
		Optional<NetResponse> refined = productUserInvalid(request);
		if (refined.isPresent()) {
			return refined.get();
		}
		try {
			return withProductUser(request);
		} catch (LicensingException e) {
			log.error("failed: ", e); //$NON-NLS-1$ ;
			return failed(e.getMessage());
		}
	}

	protected abstract Optional<NetResponse> rawInvalid();

	protected abstract Optional<NetResponse> productUserInvalid(ProductUserRequest<R> refined);

	protected abstract NetResponse withProductUser(ProductUserRequest<R> request) throws LicensingException;

	protected final NetResponse failed(String details) {
		return new Failure.OperationFailed(getClass().getSimpleName(), details);
	}

}
