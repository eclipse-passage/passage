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
package org.eclipse.passage.lic.equinox;

import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.base.ConfigurationRequirements;
import org.eclipse.passage.lic.base.RestrictionVerdicts;
import org.eclipse.passage.lic.runtime.AccessManager;
import org.eclipse.passage.lic.runtime.ConfigurationRequirement;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class LicensingEquinox {
	
	private static final String PI_LIC_EQUINOX = "org.eclipse.passage.lic.equinox";

	public static IStatus getLicensingStatus(Iterable<RestrictionVerdict> verdicts, String featureName) {
		String pluginId = PI_LIC_EQUINOX;
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

	public static <L> L getLicensingService(Class<L> clazz) {
		Bundle bundle = FrameworkUtil.getBundle(LicensingEquinox.class);
		if (bundle == null) {
			return null;
		}
		BundleContext context = bundle.getBundleContext();
		ServiceReference<L> reference = context.getServiceReference(clazz);
		if (reference == null) {
			return null;
		}
		L result = context.getService(reference);
		if (result != null) {
			context.ungetService(reference);
			return result;
		}
		return null;
	}

	public static Iterable<ConfigurationRequirement> getFeatureRequirements(String featureId, LicensingConfiguration configuration) {
		if (featureId == null) {
			return Collections.emptyList();
		}
		AccessManager accessManager = getLicensingService(AccessManager.class);
		if (accessManager == null) {
			ConfigurationRequirement error = ConfigurationRequirements.createConfigurationError(featureId, configuration);
			return Collections.singletonList(error);
		}
		return accessManager.resolveFeatureRequirements(featureId, configuration);
	}

	public static Iterable<RestrictionVerdict> getFeatureVerdicts(String featureId, LicensingConfiguration configuration) {
		if (featureId == null) {
			return Collections.emptyList();
		}
		AccessManager accessManager = getLicensingService(AccessManager.class);
		if (accessManager == null) {
			RestrictionVerdict invalid = RestrictionVerdicts.createConfigurationError(featureId, configuration);
			return Collections.singletonList(invalid);
		}
		Iterable<RestrictionVerdict> permissons = accessManager.examineFeaturePermissons(featureId, configuration);
		return permissons;
	}
}
