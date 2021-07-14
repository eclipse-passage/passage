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

import java.util.Optional;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Spinner;

abstract class PositiveIntField extends LabeledField<Integer> {

	private final String name;
	private Spinner spinner;

	PositiveIntField(int origin, String name, Runnable modified, LabelProvider labels, MandatoryService context) {
		super(Optional.of(origin), modified, labels, context);
		this.name = name;
	}

	@Override
	protected final String label() {
		return name;
	}

	@Override
	protected final Control control(Composite parent) {
		spinner = new Spinner(parent, SWT.BORDER);
		spinner.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		spinner.setMinimum(1);
		spinner.setIncrement(1);
		spinner.setPageIncrement(10);
		spinner.setMaximum(Integer.MAX_VALUE);
		spinner.addSelectionListener(SelectionListener.widgetSelectedAdapter(//
				event -> installData(Optional.of(spinner.getSelection()))));
		return spinner;
	}

	@Override
	protected final void reflectData(Integer data) {
		spinner.setSelection(data);
	}

	@Override
	public final Optional<String> error() {
		Optional<Integer> current = data();
		if (!current.isPresent()) {
			return Optional.of(errorText());
		}
		if (candidateIsValid(current.get())) {
			return Optional.empty();
		}
		return Optional.of(errorText());
	}

	@Override
	protected void enableAuxiliaryControls(boolean enable) {
		// do nothing
	}

	private boolean candidateIsValid(int candidate) {
		return candidate > 0;
	}

	protected abstract String errorText();
}
