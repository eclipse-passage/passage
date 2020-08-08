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
package org.eclipse.passage.lic.internal.jface.dialogs;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;

public final class UpdateDialogTitle {

	private final TitleAreaDialog dialog;

	public UpdateDialogTitle(TitleAreaDialog dialog) {
		this.dialog = dialog;
	}

	public void apply(Diagnostic result) {
		if (!result.severe().isEmpty()) {
			dialog.setErrorMessage(summarize(result));
			return;
		}
		if (!result.bearable().isEmpty()) {
			dialog.setErrorMessage(null);
			dialog.setMessage(summarize(result), IMessageProvider.WARNING);
			return;
		}
		dialog.setErrorMessage(null);
		dialog.setMessage(summarize(result));
	}

	private String summarize(Diagnostic diagnostic) {
		return diagnostic.toString();
	}

}
