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
package org.eclipse.passage.loc.workbench.emfforms.renderers;

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

public abstract class StructuredFeatureRendererService implements EMFFormsDIRendererService<VControl> {

	private final Class<? extends AbstractSWTRenderer<VControl>> renderer;
	private final Iterable<EStructuralFeature> features;
	private final double priority;
	private EMFFormsDatabinding databinding;
	private ReportService report;

	protected StructuredFeatureRendererService(Class<? extends AbstractSWTRenderer<VControl>> renderer, double priority,
			EStructuralFeature... features) {
		this(renderer, priority, Arrays.asList(features));
	}

	protected StructuredFeatureRendererService(Class<? extends AbstractSWTRenderer<VControl>> renderer,
			EStructuralFeature... features) {
		this(renderer, 10, features);
	}

	protected StructuredFeatureRendererService(Class<? extends AbstractSWTRenderer<VControl>> renderer, double priority,
			Collection<EStructuralFeature> features) {
		this.renderer = renderer;
		this.priority = priority;
		this.features = features;
	}

	@Override
	public final double isApplicable(VElement vElement, ViewModelContext viewModelContext) {
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
	public final Class<? extends AbstractSWTRenderer<VControl>> getRendererClass() {
		return renderer;
	}

	protected void bindEMFFormsDatabinding(EMFFormsDatabinding service) {
		this.databinding = service;
	}

	protected void unbindEMFFormsDatabinding(EMFFormsDatabinding service) {
		if (this.databinding == service) {
			this.databinding = null;
		}
	}

	protected void bindReportService(ReportService service) {
		this.report = service;
	}

	protected void unbindReportService(ReportService service) {
		if (this.report == service) {
			this.report = null;
		}
	}

}
