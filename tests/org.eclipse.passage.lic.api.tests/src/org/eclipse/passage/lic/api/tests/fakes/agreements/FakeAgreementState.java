/*******************************************************************************
 * Copyright (c) 2024 ArSysOp
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
package org.eclipse.passage.lic.api.tests.fakes.agreements;

import java.util.Optional;

import org.eclipse.passage.lic.api.agreements.AgreementState;
import org.eclipse.passage.lic.api.diagnostic.Trouble;

@SuppressWarnings("restriction")
public final class FakeAgreementState implements AgreementState {

	private final boolean accepted;

	public FakeAgreementState(boolean accepted) {
		this.accepted = accepted;
	}

	public FakeAgreementState() {
		this(true);
	}

	@Override
	public String name() {
		return "fake"; //$NON-NLS-1$
	}

	@Override
	public boolean accepted() {
		return accepted;
	}

	@Override
	public byte[] content() {
		return new byte[0];
	}

	@Override
	public Optional<Trouble> error() {
		return Optional.empty();
	}

}
