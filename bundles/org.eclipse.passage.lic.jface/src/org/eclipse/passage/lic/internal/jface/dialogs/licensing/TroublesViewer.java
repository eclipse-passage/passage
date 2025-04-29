/*******************************************************************************
 * Copyright (c) 2025 ArSysOp
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

import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.base.diagnostic.SumOfLists;
import org.eclipse.passage.lic.internal.jface.i18n.DiagnosticDialogMessages;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public final class TroublesViewer {

	private final Diagnostic diagnostic;
	private final DiagnosticColors colors;
	private TableViewer viewer;

	public TroublesViewer(Shell shell, Diagnostic diagnostic) {
		this.diagnostic = diagnostic;
		this.colors = new DiagnosticColors(shell == null ? Display.getCurrent() : shell.getDisplay());
	}

	public void installControl(Composite parent) {
		installControl(parent, List.of(//
				new SimpleViewerColumn<>(DiagnosticDialogMessages.DiagnosticDialog_column_details, 900,
						Trouble::details), //
				new SimpleViewerColumn<>(DiagnosticDialogMessages.DiagnosticDialog_column_code, 50,
						trouble -> Integer.toString(trouble.code().code())), //
				new SimpleViewerColumn<>(DiagnosticDialogMessages.DiagnosticDialog_column_type, 250,
						trouble -> trouble.code().explanation())//
		));
	}

	public void installControl(Composite parent, List<SimpleViewerColumn<Trouble>> columns) {
		viewer = new HereTable<>(parent, Trouble.class, this::backdround) //
				.withColumns(columns) //
				.viewer();
	}

	public void installInput() {
		viewer.setInput(new SumOfLists<Trouble>().apply(diagnostic.severe(), diagnostic.bearable()));
	}

	public TableViewer viewer() {
		return viewer;
	}

	private Color backdround(Object element, int column) {
		if (column != 1) {
			return null; // framework driven null
		}
		boolean failure = ((Trouble) element).exception().isPresent();
		boolean severe = diagnostic.severe().contains(element);
		return colors.get(severe, failure);
	}

}
