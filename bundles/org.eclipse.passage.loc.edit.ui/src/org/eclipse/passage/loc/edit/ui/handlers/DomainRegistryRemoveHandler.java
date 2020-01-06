/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.loc.edit.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.passage.loc.internal.edit.ui.i18n.EditUiMessages;
import org.eclipse.swt.widgets.Shell;

public class DomainRegistryRemoveHandler {

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) Resource resource, IEclipseContext context) {
		ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet != null) {
			Shell shell = context.get(Shell.class);
			String message = String.format(EditUiMessages.DomainRegistryRemoveHandler_mesage, resource.getURI());
			String title = EditUiMessages.DomainRegistryRemoveHandler_title;
			if (MessageDialog.openConfirm(shell, title, message)) {
				resource.unload();
				resourceSet.getResources().remove(resource);
			}
		}
	}
	
	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional Resource resource) {
		return resource != null;
	}

}
