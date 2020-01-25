/*******************************************************************************
 * Copyright (c) 2019-2020 ArSysOp
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

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class FileContentDialog extends Dialog {

	private final String filePath;
	private Text editor;

	public FileContentDialog(Shell parentShell, String path) {
		super(parentShell);
		this.filePath = path;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(WorkbenchMessages.FileContentDialog_shell_text);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		Composite base = new Composite(parent, SWT.BORDER);
		base.setLayout(new GridLayout(1, false));
		base.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		editor = new Text(base, SWT.MULTI);
		editor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		scheduleReader();
		return parent;
	}

	private void scheduleReader() {
		Path path = Paths.get(filePath);
		try {
			byte[] readAllBytes = Files.readAllBytes(path);
			editor.setText(new String(readAllBytes, Charset.defaultCharset()));
		} catch (IOException e) {
			StringBuilder sb = new StringBuilder();
			sb.append(WorkbenchMessages.FileContentDialog_e_unable_read).append('\n');
			sb.append(e.getMessage());
			editor.setText(sb.toString());
		}
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

}
