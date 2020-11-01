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

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.internal.api.MandatoryService;
import org.eclipse.passage.loc.internal.dashboard.ui.i18n.IssueLicensePageMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

abstract class TextField<T> extends LabeledField<T> {

	private Text text;

	protected TextField(Optional<T> source, Runnable modified, LabelProvider labels, MandatoryService context) {
		super(source, modified, labels, context);
	}

	@Override
	public Optional<String> error() {
		return data().isEmpty() ? Optional.of(errorMessage()) : Optional.empty();
	}

	@Override
	protected Widget control(Composite parent) {
		installText(parent);
		installSelectButton(parent);
		return text;
	}

	@Override
	protected void reflectData(T data) {
		if (data instanceof Collection) {
			String altogether = StreamSupport.stream(((Collection<?>) data).spliterator(), false)//
					.map(labels::getText)//
					.collect(Collectors.joining(", ")); //$NON-NLS-1$
			text.setText(altogether);
		} else {
			text.setText(labels.getText(data));
		}
	}

	private void installText(Composite parent) {
		text = new Text(parent, SWT.READ_ONLY | SWT.BORDER);
		text.addModifyListener(m -> modified.run());
		text.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
	}

	private void installSelectButton(Composite parent) {
		Button select = new Button(parent, SWT.PUSH);
		select.setText(IssueLicensePageMessages.IssueLicenseRequestPage_btn_select_text);
		select.addSelectionListener(widgetSelectedAdapter(event -> installData(select(text))));
		select.setLayoutData(GridDataFactory.fillDefaults().create());
	}

	@Override
	protected abstract String label();

	protected abstract String errorMessage();

	protected abstract Optional<T> select(Text control);

}
