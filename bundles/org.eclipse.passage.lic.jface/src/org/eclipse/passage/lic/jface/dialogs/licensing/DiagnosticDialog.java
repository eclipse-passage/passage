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
package org.eclipse.passage.lic.jface.dialogs.licensing;

import java.util.Optional;

import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.internal.base.diagnostic.SumOfLists;
import org.eclipse.passage.lic.internal.base.diagnostic.TroubleHasException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public final class DiagnosticDialog extends NotificationDialog {

	private final Diagnostic diagnostic;
	private ButtonConfig error;

	public DiagnosticDialog(Shell shell, Diagnostic diagnostic) {
		super(shell);
		this.diagnostic = diagnostic;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Licensing failure diagnostic"); //$NON-NLS-1$
		shell.setImage(getDefaultImage());
		shell.setSize(850, 300);
	}

	@Override
	protected void initButtons() {
		error = new ButtonConfig(1, this::viewError, "&View Error...", "", ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		error.reside(buttons);
		new ButtonConfig(2, new CopyToClipboard(this::getShell, new DiagnosticExplained(diagnostic)), "Co&py", "", "")//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				.reside(buttons);
	}

	@Override
	protected void buildUI(Composite parent) {
		viewer = new LicensingTable<Trouble>(parent, Trouble.class) //
				.withColumn("Error", 720, t -> t.details()) //$NON-NLS-1$
				.withColumn("Code", 50, t -> Integer.toString(t.code().code())) //$NON-NLS-1$
				.withColumn("Failure", 50, t -> t.exception().isPresent() ? "Oh, yah..." : "Nope!")//$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
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

	private void viewError() {
		Optional<Trouble> trouble = selectedTrouble();
		if (!trouble.isPresent()) {
			return;
		}
		if (!new TroubleHasException().test(trouble)) {
			return;
		}
		ErrorDialog.openError(getShell(), //
				"title", //$NON-NLS-1$
				"message", //$NON-NLS-1$
				new StatusFromException(trouble.get().exception().get()).get());//
	}

	private Optional<Trouble> selectedTrouble() {
		return new FirstSelected<Trouble>(viewer.getSelection(), Trouble.class).get();
	}
}
