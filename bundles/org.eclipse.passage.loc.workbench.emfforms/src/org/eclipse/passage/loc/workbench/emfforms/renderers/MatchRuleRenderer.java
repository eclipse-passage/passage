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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.passage.lic.internal.api.conditions.MatchingRule;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleCompatible;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleDefault;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleEquivalent;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleGreaterOrEqual;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRulePerfect;

@SuppressWarnings("restriction")
public class MatchRuleRenderer extends ExplainedComboControlRenderer {

	private final List<MatchingRule> values;
	private final List<String> names;

	@Inject
	public MatchRuleRenderer(VControl vElement, ViewModelContext viewContext, ReportService reportService,
			EMFFormsDatabinding emfFormsDatabinding, EMFFormsLabelProvider emfFormsLabelProvider,
			VTViewTemplateProvider vtViewTemplateProvider) {
		super(vElement, viewContext, reportService, emfFormsDatabinding, emfFormsLabelProvider, vtViewTemplateProvider);
		this.values = Arrays.asList(//
				new MatchingRuleCompatible(), //
				new MatchingRuleEquivalent(), //
				new MatchingRuleGreaterOrEqual(), //
				new MatchingRulePerfect());
		this.names = values.stream().map(MatchingRule::identifier).collect(Collectors.toList());
	}

	@Override
	protected List<String> getDefinedValues() {
		return names;
	}

	@Override
	protected String getUnsetText() {
		return new MatchingRuleDefault().identifier();
	}

	@Override
	protected void describe(int index) {
		if (index < 0 || index >= values.size()) {
			return;
		}
		MatchingRule rule = values.get(index);
		description.setText(rule.identifier());
	}
}
