/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
import org.eclipse.passage.lic.api.conditions.ConditionMiner;
import org.eclipse.passage.lic.api.conditions.ConditionMinerRegistry;
import org.eclipse.passage.lic.internal.jface.i18n.JFaceMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public class ConditionLocationPage extends LicensingRegistryPage<ConditionMinerRegistry> {

	public ConditionLocationPage() {
		super(ConditionMinerRegistry.class);
	}

	@Override
	protected void createContent(Composite parent, ConditionMinerRegistry registry) {
		Iterable<ConditionMiner> conditionTypes = registry.getConditionMiners();
		GridDataFactory groupData = GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).grab(true, false);
		for (ConditionMiner miner : conditionTypes) {
			Group group = new Group(parent, SWT.NONE);
			group.setData(miner);
			group.setLayout(new GridLayout(1, false));
			group.setLayoutData(groupData.create());

			Label description = new Label(group, SWT.WRAP);
			String target = registry.getConditionMinerTarget(miner);
			if (target != null) {
				description.setText(target);
			}
		}
	}

	@Override
	protected String getConfigurationErrorMessage() {
		return JFaceMessages.ConditionLocationPage_e_not_available;
	}

}
