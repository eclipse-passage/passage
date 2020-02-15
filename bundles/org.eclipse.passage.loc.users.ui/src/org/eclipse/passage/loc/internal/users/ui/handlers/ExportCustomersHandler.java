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
package org.eclipse.passage.loc.internal.users.ui.handlers;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.registry.UserRegistry;
import org.eclipse.swt.widgets.Shell;

public class ExportCustomersHandler {

	@Execute
	public void execute(IEclipseContext eclipseContext) {
		UserRegistry userRegistry = eclipseContext.get(UserRegistry.class);
		Iterable<? extends UserDescriptor> users = userRegistry.getUsers();
		MessageDialog.openInformation(eclipseContext.get(Shell.class), UserRegistry.class.getName(), users.toString());
	}
}
