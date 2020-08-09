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

import java.util.List;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.base.diagnostic.SumOfLists;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public final class DiagnosticDialog extends TitleAreaDialog {

	private final Diagnostic diagnostic;
	private TableViewer viewer;

	public DiagnosticDialog(Shell shell, Diagnostic diagnostic) {
		super(shell);
		this.diagnostic = diagnostic;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite owner = owner(parent);
		errorsTable(owner);
		inplaceData();
		return owner;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Licensing failure diagnostic"); //$NON-NLS-1$
		shell.setImage(getDefaultImage());
		shell.setSize(620, 300);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	private Composite owner(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setLayout(new GridLayout(1, false));
		return composite;
	}

	private void errorsTable(Composite parent) {
		viewer = new LicensingTable<Trouble>(parent, Trouble.class) //
				.withColumn("Error", 450, t -> t.details()) //$NON-NLS-1$
				.withColumn("Code", 50, t -> Integer.toString(t.code().code())) //$NON-NLS-1$
				.withColumn("Type", 100, t -> t.code().explanation()) //$NON-NLS-1$
				.withColumn("Failure", 50, t -> t.exception().isPresent() ? "Oh, yah..." : "Nope!")//$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
				.viewer();
	}

	private void inplaceData() {
		List<Trouble> list = new SumOfLists<Trouble>().apply(diagnostic.severe(), diagnostic.bearable());
		System.out.println(list.size());
		viewer.setInput(list);
	}
}
