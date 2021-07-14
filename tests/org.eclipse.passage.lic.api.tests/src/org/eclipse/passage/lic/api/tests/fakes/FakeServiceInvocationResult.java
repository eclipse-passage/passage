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
package org.eclipse.passage.lic.api.tests.fakes;

import java.util.Optional;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.tests.fakes.diagnostic.FakeFailureDiagnostic;

public final class FakeServiceInvocationResult<T> implements ServiceInvocationResult<T> {

	private final Optional<T> data;
	private final Diagnostic diagnostic = new FakeFailureDiagnostic();

	public FakeServiceInvocationResult(T data) {
		this(Optional.of(data));
	}

	public FakeServiceInvocationResult() {
		this(Optional.empty());
	}

	public FakeServiceInvocationResult(Optional<T> data) {
		this.data = data;
	}

	@Override
	public Diagnostic diagnostic() {
		return diagnostic;
	}

	@Override
	public Optional<T> data() {
		return data;
	}

}
