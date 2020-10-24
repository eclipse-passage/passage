/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
import org.eclipse.passage.lic.internal.api.MandatoryService;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

public final class CapacityField extends LabeledField<Integer> {

	private final String name;
	private Spinner spinner;

	CapacityField(String name, Runnable modified, LabelProvider labels, MandatoryService context) {
		super(Optional.of(1), modified, labels, context);
		this.name = name;
	}

	@Override
	protected String label() {
		return name;
	}

	@Override
	public Optional<String> error() {
		Optional<Integer> current = data();
		if (!current.isPresent()) {
			return errorMessage();
		}
		if (candidateIsValid(current.get())) {
			return Optional.empty();
		}
		return errorMessage();
	}

	private boolean candidateIsValid(int candidate) {
		return candidate > 0;
	}

	@Override
	protected Widget control(Composite parent) {
		spinner = new Spinner(parent, SWT.BORDER);
		spinner.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		spinner.setMinimum(1);
		spinner.setIncrement(1);
		spinner.setPageIncrement(10);
		spinner.setMaximum(Integer.MAX_VALUE);
		return spinner;
	}

	@Override
	protected void reflectData(Integer data) {
		spinner.setSelection(data);
	}

	private Optional<String> errorMessage() {
		return Optional.of(IssueLicensePageMessages.IssueLicenseRequestPage_e_invalid_capacity);
	}

}
