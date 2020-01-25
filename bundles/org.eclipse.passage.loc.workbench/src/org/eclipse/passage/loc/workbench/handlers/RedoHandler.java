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

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.menu.MHandledMenuItem;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.passage.loc.workbench.LocWokbench;

public class RedoHandler {

	@Execute
	public void execute(IEclipseContext context) {
		EditingDomain editingDomain = LocWokbench.extractEditingDomain(context);
		editingDomain.getCommandStack().redo();
	}
	
	
	@CanExecute
	public boolean canExecute(IEclipseContext context, MHandledMenuItem item) {
		EditingDomain editingDomain = LocWokbench.extractEditingDomain(context);
		if (editingDomain == null) {
			return false;
		}
		CommandStack commandStack = editingDomain.getCommandStack();
		if (commandStack == null) {
			return false;
		}
		return commandStack.canRedo();
	}


	protected void updateLabel(IEclipseContext context, MHandledMenuItem item, CommandStack commandStack) {
		String base = WorkbenchMessages.RedoHandler_label_base;
		Command undoCommand = commandStack.getUndoCommand();
		if (undoCommand == null) {
			return;
		}
		String label = undoCommand.getLabel();
		if (label == null) {
			return;
		}
		String bind = NLS.bind(WorkbenchMessages.RedoHandler_label_pattern, base, label);
		item.setLabel(bind);
	}
		
}
