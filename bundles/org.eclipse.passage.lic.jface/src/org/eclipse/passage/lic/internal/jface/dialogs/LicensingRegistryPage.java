/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.jface.dialogs;

import org.eclipse.passage.lic.jface.dialogs.LicensingPage;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public abstract class LicensingRegistryPage<R> extends LicensingPage {

	private BundleContext bundleContext;
	private ServiceReference<R> registryReference;
	private R registryService;

	public LicensingRegistryPage(Class<R> registryClass) {
		Bundle bundle = FrameworkUtil.getBundle(getClass());
		if (bundle != null) {
			bundleContext = bundle.getBundleContext();
			registryReference = bundleContext.getServiceReference(registryClass);
			if (registryReference != null) {
				registryService = bundleContext.getService(registryReference);
			}
		}
	}

	@Override
	public void createControl(Composite parent) {
		if (registryService != null) {
			createContent(parent, registryService);
		} else {
			createErrorContent(parent, getConfigurationErrorMessage());
		}
	}

	protected abstract void createContent(Composite parent, R registry);

	protected abstract String getConfigurationErrorMessage();

	@Override
	public void dispose() {
		if (registryService != null) {
			registryService = null;
		}
		if (registryReference != null) {
			bundleContext.ungetService(registryReference);
			registryReference = null;
			bundleContext = null;
		}
		super.dispose();
	}
}
