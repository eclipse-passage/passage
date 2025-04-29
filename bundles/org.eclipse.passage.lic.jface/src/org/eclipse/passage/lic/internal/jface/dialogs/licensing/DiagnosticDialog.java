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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.Optional;

import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.base.diagnostic.TroubleHasException;
import org.eclipse.passage.lic.internal.jface.i18n.DiagnosticDialogMessages;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public final class DiagnosticDialog extends NotificationDialog {

	private final Diagnostic diagnostic;
	private TroublesViewer troubles;
	private ButtonConfig error;

	public DiagnosticDialog(Shell shell, Diagnostic diagnostic) {
		super(shell);
		this.diagnostic = diagnostic;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(DiagnosticDialogMessages.DiagnosticDialog_title);
		shell.setImage(getDefaultImage());
		shell.setSize(1250, 500);
	}

	@Override
	protected void initButtons() {
		error = new ButtonConfig(1, this::viewError, //
				DiagnosticDialogMessages.DiagnosticDialog_action_view_failure, //
				DiagnosticDialogMessages.DiagnosticDialog_action_view_failure_tooltip, ""); //$NON-NLS-1$
		error.reside(buttons);
		new ButtonConfig(2, new CopyToClipboard(this::getShell, new DiagnosticExplained(diagnostic)), //
				DiagnosticDialogMessages.DiagnosticDialog_action_copy, //
				DiagnosticDialogMessages.DiagnosticDialog_action_copy_tooltip, "") //$NON-NLS-1$
				.reside(buttons);
	}

	@Override
	protected void buildUI(Composite parent) {
		troubles = new TroublesViewer(getShell(), diagnostic);
		troubles.installControl(parent);
		viewer = troubles.viewer();
	}

	@Override
	protected void inplaceData() {
		troubles.installInput();
	}

	@Override
	protected void updateButtonsEnablement() {
		getButton(error.id()).setEnabled(new TroubleHasException().test(selectedTrouble()));
	}

	@Override
	protected void initMessage() {
		setMessage(DiagnosticDialogMessages.DiagnosticDialog_description);
	}

	private void viewError() {
		Optional<Trouble> trouble = selectedTrouble();
		if (!trouble.isPresent()) {
			return;
		}
		if (!new TroubleHasException().test(trouble)) {
			return;
		}
		ErrorDialog.openError(getShell(), //
				DiagnosticDialogMessages.DiagnosticDialog_error_title, //
				trouble.get().details(), //
				new StatusFromException(trouble.get().exception().get()).get());//
	}

	private Optional<Trouble> selectedTrouble() {
		return new FirstSelected<Trouble>(viewer.getSelection(), Trouble.class).get();
	}

}
