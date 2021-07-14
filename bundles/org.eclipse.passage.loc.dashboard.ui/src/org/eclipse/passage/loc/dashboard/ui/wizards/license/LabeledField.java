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

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

abstract class LabeledField<T> implements Field<T> {

	protected final Optional<T> source;
	protected final Runnable modified;
	protected final LabelProvider labels;
	protected final MandatoryService context;
	protected Control widget;
	private Shell shell;
	private Label label;

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
		return Optional.ofNullable(widget).flatMap(w -> Optional.ofNullable((T) w.getData()));
	}

	private void installLabel(Composite parent) {
		label = new Label(parent, SWT.NONE);
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

	@Override
	public final Optional<String> errorIfAny() {
		return widget.isEnabled() ? error() : Optional.empty();
	}

	@Override
	public final void enable(boolean enable) {
		label.setEnabled(enable);
		widget.setEnabled(enable);
		enableAuxiliaryControls(enable);
	}

	protected abstract String label();

	protected abstract Control control(Composite parent);

	protected abstract void reflectData(T data);

	protected abstract Optional<String> error();

	protected abstract void enableAuxiliaryControls(boolean enable);

}
