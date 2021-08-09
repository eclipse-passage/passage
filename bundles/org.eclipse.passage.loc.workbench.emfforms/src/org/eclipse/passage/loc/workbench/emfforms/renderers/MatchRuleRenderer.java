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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecp.view.spi.context.ViewModelContext;
import org.eclipse.emf.ecp.view.spi.model.VControl;
import org.eclipse.emf.ecp.view.template.model.VTViewTemplateProvider;
import org.eclipse.emfforms.spi.common.report.ReportService;
import org.eclipse.emfforms.spi.core.services.databinding.EMFFormsDatabinding;
import org.eclipse.emfforms.spi.core.services.label.EMFFormsLabelProvider;
import org.eclipse.passage.lic.api.conditions.MatchingRule;
import org.eclipse.passage.lic.api.version.SemanticVersion;
import org.eclipse.passage.lic.base.conditions.MatchingRuleCompatible;
import org.eclipse.passage.lic.base.conditions.MatchingRuleDefault;
import org.eclipse.passage.lic.base.conditions.MatchingRuleEquivalent;
import org.eclipse.passage.lic.base.conditions.MatchingRuleGreaterOrEqual;
import org.eclipse.passage.lic.base.conditions.MatchingRulePerfect;
import org.eclipse.passage.lic.base.version.BaseSemanticVersion;
import org.eclipse.passage.lic.base.version.SafeVersion;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.licenses.PersonalFeatureGrantDescriptor;
import org.eclipse.passage.lic.licenses.model.api.VersionMatch;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;

public final class MatchRuleRenderer extends ExplainedComboControlRenderer {

	private final List<MatchingRule> values;
	private final List<String> names;
	private final MatchingRule unset = new MatchingRuleDefault();

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
		return unset.identifier();
	}

	@Override
	protected void describeUnset() {
		describe(values.indexOf(unset));
	}

	@Override
	protected void describe(int index) {
		if (index < 0 || index >= values.size()) {
			return;
		}
		describe(values.get(index), guideVersion());

	}

	private void describe(MatchingRule rule, SemanticVersion guide) {
		MatchingRuleExplained explained = new MatchingRuleExplained(rule, guide);
		String text = ""; //$NON-NLS-1$
		List<StyleRange> styles = new ArrayList<>();
		for (MatchingRuleExplained.Sample sample : explained.samples()) {
			if (!text.isEmpty()) {
				text = text + "  "; //$NON-NLS-1$
			}
			String piece = mark(sample.ok()) + sample.text();
			styles.add(sampleSyle(text.length(), piece, sample.ok()));
			text = text + piece;
		}
		description.setText(text);
		styles.forEach(description::setStyleRange);
	}

	private String mark(boolean ok) {
		return ok ? "\u2713 " : "\u2717 "; //$NON-NLS-1$ //$NON-NLS-2$
	}

	private StyleRange sampleSyle(int from, String sample, boolean ok) {
		StyleRange style = new StyleRange();
		style.start = from;
		style.length = sample.length();
		style.foreground = combo.getDisplay().getSystemColor(ok ? SWT.COLOR_DARK_GREEN : SWT.COLOR_DARK_RED);
		return style;
	}

	private SemanticVersion guideVersion() {
		return guide()//
				.map(v -> new SafeVersion(v).semantic())//
				.orElse(new BaseSemanticVersion(1, 2, 3));
	}

	private Optional<String> guide() {
		EObject owner = getViewModelContext().getDomainModel();
		if (owner instanceof LicensePlanFeatureDescriptor) {
			return Optional
					.ofNullable(((LicensePlanFeatureDescriptor) owner).getFeature().getVersionMatch().getVersion());
		} else if (owner instanceof PersonalFeatureGrantDescriptor) {
			return Optional
					.ofNullable(((PersonalFeatureGrantDescriptor) owner).getFeature().getVersionMatch().getVersion());
		} else if (owner instanceof VersionMatch) {
			return Optional.ofNullable(((VersionMatch) owner).getVersion());
		}
		return Optional.empty();
	}

}
