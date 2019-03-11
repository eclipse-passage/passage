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
package org.eclipse.passage.lic.jface.dialogs;

import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public abstract class LicensingRegistryPage<R> extends DialogPage {

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

	protected void createErrorContent(Composite parent, String message) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setLayout(new GridLayout(2, false));
		Label image = new Label(composite, SWT.NONE);
		image.setImage(LicensingImages.getImage(LicensingImages.IMG_LEVEL_ERROR));
		Label text = new Label(composite, SWT.NONE);
		text.setText(message);
	}

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
