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

import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.runtime.AccessManager;
import org.eclipse.passage.lic.runtime.inspector.FeatureInspector;
import org.eclipse.passage.lic.runtime.inspector.HardwareInspector;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class EquinoxAccess {

	public static final String PI_LIC_EQUINOX = "org.eclipse.passage.lic.equinox";

	public static AccessManager getAccessManager() {
		return getLicensingService(AccessManager.class);
	}

	public static EnvironmentInfo getEnvironmentInfo() {
		return getLicensingService(EnvironmentInfo.class);
	}

	public static FeatureInspector getFeatureInspector() {
		return getLicensingService(FeatureInspector.class);
	}

	public static HardwareInspector getHardwareInspector() {
		return getLicensingService(HardwareInspector.class);
	}

	public static <L> L getLicensingService(Class<L> clazz) {
		Bundle bundle = FrameworkUtil.getBundle(EquinoxAccess.class);
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
}
