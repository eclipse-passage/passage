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
package org.eclipse.passage.loc.jface.dialogs;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.AbstractSelectionDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.passage.lic.jface.widgets.StatusLine;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public abstract class ObjectSelectionStatusDialog<T> extends AbstractSelectionDialog<T> {

	private StatusLine statusLine;

	private IStatus lastStatus;

	public ObjectSelectionStatusDialog(Shell shell) {
		super(shell);
	}

	protected abstract void computeResult();

	protected void updateStatus(IStatus status) {
		lastStatus = status;
		if (statusLine != null && !statusLine.isDisposed()) {
			updateButtonsEnableState(status);
			statusLine.setStatus(status);
		}
	}

	/**
	 * Update the status of the ok button to reflect the given status. Subclasses
	 * may override this method to update additional buttons.
	 * 
	 * @param status
	 */
	protected void updateButtonsEnableState(IStatus status) {
		Button okButton = getButton(IDialogConstants.OK_ID);
		if (okButton != null && !okButton.isDisposed()) {
			okButton.setEnabled(!status.matches(IStatus.ERROR));
		}
	}

	@Override
	protected void okPressed() {
		computeResult();
		super.okPressed();
	}

	@Override
	public void create() {
		super.create();
		if (lastStatus != null) {
			updateStatus(lastStatus);
		}
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		Font font = parent.getFont();
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginLeft = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.marginWidth = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		composite.setFont(font);

		if (isHelpAvailable()) {
			createHelpControl(composite);
		}
		statusLine = new StatusLine(composite);
		statusLine.setAlignment(SWT.LEFT);
		statusLine.setStatus(null);
		statusLine.setFont(font);
		statusLine.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		boolean helpAvailable = isHelpAvailable();
		setHelpAvailable(false);
		super.createButtonBar(composite);
		setHelpAvailable(helpAvailable);
		return composite;
	}
}