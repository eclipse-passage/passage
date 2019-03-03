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
package org.eclipse.passage.lic.e4.ui.handlers;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.passage.lic.equinox.ApplicationConfigurations;
import org.eclipse.passage.lic.equinox.LicensingEquinox;
import org.eclipse.passage.lic.jface.dialogs.LicensingStatusDialog;
import org.eclipse.passage.lic.runtime.AccessManager;
import org.eclipse.passage.lic.runtime.ConfigurationRequirement;
import org.eclipse.passage.lic.runtime.FeaturePermission;
import org.eclipse.passage.lic.runtime.LicensingCondition;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;
import org.eclipse.passage.lic.runtime.inspector.HardwareInspector;
import org.eclipse.swt.widgets.Shell;

public class InspectLicenseHandler {

	@Execute
	public void execute(Shell shell, IEclipseContext context) {

		AccessManager accessManager = LicensingEquinox.getLicensingService(AccessManager.class);

		LicensingConfiguration configuration = ApplicationConfigurations.getLicensingConfiguration();
		Iterable<ConfigurationRequirement> requirements = accessManager.resolveRequirements(configuration);
		Iterable<LicensingCondition> conditions = accessManager.extractConditions(configuration);
		Iterable<FeaturePermission> permissions = accessManager.evaluateConditions(conditions, configuration);
		Iterable<RestrictionVerdict> verdicts = accessManager.examinePermissons(requirements, permissions,
				configuration);

		String contacts = "Eclipse Passage \nhttps://projects.eclipse.org/projects/technology.passage";
		LicensingStatusDialog.setDefaultContacts(contacts);

		LicensingStatusDialog dialog = new LicensingStatusDialog(shell);
		dialog.setHardwareInspector(LicensingEquinox.getLicensingService(HardwareInspector.class));
		dialog.updateLicensingStatus(requirements, verdicts);
		dialog.open();
	}

}