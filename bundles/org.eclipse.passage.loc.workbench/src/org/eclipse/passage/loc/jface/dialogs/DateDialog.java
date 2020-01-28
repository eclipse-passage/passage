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
package org.eclipse.passage.loc.jface.dialogs;

import java.time.LocalDate;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Shell;

public class DateDialog extends Dialog {

	private DateTime calendar;
	private LocalDate selected;

	public DateDialog(Shell shell, LocalDate initial) {
		super(shell);
		this.selected = initial != null ? initial : LocalDate.now();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		calendar = new DateTime(area, SWT.CALENDAR | SWT.BORDER);
		showLocalDate(selected);
		return area;
	}

	protected LocalDate collectLocalDateTime() {
		int year = calendar.getYear();
		int month = calendar.getMonth();
		int day = calendar.getDay();
		return LocalDate.of(year, month + 1, day);
	}

	protected void showLocalDate(LocalDate localDate) {
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		calendar.setDate(year, month - 1, day);
	}

	@Override
	protected void okPressed() {
		selected = collectLocalDateTime();
		super.okPressed();
	}

	public LocalDate getSelected() {
		return selected;
	}

}
