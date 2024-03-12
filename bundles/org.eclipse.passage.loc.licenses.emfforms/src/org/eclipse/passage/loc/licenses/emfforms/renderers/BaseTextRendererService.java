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
 *     ArSysOp - further support
 *******************************************************************************/
package org.eclipse.passage.loc.licenses.emfforms.renderers;

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.spi.model.VElement;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedException;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedReport;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.swt.core.AbstractSWTRenderer;
import org.eclipse.emfforms.spi.swt.core.di.EMFFormsDIRendererService;
import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;
import org.eclipse.passage.lic.products.model.meta.ProductsPackage;
import org.eclipse.passage.loc.workbench.emfforms.renderers.ValidatedTextRenderer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public final class BaseTextRendererService implements EMFFormsDIRendererService<VControl> {

	private EMFFormsDatabinding databinding;
	private ReportService report;

	@Reference
	public void bindEMFFormsDatabinding(EMFFormsDatabinding service) {
		this.databinding = service;
	}

	public void unbindEMFFormsDatabinding(EMFFormsDatabinding service) {
		if (this.databinding == service) {
			this.databinding = null;
		}
	}

	@Reference
	public void bindReportService(ReportService service) {
		this.report = service;
	}

	public void unbindReportService(ReportService service) {
		if (this.report == service) {
			this.report = null;
		}
	}

	@Override
	public double isApplicable(VElement vElement, ViewModelContext viewModelContext) {
		if (!VControl.class.isInstance(vElement)) {
			return NOT_APPLICABLE;
		}
		final VControl control = (VControl) vElement;
		if (control.getDomainModelReference() == null) {
			return NOT_APPLICABLE;
		}

		@SuppressWarnings("rawtypes")
		IValueProperty valueProperty;
		try {
			valueProperty = databinding.getValueProperty(control.getDomainModelReference(),
					viewModelContext.getDomainModel());
		} catch (final DatabindingFailedException ex) {
			report.report(new DatabindingFailedReport(ex));
			return NOT_APPLICABLE;
		}
		Object valueType = valueProperty.getValueType();
		final EStructuralFeature eStructuralFeature = EStructuralFeature.class.cast(valueType);

		if (FeaturesPackage.eINSTANCE.getFeatureVersion_Version().equals(eStructuralFeature)
				|| ProductsPackage.eINSTANCE.getProductLine_Identifier().equals(eStructuralFeature)
				|| ProductsPackage.eINSTANCE.getProduct_Identifier().equals(eStructuralFeature)
				|| ProductsPackage.eINSTANCE.getProduct_Name().equals(eStructuralFeature)
				|| ProductsPackage.eINSTANCE.getProductVersion_Version().equals(eStructuralFeature)) {
			return 9;
		}

		return NOT_APPLICABLE;
	}

	@Override
	public Class<? extends AbstractSWTRenderer<VControl>> getRendererClass() {
		return ValidatedTextRenderer.class;
	}

}
