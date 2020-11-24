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
package org.eclipse.passage.lbc.internal.base.tobemoved;

import org.eclipse.passage.lbc.internal.api.tobemoved.Chore;
import org.eclipse.passage.lbc.internal.api.tobemoved.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.tobemoved.RawRequest;
import org.eclipse.passage.lic.internal.api.LicensingException;

abstract class ChoreDraft implements Chore {

	protected final RawRequest data;

	protected ChoreDraft(RawRequest request) {
		this.data = request;
	}

	@Override
	public final FloatingResponse getDone() {
		ProductUserRequest request;
		try {
			request = new ProductUserRequest(data);
		} catch (LicensingException e) {
			return failed(e.getMessage());
		}
		if (!request.product().isPresent()) {
			return new Failure.BadRequestInvalidProduct();
		}
		if (!request.user().isPresent()) {
			return new Failure.BadRequestNoUser();
		}
		try {
			return withProductUser(request);
		} catch (LicensingException e) {
			return failed(e.getMessage());
		}
	}

	protected abstract FloatingResponse withProductUser(ProductUserRequest request) throws LicensingException;

	protected final FloatingResponse failed(String details) {
		return new Failure.OperationFailed(getClass().getSimpleName(), details);
	}

}
