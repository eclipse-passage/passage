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
package org.eclipse.passage.lic.equinox.restrictions;

import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.base.restrictions.RestrictionVerdicts;
import org.eclipse.passage.lic.equinox.EquinoxAccess;
import org.eclipse.passage.lic.runtime.AccessManager;
import org.eclipse.passage.lic.runtime.ConfigurationRequirement;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;

public class EquinoxRestrictions {

	public static Iterable<RestrictionVerdict> getFeatureVerdicts(String featureId, LicensingConfiguration configuration) {
		if (featureId == null) {
			return Collections.emptyList();
		}
		AccessManager accessManager = EquinoxAccess.getLicensingService(AccessManager.class);
		if (accessManager == null) {
			RestrictionVerdict invalid = RestrictionVerdicts.createConfigurationError(featureId, configuration);
			return Collections.singletonList(invalid);
		}
		Iterable<RestrictionVerdict> permissons = accessManager.examineFeaturePermissons(featureId, configuration);
		return permissons;
	}

	public static IStatus getRestrictionStatus(Iterable<RestrictionVerdict> verdicts, String featureName) {
		String pluginId = EquinoxAccess.PI_LIC_EQUINOX;
		if (!verdicts.iterator().hasNext()) {
			String message = NLS.bind("Feature \"{0}\" is licensed properly", featureName);
			return new Status(IStatus.OK, pluginId, message);
		}
		String title = "Issues with licensing";
		MultiStatus status = new MultiStatus(pluginId , RestrictionVerdicts.CODE_CONFIGURATION_ERROR, title, null);
		for (RestrictionVerdict verdict : verdicts) {
			ConfigurationRequirement configurationRequirement = verdict.getConfigurationRequirement();
			if (configurationRequirement != null) {
				featureName = configurationRequirement.getFeatureName();
			}
			String message = NLS.bind("Feature \"{0}\" is not licensed properly", featureName);
			status.add(new Status(IStatus.ERROR, pluginId, message));
		}
		return status;
	}

}
