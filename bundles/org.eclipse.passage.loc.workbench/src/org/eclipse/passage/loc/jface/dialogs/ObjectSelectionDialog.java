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
package org.eclipse.passage.loc.jface.dialogs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class ObjectSelectionDialog extends TrayDialog {

	private final List<Object> result = new ArrayList<>();

	private final List<Object> initial = new ArrayList<>();

	private String title;
	private Image image;

	private String message = ""; //$NON-NLS-1$

	protected ObjectSelectionDialog(Shell parentShell) {
		super(parentShell);
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		if (image != null) {
			shell.setImage(image);
		}
		if (title != null) {
			shell.setText(title);
		}
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	protected Label createMessageArea(Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		if (message != null) {
			label.setText(message);
		}
		label.setFont(composite.getFont());
		return label;
	}

	protected List<Object> getInitial() {
		return initial;
	}

	public Object getFirstResult() {
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	protected void setResult(int position, Object element) {
		result.set(position, element);
	}

	public Iterable<Object> getResult() {
		return result;
	}

	public void setInitial(Object... objects) {
		initial.clear();
		for (Object object : objects) {
			initial.add(object);
		}
	}

	public void setInitial(Iterable<?> objects) {
		initial.clear();
		objects.forEach(initial::add);
	}

	protected void setResult(Iterable<?> objects) {
		result.clear();
		objects.forEach(result::add);
	}

	protected void setResult(Object... objects) {
		result.clear();
		for (Object object : objects) {
			result.add(object);
		}
	}

	@Override
	protected boolean isResizable() {
    	return true;
    }

}
