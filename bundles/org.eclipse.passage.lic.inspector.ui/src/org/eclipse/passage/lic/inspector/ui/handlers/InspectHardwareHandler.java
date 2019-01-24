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
package org.eclipse.passage.lic.inspector.ui.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.passage.lic.inspector.HardwareInspector;
import org.eclipse.passage.lic.inspector.ui.dialogs.HardwareInspectorDialog;
import org.eclipse.passage.lic.jface.LicensingImages;
import org.eclipse.swt.widgets.Shell;

public class InspectHardwareHandler {

	@Execute
	public void execute(LicensingImages images, HardwareInspector inspector, Shell shell) {
		HardwareInspectorDialog dialog = new HardwareInspectorDialog(images, inspector, shell);
		dialog.open();
	}
		
}