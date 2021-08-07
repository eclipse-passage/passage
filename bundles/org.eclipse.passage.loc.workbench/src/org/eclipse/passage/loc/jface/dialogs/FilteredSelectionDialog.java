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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.passage.loc.internal.workbench.i18n.WorkbenchMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class FilteredSelectionDialog<T> extends ObjectSelectionStatusDialog<T> {

	private static final int DIALOG_HEIGHT = 500;
	private static final int DIALOG_WIDTH = 400;
	private final boolean multi;
	private Image image;
	private Text filteringField;
	private Label resultingField;
	private TableViewer tableViewItems;
	private final List<Object> input = new ArrayList<>();

	private LabelProvider labelProvider = new LabelProvider();
	private ViewerSearchFilter<?> filter;

	public FilteredSelectionDialog(Shell parent, boolean multi, ViewerSearchFilter<?> filter) {
		super(parent);
		this.multi = multi;
		this.filter = filter;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setInput(Iterable<?> objects) {
		input.clear();
		objects.forEach(input::add);

	}

	public void setLabelProvider(LabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	@Override
	protected void computeResult() {
		@SuppressWarnings("unchecked")
		List<T> selectedElements = tableViewItems.getStructuredSelection().toList();
		setResult(selectedElements);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);

		Composite content = new Composite(area, SWT.NONE);
		content.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label lblFilteringField = new Label(content, SWT.NONE);
		{
			GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
			lblFilteringField.setLayoutData(data);
			lblFilteringField.setText(WorkbenchMessages.FilteredSelectionDialog_filtering_label);
		}

		filteringField = new Text(content, SWT.BORDER);
		{
			GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
			filteringField.setLayoutData(data);

		}

		filteringField.addKeyListener(new KeyListener() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (filter != null) {
					filter.setFilteringText(getFilteringField());
					tableViewItems.refresh();
					Table table = tableViewItems.getTable();
					if (table.getItemCount() > 0) {
						TableItem item = table.getItem(0);
						if (item != null) {
							resultingField.setText(item.getText());
							table.setSelection(item);
						}
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		content.setLayout(layout);

		Label lblListItems = new Label(content, SWT.NONE);
		{
			GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
			lblListItems.setLayoutData(data);
			lblListItems.setText(WorkbenchMessages.FilteredSelectionDialog_items_label);
		}

		tableViewItems = new TableViewer(content, (multi ? SWT.MULTI : SWT.SINGLE) | SWT.BORDER | SWT.V_SCROLL);
		tableViewItems.setContentProvider(ArrayContentProvider.getInstance());
		tableViewItems.setLabelProvider(labelProvider);
		tableViewItems.setInput(input);
		tableViewItems.setSelection(new StructuredSelection(getInitialSelection().toArray()));
		tableViewItems.getTable().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = tableViewItems.getTable().getSelectionIndex();
				TableItem item = tableViewItems.getTable().getItem(selectionIndex);
				if (item != null) {
					resultingField.setText(item.getText());
				}
			}
		});
		if (filter != null) {
			tableViewItems.setFilters(filter);
		}

		GridData gd = new GridData(GridData.FILL_BOTH);
		applyDialogFont(tableViewItems.getTable());
		gd.heightHint = tableViewItems.getTable().getItemHeight() * 15;
		tableViewItems.getTable().setLayoutData(gd);
		tableViewItems.addDoubleClickListener(event -> handleDoubleClick());

		resultingField = new Label(content, SWT.BORDER);
		{
			GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
			resultingField.setLayoutData(data);

		}

		applyDialogFont(content);
		return area;
	}

	protected void handleDoubleClick() {
		okPressed();
	}

	public String getFilteringField() {
		return filteringField.getText();
	}

	@Override
	protected void configureShell(Shell shell) {
		shell.setMinimumSize(DIALOG_WIDTH, DIALOG_HEIGHT);
		if (image != null) {
			shell.setImage(image);
		}
		super.configureShell(shell);
	}
}
