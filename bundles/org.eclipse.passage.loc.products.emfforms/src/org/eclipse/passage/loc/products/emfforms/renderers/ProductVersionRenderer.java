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
package org.eclipse.passage.loc.products.emfforms.renderers;

import javax.inject.Inject;

import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.passage.lic.emf.edit.ComposedAdapterFactoryProvider;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.products.ProductsRegistry;
import org.eclipse.passage.loc.products.ui.ProductsUi;
import org.eclipse.passage.loc.workbench.emfforms.renderers.TextWithButtonRenderer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ProductVersionRenderer extends TextWithButtonRenderer {

	private static final String IDENTIFIER_EMPTY = ""; //$NON-NLS-1$

	private final ProductsRegistry registry;
	private final ComposedAdapterFactoryProvider provider;

	@Inject
	public ProductVersionRenderer(VControl vElement, ViewModelContext viewContext, ReportService reportService,
			EMFFormsDatabinding emfFormsDatabinding, EMFFormsLabelProvider emfFormsLabelProvider,
			VTViewTemplateProvider vtViewTemplateProvider) {
		super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider);
		registry = viewContext.getService(ProductsRegistry.class);
		provider = viewContext.getService(ComposedAdapterFactoryProvider.class);
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
		ProductVersionDescriptor initial = null;
		ProductVersionDescriptor descriptor = ProductsUi.selectProductVersionDescriptor(shell, provider,
				registry, initial);
		if (descriptor != null) {
			String identifier = descriptor.getVersion();
			if (identifier != null) {
				text.setText(identifier);
			}
		}
	}

}
