/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.loc.workbench.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ManageTextValuesDialog extends Dialog {

	private final String splitter;

	private final String initialValues;

	private Text editor;
	private String resultValues;

	public ManageTextValuesDialog(Shell parentShell, String values, String splitter) {
		super(parentShell);
		this.splitter = splitter;
		this.initialValues = values;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setSize(400, 500);
	}

	public String getResultValue() {
		return resultValues;
	}

	private String prepareResultValues() {

		StringBuilder builder = new StringBuilder();
		for (String item : editor.getText().split("\n")) { //$NON-NLS-1$
			String filtred = item.trim();
			if (filtred.isEmpty()) {
				continue;
			}
			if (builder.length() > 0) {
				builder.append(splitter);
			}
			builder.append(filtred);
		}
		return builder.toString();
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == Dialog.OK) {
			resultValues = prepareResultValues();
		}
		super.buttonPressed(buttonId);
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		parent.setLayout(new GridLayout(1, false));
		Composite base = new Composite(parent, SWT.BORDER);
		base.setLayout(new GridLayout(1, false));
		base.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		editor = new Text(base, SWT.MULTI);
		editor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		if (initialValues != null) {
			String replaced = initialValues.replace(splitter, "\n"); //$NON-NLS-1$
			editor.setText(replaced);
		}
		return parent;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}
}
