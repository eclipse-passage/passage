/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.lic.jface.dialogs;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @since 0.5.0
 *
 */
public final class LicensingResultDialogs {

	public static void openMessageDialog(Shell parent, String title, LicensingResult result) {
		int severity = result.getSeverity();
		String message = result.getMessage();
		switch (severity) {
		case LicensingResult.OK:
		case LicensingResult.INFO:
			MessageDialog.openInformation(parent, title, message);
			return;
		case LicensingResult.WARNING:
			MessageDialog.openWarning(parent, title, message);
			return;
		case LicensingResult.ERROR:
		case LicensingResult.CANCEL:
			MessageDialog.openError(parent, title, message);
			return;
		default:
			MessageDialog.openError(parent, title, message);
		}
	}

}
