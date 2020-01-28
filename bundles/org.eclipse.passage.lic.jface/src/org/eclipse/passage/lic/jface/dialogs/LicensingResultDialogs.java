/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.jface.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
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
		String message = prepareMessage(result);
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

	public static void updateTitleDialog(TitleAreaDialog dialog, LicensingResult result) {
		int severity = result.getSeverity();
		String message = prepareMessage(result);
		switch (severity) {
		case LicensingResult.OK:
			dialog.setErrorMessage(null);
			dialog.setMessage(message);
			return;
		case LicensingResult.INFO:
			dialog.setErrorMessage(null);
			dialog.setMessage(message, IMessageProvider.INFORMATION);
			return;
		case LicensingResult.WARNING:
			dialog.setErrorMessage(null);
			dialog.setMessage(message, IMessageProvider.WARNING);
			return;
		case LicensingResult.ERROR:
		case LicensingResult.CANCEL:
			dialog.setErrorMessage(message);
			return;
		default:
			dialog.setErrorMessage(message);
		}
	}

	private static String prepareMessage(LicensingResult result) {
		StringBuilder builder = new StringBuilder();
		builder.append(result.getMessage());
		collectMessages(result.getChildren(), builder, String.valueOf('\t'), 1);
		return builder.toString();
	}

	private static void collectMessages(Iterable<LicensingResult> children, StringBuilder builder, String indent,
			int level) {
		for (LicensingResult result : children) {
			builder.append('\n');
			for (int i = 0; i < level; i++) {
				builder.append(indent);
			}
			builder.append(result.getMessage());
			collectMessages(result.getChildren(), builder, indent, level + 1);
		}
	}

}
