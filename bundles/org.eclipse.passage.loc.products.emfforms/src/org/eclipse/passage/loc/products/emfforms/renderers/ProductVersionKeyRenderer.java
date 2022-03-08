/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.inject.Inject;

import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.passage.lic.base.BaseLicensedProduct;
import org.eclipse.passage.lic.base.io.FileNameFromLicensedProduct;
import org.eclipse.passage.lic.base.io.UserHomeProductResidence;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.loc.internal.api.OperatorGearSupplier;
import org.eclipse.passage.loc.internal.api.workspace.Keys;
import org.eclipse.passage.loc.internal.api.workspace.OperatorWorkspace;
import org.eclipse.passage.loc.workbench.emfforms.renderers.FileContentRenderer;

public abstract class ProductVersionKeyRenderer extends FileContentRenderer<ProductVersion> {

	protected final OperatorWorkspace workspace;

	@Inject
	public ProductVersionKeyRenderer(VControl vElement, ViewModelContext viewContext, ReportService reportService,
			EMFFormsDatabinding emfFormsDatabinding, EMFFormsLabelProvider emfFormsLabelProvider,
			VTViewTemplateProvider vtViewTemplateProvider, OperatorGearSupplier gear) {
		super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider,
				ProductVersion.class);
		this.workspace = gear.gear().workspace();
	}

	@Override
	protected String extractFilePath(String value, ProductVersion observed) {
		BaseLicensedProduct product = new BaseLicensedProduct(//
				observed.getProduct().getIdentifier(), observed.getVersion());
		Path dir = new Keys.Smart(workspace.keys()).existing(product)//
				.map(Paths::get)//
				.map(Path::getParent)//
				.orElseGet(new UserHomeProductResidence(product));
		return dir.resolve(new FileNameFromLicensedProduct(product, this::getFileExtension).get()).toString();
	}

	protected abstract String getFileExtension();

}
