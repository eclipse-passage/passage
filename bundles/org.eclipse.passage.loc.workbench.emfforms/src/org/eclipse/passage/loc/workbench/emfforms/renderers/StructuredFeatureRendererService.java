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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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

public class StructuredFeatureRendererService implements EMFFormsDIRendererService<VControl> {

	private final Class<? extends AbstractSWTRenderer<VControl>> renderer;
	private final Iterable<EStructuralFeature> features;

	private double priority = 10;

	private EMFFormsDatabinding databindingService;
	private ReportService reportService;

	protected StructuredFeatureRendererService(Class<? extends AbstractSWTRenderer<VControl>> renderer,
			EStructuralFeature... features) {
		this.renderer = renderer;
		this.features = Arrays.asList(features);
	}

	/**
	 * 
	 * @param renderer
	 * @param features
	 * 
	 * @since 0.5.0
	 */
	protected StructuredFeatureRendererService(Class<? extends AbstractSWTRenderer<VControl>> renderer,
			Collection<EStructuralFeature> features) {
		this.renderer = renderer;
		this.features = new ArrayList<EStructuralFeature>(features);
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
			valueProperty = databindingService.getValueProperty(control.getDomainModelReference(),
					viewModelContext.getDomainModel());
		} catch (final DatabindingFailedException ex) {
			reportService.report(new DatabindingFailedReport(ex));
			return NOT_APPLICABLE;
		}
		Object valueType = valueProperty.getValueType();
		if (valueType instanceof EStructuralFeature) {
			EStructuralFeature structuralFeature = (EStructuralFeature) valueType;
			for (EStructuralFeature f : features) {
				if (structuralFeature.equals(f)) {
					return priority;
				}
			}
		}
		return NOT_APPLICABLE;
	}

	@Override
	public Class<? extends AbstractSWTRenderer<VControl>> getRendererClass() {
		return renderer;
	}

	protected void bindEMFFormsDatabinding(EMFFormsDatabinding service) {
		this.databindingService = service;
	}

	protected void unbindEMFFormsDatabinding(EMFFormsDatabinding service) {
		if (this.databindingService == service) {
			this.databindingService = null;
		}
	}

	protected void bindReportService(ReportService service) {
		this.reportService = service;
	}

	protected void unbindReportService(ReportService service) {
		if (this.reportService == service) {
			this.reportService = null;
		}
	}

}
