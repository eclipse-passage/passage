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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.core.swt.SimpleControlSWTControlSWTRenderer;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.spi.model.reporting.StatusReport;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedException;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.emfforms.spi.core.services.label.NoLabelFoundException;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.passage.lic.jface.resource.LicensingColors;
import org.eclipse.passage.loc.workbench.LocWokbench;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public class ValidatedTextRenderer extends SimpleControlSWTControlSWTRenderer {

	private static final String TEXT_MESSAGE_DEFAULT = ""; //$NON-NLS-1$
	private static final String UNSET_TEXT_DEFAULT = ""; //$NON-NLS-1$

	private LicensingColors licensingColors;
	private Text text;

	@Inject
	public ValidatedTextRenderer(VControl vElement, ViewModelContext viewContext, ReportService reportService,
			EMFFormsDatabinding emfFormsDatabinding, EMFFormsLabelProvider emfFormsLabelProvider,
			VTViewTemplateProvider vtViewTemplateProvider) {
		super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider);
		licensingColors = viewContext.getService(LicensingColors.class);
	}

	@Override
	protected Binding[] createBindings(Control control) throws DatabindingFailedException {
		if (control instanceof Text) {
			final Binding binding = getDataBindingContext().bindValue(WidgetProperties.text(SWT.Modify).observe(text),
					getModelValue(), withPreSetValidation(new UpdateValueStrategy()), null);
			return new Binding[] { binding };
		}

		return new Binding[] {};
	}

	@Override
	protected void setValidationColor(Control control, Color validationColor) {
		if (licensingColors == null) {
			return;
		}
		if (control instanceof Text) {
			Text textControl = ((Text) control);
			if (textControl.getText().isEmpty()) {
				control.setBackground(licensingColors.getColor(LicensingColors.COLOR_VALIDATION_ERROR));
			} else {
				control.setBackground(licensingColors.getColor(LicensingColors.COLOR_VALIDATION_OK));
			}
		}
	}

	@Override
	protected Control createSWTControl(Composite parent) {
		text = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
		final GridData data = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		text.setLayoutData(data);
		text.setBackground(Display.getDefault().getSystemColor(SWT.BACKGROUND));
		text.setText(getTextMessage());
		text.setEditable(true);
		return text;
	}

	@Override
	protected void dispose() {
		licensingColors = null;
		if (text != null) {
			text.dispose();
		}
		super.dispose();
	}

	protected String getTextMessage() {
		try {
			return (String) getEMFFormsLabelProvider()
					.getDisplayName(getVElement().getDomainModelReference(), getViewModelContext().getDomainModel())
					.getValue();
		} catch (final NoLabelFoundException ex) {
			Status status = new Status(IStatus.ERROR, LocWokbench.BUNDLE_SYMBOLIC_NAME, ex.getMessage(), ex);
			getReportService().report(new StatusReport(status));
		}
		return TEXT_MESSAGE_DEFAULT;
	}

	@Override
	protected String getUnsetText() {
		return UNSET_TEXT_DEFAULT;
	}

}
