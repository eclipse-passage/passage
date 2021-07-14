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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

abstract class TextField extends LabeledField<String> {

	private Text text;

	protected TextField(Optional<String> source, Runnable modified, LabelProvider labels, MandatoryService context) {
		super(source, modified, labels, context);
	}

	@Override
	public Optional<String> error() {
		return noData() ? Optional.of(errorText()) : Optional.empty();
	}

	private boolean noData() {
		return data().isEmpty() || data().get().trim().isEmpty();
	}

	@Override
	protected Control control(Composite parent) {
		installText(parent);
		return text;
	}

	@Override
	protected void reflectData(String data) {
		text.setText(labels.getText(data));
	}

	private void installText(Composite parent) {
		text = new Text(parent, SWT.BORDER);
		text.addModifyListener(m -> onModify());
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
	}

	private void onModify() {
		text.setData(text.getText().trim());
		modified.run();
	}

	@Override
	protected void enableAuxiliaryControls(boolean enable) {
		// do nothing
	}

	@Override
	protected abstract String label();

	protected abstract String errorText();

}
