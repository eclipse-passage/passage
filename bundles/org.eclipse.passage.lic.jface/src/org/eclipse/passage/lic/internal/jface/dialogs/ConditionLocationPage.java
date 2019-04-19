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

import org.eclipse.passage.lic.internal.jface.JFaceMessages;
import org.eclipse.passage.lic.jface.dialogs.LicensingRegistryPage;
import org.eclipse.passage.lic.runtime.conditions.ConditionMinerRegistry;
import org.eclipse.swt.widgets.Composite;

public class ConditionLocationPage extends LicensingRegistryPage<ConditionMinerRegistry> {

	public ConditionLocationPage() {
		super(ConditionMinerRegistry.class);
	}

	@Override
	protected void createContent(Composite parent, ConditionMinerRegistry registry) {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getConfigurationErrorMessage() {
		return JFaceMessages.ConditionLocationPage_e_not_available;
	}

}
