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
package org.eclipse.passage.lic.internal.base.diagnostic;

import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;

public final class DiagnosticExplained implements Supplier<String> {

	private final Diagnostic diagnostic;

	public DiagnosticExplained(Diagnostic diagnostic) {
		this.diagnostic = diagnostic;
	}

	@Override
	public String get() {
		StringBuilder out = new StringBuilder();
		// FIXME: implement
		return out.toString();
	}

}
