/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
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
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public abstract class ComboControlRenderer extends SimpleControlSWTControlSWTRenderer {

	private Combo combo;

	@Inject
	public ComboControlRenderer(VControl vElement, ViewModelContext viewContext, ReportService reportService,
			EMFFormsDatabinding emfFormsDatabinding, EMFFormsLabelProvider emfFormsLabelProvider,
			VTViewTemplateProvider vtViewTemplateProvider) {
		super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider);

	}

	@Override
	protected Binding[] createBindings(Control control) throws DatabindingFailedException {
		DataBindingContext context = getDataBindingContext();
		ISWTObservableValue<String> observed = WidgetProperties.comboSelection().observe(combo);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		final Binding binding = context.bindValue(observed, getModelValue(),
				withPreSetValidation(new UpdateValueStrategy()), null);
		return new Binding[] { binding };
	}

	@Override
	protected Control createSWTControl(Composite parent) {
		combo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(false, false).span(2, 1).applyTo(combo);
		List<String> definedValues = getDefinedValues();
		String[] array = definedValues.toArray(new String[definedValues.size()]);
		combo.setItems(array);
		String currentValue = getCurrentValue();
		if (currentValue == null || currentValue.isEmpty()) {
			combo.select(0);
		} else {
			int curIndex = definedValues.indexOf(currentValue);
			combo.select(curIndex);
		}

		combo.setBackground(Display.getDefault().getSystemColor(SWT.BACKGROUND));
		return combo;
	}

	@Override
	protected void dispose() {
		if (combo != null) {
			combo.dispose();
		}
		super.dispose();
	}

	protected abstract List<String> getDefinedValues();

	protected String getCurrentValue() {
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
