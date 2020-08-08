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
package org.eclipse.passage.lic.jface.dialogs.licensingstatus;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.passage.lic.jface.viewers.LicensingLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

final class LicensingTable {
	private final TableViewer table;
	private final ILabelProvider labels = new LicensingLabelProvider();

	LicensingTable(Composite parent) {
		this.table = new TableViewer(parent);
	}

	public TableViewer viewer() {
		table.setLabelProvider(labels);
		table.getTable().setHeaderVisible(true);
		table.getTable().setLinesVisible(true);
		return table;
	}

	public LicensingTable withColumn(String name, int width) {
		TableViewerColumn column = new TableViewerColumn(table, SWT.NONE);
		setupColumn(column.getColumn(), name, width);
		return this;
	}

	public LicensingTable withColumn(String name, int width, int index) {
		TableViewerColumn column = new TableViewerColumn(table, SWT.NONE, index);
		setupColumn(column.getColumn(), name, width);
		return this;
	}

	private void setupColumn(TableColumn column, String name, int width) {
		column.setText(name);
		column.setWidth(width);
		column.setResizable(true);
	}

}
