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

import java.util.Iterator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.service.environment.EnvironmentInfo;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.eclipse.passage.lic.runtime.access.AccessManager;
import org.eclipse.passage.lic.runtime.inspector.FeatureInspector;
import org.eclipse.passage.lic.runtime.inspector.HardwareInspector;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class LicensingEquinox {

	public static final String PI_LIC_EQUINOX = "org.eclipse.passage.lic.equinox"; //$NON-NLS-1$

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

	public static IStatus toStatus(LicensingResult result) {
		int severity = result.getSeverity();
		String source = result.getSource();
		String message = result.getMessage();
		int code = result.getCode();
		Throwable exception = result.getException();
		Iterator<LicensingResult> iterator = result.getChildren().iterator();
		if (iterator.hasNext()) {
			MultiStatus status = new MultiStatus(source, code, message, exception);
			while (iterator.hasNext()) {
				LicensingResult child = iterator.next();
				status.merge(toStatus(child));
			}
			return status;
		} else {
			return new Status(severity, source, code, message, exception);
		}
	}
}
