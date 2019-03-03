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

import java.util.Collection;

import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.Version;

public class ApplicationConfigurations {

	public static IApplicationContext getApplicationContext() {
		Bundle bundle = FrameworkUtil.getBundle(ApplicationConfigurations.class);
		if (bundle == null) {
			return null;
		}
		BundleContext context = bundle.getBundleContext();
		Collection<ServiceReference<IApplicationContext>> references;
		try {
			references = context.getServiceReferences(IApplicationContext.class, "(eclipse.application.type=main.thread)"); //$NON-NLS-1$
		} catch (InvalidSyntaxException e) {
			return null;
		}
		if (references == null || references.isEmpty()) {
			return null;
		}
		// assumes the application context is available as a service
		ServiceReference<IApplicationContext> firstRef = references.iterator().next();
		IApplicationContext result = context.getService(firstRef);
		if (result != null) {
			context.ungetService(firstRef);
			return result;
		}
		return null;
	}
	
	public static LicensingConfiguration getLicensingConfiguration() {
		IApplicationContext applicationContext = getApplicationContext();
		return getLicensingConfiguration(applicationContext);
	}

	public static LicensingConfiguration getLicensingConfiguration(IApplicationContext application) {
		if (application == null) {
			return LicensingConfigurations.INVALID;
		}
		String productId = getLicensingProductIdentifier(application);
		String productVersion = getLicensingProductVersion(application);
		LicensingConfiguration configuration = LicensingConfigurations.create(productId, productVersion);
		return configuration;
	}

	public static String getLicensingProductIdentifier(IApplicationContext application) {
		if (application == null) {
			return LicensingConfigurations.INVALID.getProductIdentifier();
		}
		String property = application.getBrandingProperty(LicensingProperties.LICENSING_PRODUCT_IDENTIFIER);
		if (property != null) {
			return property;
		}
		return application.getBrandingId();
	}

	public static String getLicensingProductVersion(IApplicationContext application) {
		if (application == null) {
			return LicensingConfigurations.INVALID.getProductVersion();
		}
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
