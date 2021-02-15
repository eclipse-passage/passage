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

import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.internal.api.EvaluationInstructions;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.handle.ChoreDraft;
import org.eclipse.passage.lic.internal.net.handle.Failure;

abstract class AuthentifiedChoreDraft extends ChoreDraft<RawRequest> {

	protected AuthentifiedChoreDraft(RawRequest request) {
		super(request);
	}

	@Override
	protected Optional<NetResponse> invalid() {
		Optional<EvaluationInstructions> instructions = new ServerAuthenticationInstructions(data).get();
		if (!instructions.isPresent()) {
			return Optional.of(new Failure.BadRequestInvalidServerAuthInstructions());
		}
		try {
			new ServerAuthentication(instructions.get()).evaluate();
		} catch (Exception e) {
			log.error("failed: ", e); //$NON-NLS-1$
			return Optional.of(new Failure.ForeignServer(e.getMessage()));
		}
		return Optional.empty();
	}

}
