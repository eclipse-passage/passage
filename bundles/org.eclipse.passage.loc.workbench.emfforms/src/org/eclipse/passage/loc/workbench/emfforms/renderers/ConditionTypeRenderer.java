/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironment;
import org.eclipse.passage.loc.internal.api.OperatorGear;
import org.eclipse.passage.loc.internal.equinox.OperatorGearAware;
import org.eclipse.passage.loc.internal.workbench.emfforms.i18n.WorkbenchEmfformsMessages;

@SuppressWarnings("restriction")
public class ConditionTypeRenderer extends ComboControlRenderer {

	private final Logger log = LogManager.getLogger(getClass());
	private final List<String> environments;

	@Inject
	public ConditionTypeRenderer(VControl vElement, ViewModelContext viewContext, ReportService reportService,
			EMFFormsDatabinding emfFormsDatabinding, EMFFormsLabelProvider emfFormsLabelProvider,
			VTViewTemplateProvider vtViewTemplateProvider) {
		super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider);
		Optional<List<String>> found = Optional.empty();
		try {
			found = new OperatorGearAware().withGear(this::names);
		} catch (LicensingException e) {
			log.error(e);
		}
		environments = found.orElseGet(Collections::emptyList);
	}

	@Override
	protected String getUnsetText() {
		return WorkbenchEmfformsMessages.ConditionTypeRenderer_not_set;
	}

	@Override
	protected List<String> getDefinedValues() {
		return new ArrayList<>(environments);
	}

	private Optional<List<String>> names(OperatorGear gear) {
		return Optional.of(//
				gear.environments().get().services().stream()//
						.map(RuntimeEnvironment::id)//
						.map(EvaluationType::identifier)//
						.collect(Collectors.toList()));
	}

}
