/*******************************************************************************
 * Copyright (c) 2021, 2024 ArSysOp
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
package org.eclipse.passage.loc.agreements.emfforms.renderers;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

import javax.inject.Inject;

import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.passage.loc.internal.equinox.AgreementsService;

@SuppressWarnings("restriction")
public final class AgreementFileRenderer extends BaseAgreementFileRenderer {

	@Inject
	public AgreementFileRenderer(VControl element, ViewModelContext context, ReportService report,
			EMFFormsDatabinding databinding, EMFFormsLabelProvider labeling, VTViewTemplateProvider template) {
		super(element, context, report, databinding, labeling, template);
	}

	@Override
	protected Optional<File> locatedAgreementFile() {
		return new LocatedAgreementFile().get();
	}

	@Override
	protected String residentAgreementResource(File file) throws Exception {
		String name = file.getName();
		new AgreementsService().get().located(name, getViewModelContext().getDomainModel())
				.write(Files.readAllBytes(file.toPath()));
		return name;
	}

}
