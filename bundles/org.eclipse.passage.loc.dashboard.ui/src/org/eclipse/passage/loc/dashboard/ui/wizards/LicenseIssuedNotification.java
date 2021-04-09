/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.wizards;

import java.nio.file.Path;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.passage.loc.internal.api.IssuedFloatingLicense;
import org.eclipse.passage.loc.internal.api.IssuedLicense;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.passage.loc.internal.licenses.ui.i18n.LicensesUiMessages;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Shell;

public final class LicenseIssuedNotification {

	private final Shell shell;

	public LicenseIssuedNotification(Shell shell) {
		this.shell = shell;
	}

	public void showPersonal(IssuedLicense license) {
		show(//
				IssueLicensePageMessages.IssueLicenseWizard_ok_licensed_title, //
				String.format(LicensesUiMessages.LicenseExportHandler_success_description, //
						license.encrypted().toAbsolutePath().toString(), //
						license.decrypted().toAbsolutePath().toString()), //
				license.encrypted().getParent());
	}

	public void showFloating(IssuedFloatingLicense license) {
		show(//
				IssueLicensePageMessages.IssueFloatingLicenseWizard_success, //
				String.format(IssueLicensePageMessages.IssueFloatingLicenseWizard_success_description,
						license.residence().toAbsolutePath()), //
				license.residence());
	}

	private void show(String title, String description, Path residence) {
		MessageDialog dialog = new MessageDialog(//
				shell, //
				title, //
				null, //
				description, //
				MessageDialog.INFORMATION, //
				new String[] { //
						"OK", //$NON-NLS-1$
						"Open &Folder" //$NON-NLS-1$
				}, 0);
		int result = dialog.open();
		if (result == 1) {
			Program.launch(residence.toAbsolutePath().toString());
		}
	}

}
