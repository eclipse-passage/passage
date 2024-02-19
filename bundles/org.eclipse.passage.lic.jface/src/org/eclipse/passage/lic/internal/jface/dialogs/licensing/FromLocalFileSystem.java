/*******************************************************************************
 * Copyright (c) 2024 ArSysOp
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

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.passage.lic.internal.jface.i18n.ImportLicenseDialogMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public final class FromLocalFileSystem implements LicenseFilesControl {

	private Text path;

	@Override
	/**
	 * parent is expected to have GridLayout(3, false)
	 */
	public void install(Composite parent, Consumer<List<Path>> onLicenses) {
		new Label(parent, SWT.NONE).setText(ImportLicenseDialogMessages.ImportLicenseDialog_path_label);
		path = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
		path.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		Button browse = new Button(parent, SWT.PUSH);
		browse.setText(ImportLicenseDialogMessages.ImportLicenseDialog_browse);
		browse.addListener(SWT.Selection, e -> browseAndLoad(onLicenses));
	}

	private void browseAndLoad(Consumer<List<Path>> onLicenses) {
		onLicenses.accept(browse());
	}

	private List<Path> browse() {
		DirectoryDialog dialog = new DirectoryDialog(path.getShell(), SWT.OPEN | SWT.SHEET);
		dialog.setText(ImportLicenseDialogMessages.ImportLicenseDialog_browse_dialog_title);
		String folder = dialog.open();
		if (folder == null) {
			return Collections.emptyList();
		}
		path.setText(folder);
		List<Path> licenses = new AllLicensesFromFolder(folder).get();
		path.setData(licenses);
		return licenses;
	}

}
