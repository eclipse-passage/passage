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

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.swt.widgets.Shell;

public class ExitWorkbenchHandler {
	@Inject
	private IEclipseContext context;

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell shell, EPartService partService)
			throws ExecutionException {
		if (!partService.saveAll(true)) {
			return;
		}
		if (MessageDialog.openQuestion(shell, WorkbenchMessages.ExitWorkbenchHandler_exit_title, WorkbenchMessages.ExitWorkbenchHandler_exit_message)) {
			Object workbench = context.get(IWorkbench.class.getName());
			if (workbench != null && workbench instanceof IWorkbench) {
				((IWorkbench) workbench).close();
			}
		}
	}
}
