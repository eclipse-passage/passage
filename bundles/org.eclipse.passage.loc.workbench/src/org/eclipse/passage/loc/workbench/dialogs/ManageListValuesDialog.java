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
package org.eclipse.passage.loc.workbench.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ManageListValuesDialog extends Dialog {

	private static final String SPLITTER = ";";
	
	List lstItems;
	String expressionValues;
	Text txtEditItem;
	Button btnAddItem;
	String resultExpressionValues;

	public ManageListValuesDialog(Shell parentShell, String values) {
		super(parentShell);
		this.expressionValues = values;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Condition Expession");
		shell.setSize(400, 500);
	}

	public String getResultValues() {
		return resultExpressionValues;
	}

	private String prepareResultValues() {
		StringBuilder builder = new StringBuilder();
		for (String item : lstItems.getItems()) {
			if (builder.length() > 0) {
				builder.append(SPLITTER);
			}
			builder.append(item);
		}
		return builder.toString();
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (buttonId == Dialog.OK) {
			resultExpressionValues = prepareResultValues();
		}
		super.buttonPressed(buttonId);
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		parent.setLayout(new GridLayout(1, false));
		Composite base = new Composite(parent, SWT.NONE);
		base.setLayout(new GridLayout(3, false));
		base.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		Label lblTitle = new Label(base, SWT.NONE);
		lblTitle.setText("Condition Expession values:");
		lblTitle.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));
		lstItems = new List(base, SWT.BORDER);
		lstItems.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));

		GridData text = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		Text txtExpressionItem = new Text(base, SWT.BORDER);
		txtExpressionItem.setLayoutData(text);
		txtExpressionItem.setText("");
		GridData btnGridLayout = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		btnGridLayout.heightHint = 30;
		btnGridLayout.widthHint = 70;

		Button btnAdd = new Button(base, SWT.PUSH);
		btnAdd.setText("Add");
		btnAdd.setLayoutData(btnGridLayout);
		btnAdd.getFont().getFontData()[0].setHeight(10);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String textExpression = txtExpressionItem.getText();
				if (!textExpression.isEmpty()) {
					lstItems.add(textExpression);
					txtExpressionItem.setText("");
				}
			}
		});
		Button btnRemove = new Button(base, SWT.PUSH);
		btnRemove.setText("Remove");
		btnRemove.setLayoutData(btnGridLayout);
		btnRemove.getFont().getFontData()[0].setHeight(10);
		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = lstItems.getSelectionIndex();
				if (selectionIndex > -1) {
					lstItems.remove(selectionIndex);
					txtExpressionItem.setText("");
				}
			}
		});

		if (expressionValues != null && !expressionValues.isEmpty()) {
			String[] splitedVAlues = expressionValues.split(SPLITTER);
			lstItems.setItems(splitedVAlues);
		}

		lstItems.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String selectedValue = lstItems.getItems()[lstItems.getSelectionIndex()];
				txtExpressionItem.setText(selectedValue);
			}
		});

		return parent;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}
}
