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
package org.eclipse.passage.lbc.internal.base;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.internal.api.EvaluationInstructions;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.net.api.handle.Chore;
import org.eclipse.passage.lic.internal.net.api.handle.NetRequest;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.handle.Failure;

abstract class ChoreDraft<R extends NetRequest> implements Chore {

	protected final R data;
	protected final Logger log = LogManager.getLogger(getClass());

	protected ChoreDraft(R request) {
		this.data = request;
	}

	@Override
	public final NetResponse getDone() {
		Optional<EvaluationInstructions> instructions = new ServerAuthenticationInstructions(data).get();
		if (!instructions.isPresent()) {
			return new Failure.BadRequestInvalidServerAuthInstructions();
		}
		try {
			new ServerAuthentication(instructions.get()).evaluate();
		} catch (Exception e) {
			log.error("failed: ", e); //$NON-NLS-1$
			return new Failure.ForeignServer(e.getMessage());
		}
		ProductUserRequest<R> request;
		try {
			request = new ProductUserRequest<R>(data);
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
		try {
			return withProductUser(request);
		} catch (LicensingException e) {
			log.error("failed: ", e); //$NON-NLS-1$ ;
			return failed(e.getMessage());
		}
	}

	protected abstract NetResponse withProductUser(ProductUserRequest<R> request) throws LicensingException;

	protected final NetResponse failed(String details) {
		return new Failure.OperationFailed(getClass().getSimpleName(), details);
	}

}
