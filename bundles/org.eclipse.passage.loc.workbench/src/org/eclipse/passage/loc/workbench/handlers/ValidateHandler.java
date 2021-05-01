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
package org.eclipse.passage.loc.workbench.handlers;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.passage.loc.internal.emf.LabeledDiagnostician;
import org.eclipse.swt.widgets.Shell;

public class ValidateHandler {

	@Execute
	public void execute(ESelectionService selectionService, Shell shell, IEclipseContext context) {
		Object selection = selectionService.getSelection();
		if (selection instanceof EObject) {
			EObject eObject = (EObject) selection;
			Diagnostician diagnostician = new LabeledDiagnostician();
			Diagnostic validate = diagnostician.validate(eObject);
			handleDiagnostic(validate, shell);
		}
	}

	@CanExecute
	public boolean canExecute(ESelectionService selectionService) {
		Object selection = selectionService.getSelection();
		if (selection instanceof EObject) {
			return true;
		}
		return false;
	}

	protected void handleDiagnostic(Diagnostic diagnostic, Shell shell) {
		int severity = diagnostic.getSeverity();
		String title = null;
		String message = null;

		if (severity == Diagnostic.ERROR || severity == Diagnostic.WARNING) {
			title = EMFEditUIPlugin.INSTANCE.getString("_UI_ValidationProblems_title"); //$NON-NLS-1$
			message = EMFEditUIPlugin.INSTANCE.getString("_UI_ValidationProblems_message"); //$NON-NLS-1$
		} else {
			title = EMFEditUIPlugin.INSTANCE.getString("_UI_ValidationResults_title"); //$NON-NLS-1$
			message = EMFEditUIPlugin.INSTANCE.getString(
					severity == Diagnostic.OK ? "_UI_ValidationOK_message" : "_UI_ValidationResults_message"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (diagnostic.getSeverity() == Diagnostic.OK) {
			MessageDialog.openInformation(shell, title, message);
		} else {
			IStatus status = BasicDiagnostic.toIStatus(diagnostic);
			ErrorDialog.openError(shell, title, message, status);
		}
	}
}
