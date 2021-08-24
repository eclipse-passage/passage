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
package org.eclipse.passage.lic.base.agreements;

import org.eclipse.passage.lic.api.agreements.AgreementState;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.agreements.ResolvedAgreement;
import org.eclipse.passage.lic.api.requirements.Requirement;

public final class BaseAgreementToAccept implements AgreementToAccept {

	private final Requirement origin;
	private final ResolvedAgreement definition;
	private final AgreementState acceptance;

	public BaseAgreementToAccept(Requirement origin, ResolvedAgreement definition, AgreementState acceptance) {
		this.origin = origin;
		this.definition = definition;
		this.acceptance = acceptance;
	}

	@Override
	public Requirement origin() {
		return origin;
	}

	@Override
	public ResolvedAgreement definition() {
		return definition;
	}

	@Override
	public AgreementState acceptance() {
		return acceptance;
	}

}
