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
package org.eclipse.passage.lic.internal.jface.dialogs;

import java.util.function.Supplier;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.swt.widgets.Shell;

/**
 * @deprecated use DiagnosticDialog
 */
@Deprecated
public final class OpenDiagnosticMessage {

	private final Supplier<Shell> shell;
	private final Supplier<String> title;

	public OpenDiagnosticMessage(Supplier<Shell> shell, Supplier<String> title) {
		this.shell = shell;
		this.title = title;
	}

	public void apply(Diagnostic result) {
		if (!result.severe().isEmpty()) {
			MessageDialog.openError(shell.get(), title.get(), summarize(result));
			return;
		}
		if (!result.bearable().isEmpty()) {
			MessageDialog.openWarning(shell.get(), title.get(), summarize(result));
			return;
		}
		MessageDialog.openInformation(shell.get(), title.get(), summarize(result));
	}

	private String summarize(Diagnostic diagnostic) {
		return diagnostic.toString();
	}

}
