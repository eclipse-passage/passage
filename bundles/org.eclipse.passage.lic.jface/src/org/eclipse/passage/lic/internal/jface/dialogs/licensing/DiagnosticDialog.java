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
import org.eclipse.passage.lic.base.diagnostic.SumOfLists;
import org.eclipse.passage.lic.base.diagnostic.TroubleHasException;
import org.eclipse.passage.lic.internal.jface.i18n.DiagnosticDialogMessages;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public final class DiagnosticDialog extends NotificationDialog {

	private final Diagnostic diagnostic;
	private final DiagnosticColors colors;
	private ButtonConfig error;

	public DiagnosticDialog(Shell shell, Diagnostic diagnostic) {
		super(shell);
		this.diagnostic = diagnostic;
		this.colors = new DiagnosticColors(shell == null ? Display.getCurrent() : shell.getDisplay());
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
		viewer = new HereTable<Trouble>(parent, Trouble.class, this::backdround) //
				.withColumn(DiagnosticDialogMessages.DiagnosticDialog_column_details, 900, Trouble::details)
				.withColumn(DiagnosticDialogMessages.DiagnosticDialog_column_code, 50,
						trouble -> Integer.toString(trouble.code().code()))//
				.withColumn(DiagnosticDialogMessages.DiagnosticDialog_column_type, 250,
						trouble -> trouble.code().explanation())//
				.viewer();
	}

	@Override
	protected void inplaceData() {
		viewer.setInput(new SumOfLists<Trouble>().apply(diagnostic.severe(), diagnostic.bearable()));
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

	private Color backdround(Object element, int column) {
		if (column != 1) {
			return null; // framework driven null
		}
		boolean failure = ((Trouble) element).exception().isPresent();
		boolean severe = diagnostic.severe().contains(element);
		return colors.get(severe, failure);
	}

}
