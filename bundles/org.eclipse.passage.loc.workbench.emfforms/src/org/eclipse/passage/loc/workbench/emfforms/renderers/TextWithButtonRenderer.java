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

import javax.inject.Inject;

import org.eclipse.core.databinding.Binding;
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
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.passage.lic.jface.resource.LicensingColors;
import org.eclipse.passage.loc.jface.LocImages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public abstract class TextWithButtonRenderer extends SimpleControlSWTControlSWTRenderer {

	protected Composite base;
	protected Text text;
	protected Button button;
	
	private final LicensingColors licensingColors;
	private final LocImages locImages;

	@Inject
	public TextWithButtonRenderer(VControl vElement, ViewModelContext viewContext, ReportService reportService,
			EMFFormsDatabinding emfFormsDatabinding, EMFFormsLabelProvider emfFormsLabelProvider,
			VTViewTemplateProvider vtViewTemplateProvider) {
		super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider);
		this.licensingColors = viewContext.getService(LicensingColors.class);
		this.locImages = viewContext.getService(LocImages.class);
	}
	
	public LicensingColors getLicensingColors() {
		return licensingColors;
	}
	
	public LocImages getLocImages() {
		return locImages;
	}

	@Override
	protected Binding[] createBindings(Control control) throws DatabindingFailedException {
		if (control instanceof Text) {
			ISWTObservableValue observe = WidgetProperties.text(SWT.Modify).observe(control);
			UpdateValueStrategy target2model = withPreSetValidation(new UpdateValueStrategy());
			final Binding binding = getDataBindingContext().bindValue(observe, getModelValue(),
					target2model, null);
			return new Binding[] { binding };
		}

		return new Binding[] {};
	}

	@Override
	protected Control createSWTControl(Composite parent) {
		base = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		base.setLayout(layout);
		base.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		base.setFont(parent.getFont());

		text = createText(base);
		button = createButton(base);
		return text;
	}

	protected Text createText(Composite parent) {
		Text text = new Text(parent, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		text.setText(getCurrentValue());
		text.setEditable(false);
		return text;
	}

	protected Button createButton(Composite parent) {
		Button button = new Button(parent, SWT.PUSH);
		button.setText("Edit...");
		button.setImage(locImages.getImage(LocImages.IMG_TOOL_EDIT));
		button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		return button;
	}
	
	@Override
	protected void dispose() {
		if (base != null && !base.isDisposed()) {
			for (Control control : base.getChildren()) {
				if (control != null) {
					control.dispose();
				}
			}
		}
		super.dispose();
	}

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

	@Override
	protected void setValidationColor(Control control, Color validationColor) {
		if (control instanceof Text) {
			Text textControl = ((Text) control);
			if (textControl.getText().isEmpty()) {
				control.setBackground(licensingColors.getColor(LicensingColors.COLOR_VALIDATION_ERROR));
			} else {
				control.setBackground(licensingColors.getColor(LicensingColors.COLOR_VALIDATION_OK));
			}
		}
	}

}
