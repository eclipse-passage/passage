/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.loc.dashboard.ui.wizards.license;

import java.time.LocalDate;
import java.util.Optional;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.passage.loc.jface.dialogs.DateDialog;
import org.eclipse.swt.widgets.Text;

public final class DateField extends SelectableField<LocalDate> {

	private final String name;

	DateField(LocalDate initial, String name, Runnable modified, LabelProvider labels, MandatoryService context) {
		super(Optional.of(initial), modified, labels, context);
		this.name = name;
	}

	@Override
	protected String label() {
		return name;
	}

	@Override
	protected String errorText() {
		return String.format(IssueLicensePageMessages.IssueLicenseRequestPage_e_no_date, name);
	}

	@Override
	protected Optional<LocalDate> select(Text control) {
		LocalDate current = source.get();
		Object data = control.getData();
		if (data instanceof LocalDate) {
			current = (LocalDate) data;
		}
		DateDialog dialog = new DateDialog(shell(), current);
		dialog.create();
		dialog.getShell().setText(name);
		if (dialog.open() == Window.OK) {
			current = dialog.getSelected();
			control.setData(current);
		}
		return Optional.of(current);
	}

}
