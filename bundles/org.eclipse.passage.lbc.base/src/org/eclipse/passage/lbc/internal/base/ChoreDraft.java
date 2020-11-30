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
package org.eclipse.passage.lbc.internal.base;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lbc.internal.api.Chore;
import org.eclipse.passage.lbc.internal.api.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.RawRequest;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationInstructions;

abstract class ChoreDraft implements Chore {

	protected final RawRequest data;
	protected final Logger log = LogManager.getLogger(getClass());

	protected ChoreDraft(RawRequest request) {
		this.data = request;
	}

	@Override
	public final FloatingResponse getDone() {
		Optional<EvaluationInstructions> instructions = new ServerAuthenticationInstructions(data).get();
		if (!instructions.isPresent()) {
			return new Failure.BadRequestInvalidServerAuthInstructions();
		}
		try {
			new ServerAuthentication(instructions.get()).evaluate();
		} catch (Exception e) {
			log.error(e);
			return new Failure.ForeignServer(e.getMessage());
		}
		ProductUserRequest request;
		try {
			request = new ProductUserRequest(data);
		} catch (LicensingException e) {
			log.error(e);
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
			log.error(e);
			return failed(e.getMessage());
		}
	}

	protected abstract FloatingResponse withProductUser(ProductUserRequest request) throws LicensingException;

	protected final FloatingResponse failed(String details) {
		return new Failure.OperationFailed(getClass().getSimpleName(), details);
	}

}
