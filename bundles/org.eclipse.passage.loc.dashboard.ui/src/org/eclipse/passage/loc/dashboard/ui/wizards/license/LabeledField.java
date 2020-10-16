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

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.internal.api.MandatoryService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

abstract class LabeledField<T> implements Field<T> {

	protected final Optional<T> source;
	protected final Runnable modified;
	protected final LabelProvider labels;
	protected final MandatoryService context;
	protected Widget widget;
	private Shell shell;

	protected LabeledField(Optional<T> source, Runnable modified, LabelProvider labels, MandatoryService context) {
		this.source = source;
		this.modified = modified;
		this.labels = labels;
		this.context = context;
	}

	@Override
	public final void installControll(Composite parent) {
		shell = parent.getShell();
		installLabel(parent);
		widget = control(parent);
		installData(source);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final Optional<T> data() {
		return Optional.ofNullable((T) widget.getData());
	}

	private void installLabel(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(label());
		label.setLayoutData(GridDataFactory.fillDefaults().create());
	}

	protected final void installData(Optional<T> origin) {
		origin.ifPresent(data -> {
			widget.setData(data);
			reflectData(data);
		});
	}

	protected final Shell shell() {
		return shell;
	}

	protected abstract String label();

	protected abstract Widget control(Composite parent);

	protected abstract void reflectData(T data);
}
