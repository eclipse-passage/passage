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
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.internal.jface.dialogs.OpenDiagnosticMessage;
import org.eclipse.passage.lic.internal.jface.dialogs.UpdateDialogTitle;

/**
 * 
 * @since 0.5.0
 * 
 * @deprecated use {@link OpenDiagnosticMessage} {@link UpdateDialogTitle}
 */
@Deprecated
public final class LicensingResultDialogs {

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
