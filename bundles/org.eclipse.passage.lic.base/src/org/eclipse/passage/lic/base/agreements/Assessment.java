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

import java.util.Optional;

import org.eclipse.passage.lic.api.agreements.AgreementState;
import org.eclipse.passage.lic.api.diagnostic.Trouble;

final class Assessment implements AgreementState {

	private final String name;
	private final byte[] content;
	private final boolean accepted;
	private final Optional<Trouble> error;

	Assessment(String name, byte[] content, boolean accepted) {
		this(name, content, accepted, Optional.empty());
	}

	Assessment(String name, byte[] content, Trouble error) {
		this(name, content, false, Optional.of(error));
	}

	Assessment(String name, Trouble error) {
		this(name, new byte[0], false, Optional.of(error));
	}

	private Assessment(String name, byte[] content, boolean accepted, Optional<Trouble> error) {
		this.name = name;
		this.content = content;
		this.accepted = accepted;
		this.error = error;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public byte[] content() {
		return content;
	}

	@Override
	public boolean accepted() {
		return accepted;
	}

	@Override
	public Optional<Trouble> error() {
		return error;
	}

}
