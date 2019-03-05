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

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class EquinoxAccess {
	
	public static final String PI_LIC_EQUINOX = "org.eclipse.passage.lic.equinox";

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
