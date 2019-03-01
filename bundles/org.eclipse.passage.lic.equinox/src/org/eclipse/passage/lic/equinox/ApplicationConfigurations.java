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

import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

public class ApplicationConfigurations {

	public static LicensingConfiguration getLicensingConfiguration(IApplicationContext application) {
		String productId = getLicensingProductIdentifier(application);
		String productVersion = getLicensingProductVersion(application);
		LicensingConfiguration configuration = LicensingConfigurations.create(productId, productVersion);
		return configuration;
	}

	public static String getLicensingProductIdentifier(IApplicationContext application) {
		String property = application.getBrandingProperty(LicensingProperties.LICENSING_PRODUCT_IDENTIFIER);
		if (property != null) {
			return property;
		}
		return application.getBrandingId();
	}

	public static String getLicensingProductVersion(IApplicationContext application) {
		String property = application.getBrandingProperty(LicensingProperties.LICENSING_PRODUCT_VERSION);
		if (property != null) {
			return property;
		}
		Bundle brandingBundle = application.getBrandingBundle();
		if (brandingBundle != null) {
			Version version = brandingBundle.getVersion();
			StringBuilder sb = new StringBuilder();
			sb.append(version.getMajor()).append('.');
			sb.append(version.getMinor()).append('.');
			sb.append(version.getMicro());
			return sb.toString();
		}
		return LicensingVersions.VERSION_DEFAULT;
	}
}
