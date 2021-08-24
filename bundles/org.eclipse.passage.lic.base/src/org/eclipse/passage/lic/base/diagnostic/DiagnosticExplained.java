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
package org.eclipse.passage.lic.base.diagnostic;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.i18n.DiagnosticExplainedMessages;

/**
 * 
 * @since 2.1
 */
public final class DiagnosticExplained implements Supplier<String> {

	private final Diagnostic diagnostic;

	public DiagnosticExplained(Diagnostic diagnostic) {
		Objects.requireNonNull(diagnostic, "DiagnosticExplained::diagnostic");//$NON-NLS-1$
		this.diagnostic = diagnostic;
	}

	@Override
	public String get() {
		if (empty()) {
			return DiagnosticExplainedMessages.getString("DiagnosticExplained.empty"); //$NON-NLS-1$
		}
		StringBuilder out = new StringBuilder();
		out//
				.append(DiagnosticExplainedMessages.getString("DiagnosticExplained.prelude")) //$NON-NLS-1$
				.append("\r\n"); //$NON-NLS-1$
		append(diagnostic.severe(), DiagnosticExplainedMessages.getString("DiagnosticExplained.severe"), out); //$NON-NLS-1$
		append(diagnostic.bearable(), DiagnosticExplainedMessages.getString("DiagnosticExplained.bearable"), out); //$NON-NLS-1$
		return out.toString();
	}

	private boolean empty() {
		return diagnostic.severe().isEmpty() && diagnostic.bearable().isEmpty();
	}

	private void append(List<Trouble> troubles, String name, StringBuilder out) {
		if (troubles.isEmpty()) {
			return;
		}
		out.append(name).append("\r\n"); //$NON-NLS-1$
		troubles.forEach(trouble -> out.append(new TroubleExplained(trouble).get()));
	}

}
