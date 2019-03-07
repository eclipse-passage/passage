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
package org.eclipse.passage.lic.jface.viewers;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class LicensingTableVieweBuilder {

	public static LicensingTableVieweBuilder forTable(Table table) {
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableViewer tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		return new LicensingTableVieweBuilder(tableViewer);
	}

	private final TableViewer tableViewer;

	private ILabelProvider labelProvider = new LicensingLabelProvider();

	private LicensingTableVieweBuilder(TableViewer tableViewer) {
		this.tableViewer = tableViewer;
	}

	public TableViewer getTableViewer() {
		tableViewer.setLabelProvider(labelProvider);
		return tableViewer;
	}

	public LicensingTableVieweBuilder addColumn(String columnName, int width) {
		TableViewerColumn columnViewer = new TableViewerColumn(tableViewer, SWT.NONE);
		setupColumn(columnViewer, columnName, width);
		return this;
	}

	public LicensingTableVieweBuilder addColumn(String columnName, int width, int index) {
		TableViewerColumn columnViewer = new TableViewerColumn(tableViewer, SWT.NONE, index);
		setupColumn(columnViewer, columnName, width);
		return this;
	}

	protected void setupColumn(TableViewerColumn columnViewer, String columnName, int width) {
		TableColumn column = columnViewer.getColumn();
		column.setText(columnName);
		column.setWidth(width);
		column.setResizable(true);
	}

}
