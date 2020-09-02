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
import java.util.List;

import javax.inject.Inject;

import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleCompatible;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleDefault;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleEquivalent;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleGreaterOrEqual;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRulePerfect;

public class MatchRuleRenderer extends ComboControlRenderer {

	@Inject
	public MatchRuleRenderer(VControl vElement, ViewModelContext viewContext, ReportService reportService,
			EMFFormsDatabinding emfFormsDatabinding, EMFFormsLabelProvider emfFormsLabelProvider,
			VTViewTemplateProvider vtViewTemplateProvider) {
		super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider);

	}

	@Override
	protected List<String> getDefinedValues() {
		List<String> values = new ArrayList<>();
		values.add(new MatchingRuleCompatible().identifier());
		values.add(new MatchingRuleEquivalent().identifier());
		values.add(new MatchingRuleGreaterOrEqual().identifier());
		values.add(new MatchingRulePerfect().identifier());
		return values;
	}

	@Override
	protected String getUnsetText() {
		return new MatchingRuleDefault().identifier();
	}
}
