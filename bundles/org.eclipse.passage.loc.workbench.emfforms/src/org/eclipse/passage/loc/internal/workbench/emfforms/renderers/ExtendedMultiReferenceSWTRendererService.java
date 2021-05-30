/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.workbench.emfforms.renderers;

import java.util.Optional;

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.spi.model.VDomainModelReference;
import org.eclipse.emf.ecp.view.spi.model.VElement;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedException;
import org.eclipse.emfforms.spi.core.services.databinding.DatabindingFailedReport;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.swt.core.AbstractSWTRenderer;
import org.eclipse.emfforms.spi.swt.core.di.EMFFormsDIRendererService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public final class ExtendedMultiReferenceSWTRendererService implements EMFFormsDIRendererService<VControl> {

	private EMFFormsDatabinding databinding;
	private ReportService report;

	@Reference
	protected void setEMFFormsDatabinding(EMFFormsDatabinding databindingService) {
		this.databinding = databindingService;
	}

	@Reference
	protected void setReportService(ReportService reportService) {
		this.report = reportService;
	}

	@Override
	public double isApplicable(VElement vElement, ViewModelContext context) {
		Optional<VDomainModelReference> dmr = dmr(vElement);
		if (dmr.isEmpty()) {
			return NOT_APPLICABLE;
		}
		IValueProperty<?, ?> property;
		try {
			property = databinding.getValueProperty(dmr.get(), context.getDomainModel());
		} catch (DatabindingFailedException ex) {
			report.report(new DatabindingFailedReport(ex));
			return NOT_APPLICABLE;
		}
		EStructuralFeature feature = EStructuralFeature.class.cast(property.getValueType());
		if (!feature.isMany() || !EReference.class.isInstance(feature)) {
			return NOT_APPLICABLE;
		}
		return 20;
	}

	private Optional<VDomainModelReference> dmr(VElement element) {
		return Optional.ofNullable(element)//
				.filter(VControl.class::isInstance)//
				.map(VControl.class::cast)//
				.map(VControl::getDomainModelReference);
	}

	@Override
	public Class<? extends AbstractSWTRenderer<VControl>> getRendererClass() {
		return ExtendedMultiReferenceSWTRenderer.class;
	}

}
