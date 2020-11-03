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
package org.eclipse.passage.lic.internal.e4.ui.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.passage.lic.internal.jface.dialogs.licensing.EnvironmentStateDialog;
import org.eclipse.swt.widgets.Shell;

public final class InspectEnvironmentHandler {

	@Execute
	public void execute(Shell shell) {
		new EnvironmentStateDialog(shell).open();
	}

}
