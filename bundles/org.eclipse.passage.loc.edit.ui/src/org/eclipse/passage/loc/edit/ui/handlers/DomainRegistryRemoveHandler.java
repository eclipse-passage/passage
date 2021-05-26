/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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

import java.util.Objects;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.passage.loc.internal.emf.EditingDomainRegistryAccess;
import org.eclipse.passage.loc.internal.workbench.LocDomainRegistryAccess;
import org.eclipse.passage.loc.internal.workbench.registry.UnregisterConfirmation;
import org.eclipse.passage.loc.internal.workbench.registry.UnregisterSelected;
import org.eclipse.passage.loc.internal.workbench.registry.UnregisterUri;
import org.eclipse.swt.widgets.Shell;

public class DomainRegistryRemoveHandler {

	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) Object selection) {
		return Objects.nonNull(selection);
	}

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) Object selection, IEclipseContext context) {
		new UnregisterSelected(//
				new UnregisterUri((LocDomainRegistryAccess) context.get(EditingDomainRegistryAccess.class)), //
				new UnregisterConfirmation(() -> context.get(Shell.class))//
		).unregister(selection);
	}

}
