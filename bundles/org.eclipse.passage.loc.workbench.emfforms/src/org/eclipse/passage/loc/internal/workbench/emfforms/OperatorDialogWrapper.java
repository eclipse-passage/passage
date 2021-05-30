/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.loc.internal.workbench.emfforms;

import org.eclipse.emf.ecp.edit.internal.swt.util.DialogWrapper;
import org.eclipse.emf.ecp.edit.spi.swt.util.ECPDialogExecutor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;

@SuppressWarnings("restriction")
public class OperatorDialogWrapper implements DialogWrapper {

	@Override
	public void openDialog(Dialog dialog, ECPDialogExecutor callBack) {
		dialog.create();
		Shell shell = dialog.getShell();
		shell.setSize(Math.max(1000, shell.getSize().x), Math.max(600, shell.getSize().y));
		callBack.handleResult(dialog.open());
	}

}
