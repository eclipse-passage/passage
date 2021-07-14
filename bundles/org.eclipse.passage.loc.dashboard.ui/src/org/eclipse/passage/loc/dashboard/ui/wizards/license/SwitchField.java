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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

final class SwitchField extends LabeledField<Boolean> {

	private final String name;
	private Button switcher;

	protected SwitchField(String name, boolean value, Runnable modified, LabelProvider labels,
			MandatoryService context) {
		super(Optional.of(value), modified, labels, context);
		this.name = name;
	}

	@Override
	public Optional<String> error() {
		return Optional.empty();
	}

	@Override
	protected Control control(Composite parent) {
		installSwitch(parent);
		return switcher;
	}

	@Override
	protected void reflectData(Boolean data) {
		switcher.setSelection(data);
	}

	@Override
	protected void enableAuxiliaryControls(boolean enable) {
		// do nothing
	}

	private void installSwitch(Composite parent) {
		switcher = new Button(parent, SWT.CHECK);
		switcher.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> changeState()));
		switcher.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		new Label(parent, SWT.NONE).setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
	}

	private void changeState() {
		switcher.setData(switcher.getSelection());
		modified.run();
	}

	@Override
	protected String label() {
		return name;
	}

	protected String errorText() {
		throw new UnsupportedOperationException();
	}

}
