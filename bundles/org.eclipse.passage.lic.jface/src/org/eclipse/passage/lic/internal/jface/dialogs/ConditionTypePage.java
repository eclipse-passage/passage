/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.jface.dialogs;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.passage.lic.jface.dialogs.LicensingRegistryPage;
import org.eclipse.passage.lic.runtime.ConditionEvaluator;
import org.eclipse.passage.lic.runtime.registry.ConditionEvaluatorRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public class ConditionTypePage extends LicensingRegistryPage<ConditionEvaluatorRegistry> {

	public ConditionTypePage() {
		super(ConditionEvaluatorRegistry.class);
	}

	@Override
	protected void createContent(Composite parent, ConditionEvaluatorRegistry registry) {
		Iterable<String> conditionTypes = registry.getSupportedConditionTypes();
		GridDataFactory groupData = GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).grab(true, false);
		for (String type : conditionTypes) {
			ConditionEvaluator evaluator = registry.getConditionEvaluator(type);
			Group group = new Group(parent, SWT.NONE);
			group.setText(evaluator.getConditionName());
			group.setData(evaluator);
			group.setLayout(new GridLayout(1, false));
			group.setLayoutData(groupData.create());

			Label description = new Label(group, SWT.WRAP);
			description.setText(evaluator.getConditionDescription());
		}
	}

	@Override
	protected String getConfigurationErrorMessage() {
		return "Condition types definitions are not available.\nPlease check the product configuration";
	}

}
