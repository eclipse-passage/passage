/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.loc.workbench.emfforms.renderers;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.core.swt.SimpleControlSWTControlSWTRenderer;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedException;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedReport;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class WithComboRenderer extends SimpleControlSWTControlSWTRenderer {

	protected Combo combo;

	@Inject
	public WithComboRenderer(VControl element, ViewModelContext context, ReportService report,
			EMFFormsDatabinding databinding, EMFFormsLabelProvider labels, VTViewTemplateProvider templates) {
		super(element, context, report, databinding, labels, templates);
	}

	@Override
	protected final Binding[] createBindings(Control control) throws DatabindingFailedException {
		DataBindingContext context = getDataBindingContext();
		ISWTObservableValue<String> observed = WidgetProperties.comboSelection().observe(combo);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Binding binding = context.bindValue(observed, getModelValue(), withPreSetValidation(new UpdateValueStrategy()),
				null);
		return new Binding[] { binding };
	}

	protected final void createCombo(Composite parent) {
		combo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		combo.setBackground(parent.getBackground());
		installValues();
	}

	private final void installValues() {
		List<String> values = getDefinedValues();
		combo.setItems(values.toArray(new String[values.size()]));
		selectCurrentValue(values);
	}

	private void selectCurrentValue(List<String> values) {
		String current = getCurrentValue();
		if (current == null || current.isEmpty()) {
			combo.select(0);
		} else {
			combo.select(values.indexOf(current));
		}
	}

	@Override
	protected void dispose() {
		if (combo != null) {
			combo.dispose();
		}
		super.dispose();
	}

	protected abstract List<String> getDefinedValues();

	protected final String getCurrentValue() {
		try {
			Object value = getModelValue().getValue();
			if (value instanceof String) {
				return (String) value;
			}
		} catch (DatabindingFailedException e) {
			getReportService().report(new DatabindingFailedReport(e));
		}
		return getUnsetText();
	}

}
