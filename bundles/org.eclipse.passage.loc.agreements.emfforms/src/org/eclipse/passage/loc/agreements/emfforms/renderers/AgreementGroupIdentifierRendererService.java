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
package org.eclipse.passage.loc.agreements.emfforms.renderers;

import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.swt.core.di.EMFFormsDIRendererService;
import org.eclipse.passage.lic.agreements.model.meta.AgreementsPackage;
import org.eclipse.passage.loc.workbench.emfforms.renderers.StructuredFeatureRendererService;
import org.eclipse.passage.loc.workbench.emfforms.renderers.ValidatedTextRenderer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class AgreementGroupIdentifierRendererService extends StructuredFeatureRendererService
		implements EMFFormsDIRendererService<VControl> {

	public AgreementGroupIdentifierRendererService() {
		super(ValidatedTextRenderer.class, AgreementsPackage.eINSTANCE.getAgreementGroup_Identifier());
	}

	@Reference
	@Override
	public void bindEMFFormsDatabinding(EMFFormsDatabinding databindingService) {
		super.bindEMFFormsDatabinding(databindingService);
	}

	@Override
	public void unbindEMFFormsDatabinding(EMFFormsDatabinding databindingService) {
		super.unbindEMFFormsDatabinding(databindingService);
	}

	@Reference
	@Override
	public void bindReportService(ReportService reportService) {
		super.bindReportService(reportService);
	}

	@Override
	public void unbindReportService(ReportService reportService) {
		super.unbindReportService(reportService);
	}

}
