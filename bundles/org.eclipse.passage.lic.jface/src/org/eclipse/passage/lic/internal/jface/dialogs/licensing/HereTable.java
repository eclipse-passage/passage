/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.jface.dialogs.licensing;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

final class HereTable<T> {

	private final TableViewer table;
	private final Class<T> cls;
	private final Map<Integer, Function<T, String>> texts = new HashMap<>();
	private final BiFunction<Object, Integer, Color> background;

	HereTable(Composite parent, Class<T> cls) {
		this(parent, cls, (element, index) -> null); // framework-driven null
	}

	HereTable(Composite parent, Class<T> cls, BiFunction<Object, Integer, Color> background) {
		this.table = new TableViewer(parent);
		this.cls = cls;
		this.background = background;
	}

	public TableViewer viewer() {
		table.setContentProvider(new ArrayContentProvider());
		table.setLabelProvider(new HereLabelProvider<T>(texts, cls, background));
		table.getTable().setHeaderVisible(true);
		table.getTable().setLinesVisible(true);
		table.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		return table;
	}

	public HereTable<T> withColumn(String name, int width, Function<T, String> text) {
		TableViewerColumn column = new TableViewerColumn(table, SWT.NONE);
		setupColumn(column.getColumn(), name, width);
		texts.put(table.getTable().getColumnCount() - 1, text);
		return this;
	}

	public HereTable<T> withColumn(String name, int width, int index, Function<T, String> text) {
		TableViewerColumn column = new TableViewerColumn(table, SWT.NONE, index);
		setupColumn(column.getColumn(), name, width);
		texts.put(index, text);
		return this;
	}

	private void setupColumn(TableColumn column, String name, int width) {
		column.setText(name);
		column.setWidth(width);
		column.setResizable(true);
	}

}
