/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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

import javax.inject.Inject;

import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.loc.internal.workbench.emfforms.i18n.WorkbenchEmfformsMessages;
import org.eclipse.passage.loc.workbench.dialogs.ManageTextValuesDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ConditionExpressionRenderer extends TextWithButtonRenderer {

	private static final String EXPRESSION_EMPTY = ""; //$NON-NLS-1$
	private static final String EXPRESSION_SEPARATOR = ";"; //$NON-NLS-1$

	@Inject
	public ConditionExpressionRenderer(VControl vElement, ViewModelContext viewContext, ReportService reportService,
			EMFFormsDatabinding emfFormsDatabinding, EMFFormsLabelProvider emfFormsLabelProvider,
			VTViewTemplateProvider vtViewTemplateProvider) {
		super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider);
	}

	@Override
	protected Control createSWTControl(Composite parent) {
		Control control = super.createSWTControl(parent);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = Display.getDefault().getActiveShell();
				ManageTextValuesDialog dialog = new ManageTextValuesDialog(shell, getCurrentValue(),
						EXPRESSION_SEPARATOR);
				dialog.create();
				Shell dialogShell = dialog.getShell();
				dialogShell.setText(WorkbenchEmfformsMessages.ConditionExpressionRenderer_condition_expression);
				Image image = LicensingImages.getImage(LicensingImages.IMG_DEFAULT);
				dialogShell.setImage(image);
				if (dialog.open() == Dialog.OK) {
					text.setText(dialog.getResultValue());
				}
			}
		});

		return control;
	}

	@Override
	protected String getUnsetText() {
		return EXPRESSION_EMPTY;
	}

}
