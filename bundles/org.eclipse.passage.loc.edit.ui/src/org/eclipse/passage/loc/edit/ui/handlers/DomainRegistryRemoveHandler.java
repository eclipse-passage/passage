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
package org.eclipse.passage.loc.edit.ui.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.passage.lic.emf.edit.BaseDomainRegistry;
import org.eclipse.passage.lic.emf.edit.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.edit.ui.i18n.EditUiMessages;
import org.eclipse.passage.loc.internal.workbench.LocDomainRegistryAccess;
import org.eclipse.swt.widgets.Shell;

//FIXME: rewrite to avoid restriction warnings
@SuppressWarnings("restriction")
public class DomainRegistryRemoveHandler {

	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional Resource resource) {
		return resource != null;
	}

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) Resource resource, IEclipseContext context) {
		URI uri = resource.getURI();
		if (uri != null) {
			LocDomainRegistryAccess access = (LocDomainRegistryAccess) context.get(EditingDomainRegistryAccess.class);
			access.domainRegistryForExtension(uri.fileExtension())//
					.filter(BaseDomainRegistry.class::isInstance)//
					.map(BaseDomainRegistry.class::cast)//
					.ifPresent(r -> unregister(r, uri, context.get(Shell.class)));
		}
	}

	private void unregister(BaseDomainRegistry<?> registry, URI uri, Shell shell) {
		String message = String.format(EditUiMessages.DomainRegistryRemoveHandler_mesage, uri.toFileString());
		String title = EditUiMessages.DomainRegistryRemoveHandler_title;
		if (MessageDialog.openConfirm(shell, title, message)) {
			registry.unregisterSource(uri.toFileString());
		}
	}

}
