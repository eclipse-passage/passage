/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
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
package org.eclipse.passage.loc.products.emfforms.renderers;

import java.util.Optional;

import javax.inject.Inject;

import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedException;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedReport;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.passage.lic.products.model.api.Product;
import org.eclipse.passage.loc.internal.products.ProductRegistry;
import org.eclipse.passage.loc.products.ui.ProductsUi;
import org.eclipse.passage.loc.workbench.emfforms.renderers.TextWithButtonRenderer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ProductIdentifierRenderer extends TextWithButtonRenderer {

	private static final String IDENTIFIER_EMPTY = ""; //$NON-NLS-1$

	private final ProductRegistry registry;

	@Inject
	public ProductIdentifierRenderer(VControl vElement, ViewModelContext viewContext, ReportService reportService,
			EMFFormsDatabinding emfFormsDatabinding, EMFFormsLabelProvider emfFormsLabelProvider,
			VTViewTemplateProvider vtViewTemplateProvider) {
		super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider);
		registry = viewContext.getService(ProductRegistry.class);
	}

	@Override
	protected Control createSWTControl(Composite parent) {
		Control control = super.createSWTControl(parent);
		text.setEditable(true);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectIdentifier();
			}
		});

		return control;
	}

	@Override
	protected String getUnsetText() {
		return IDENTIFIER_EMPTY;
	}

	protected void selectIdentifier() {
		Shell shell = Display.getDefault().getActiveShell();
		Optional<Product> initial = Optional.empty();
		try {
			Object value = getModelValue().getValue();
			if (value instanceof String) {
				String id = (String) value;
				initial = registry.product(id);
			}
		} catch (DatabindingFailedException e) {
			getReportService().report(new DatabindingFailedReport(e));
		}
		Product descriptor = ProductsUi.selectProduct(shell, registry, initial);
		if (descriptor != null) {
			String identifier = descriptor.getIdentifier();
			if (identifier != null) {
				text.setText(identifier);
			}
		}
	}

}
