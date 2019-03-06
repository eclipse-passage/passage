/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.jface.actions;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.Action;
import org.eclipse.passage.lic.jface.LicensingWidgets;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

public class LicensedAction extends Action {

	@Override
	public void runWithEvent(Event event) {
		Shell shell = event.display.getActiveShell();
		IStatus validated = LicensingWidgets.validateLicense(shell, getId());
		if (!validated.isOK()) {
			return;
		}
		super.runWithEvent(event);
	}

}
