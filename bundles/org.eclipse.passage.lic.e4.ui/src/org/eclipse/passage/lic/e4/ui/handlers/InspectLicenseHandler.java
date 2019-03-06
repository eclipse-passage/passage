/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.e4.ui.handlers;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.passage.lic.jface.dialogs.LicensingStatusDialog;
import org.eclipse.swt.widgets.Shell;

public class InspectLicenseHandler {

	@Execute
	public void execute(Shell shell, IEclipseContext context) {
		String contacts = "Eclipse Passage \nhttps://www.eclipse.org/passage";
		LicensingStatusDialog.setDefaultContacts(contacts);
		LicensingStatusDialog dialog = new LicensingStatusDialog(shell);
		dialog.open();
	}

}