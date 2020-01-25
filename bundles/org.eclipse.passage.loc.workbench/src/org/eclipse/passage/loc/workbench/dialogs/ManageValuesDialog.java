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
package org.eclipse.passage.loc.workbench.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;

public abstract class ManageValuesDialog<V> extends Dialog {

	V resultValue;

	public ManageValuesDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setSize(400, 500);
	}

	public V getResultValue() {
		return resultValue;
	}

	protected abstract V prepareResultValue();

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == Dialog.OK) {
			resultValue = prepareResultValue();
		}
		super.buttonPressed(buttonId);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}
}
