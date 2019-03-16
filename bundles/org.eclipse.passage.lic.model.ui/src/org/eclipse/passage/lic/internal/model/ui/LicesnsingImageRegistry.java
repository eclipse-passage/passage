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
package org.eclipse.passage.lic.internal.model.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.passage.lic.features.edit.FeaturesEditPlugin;
import org.eclipse.passage.lic.features.model.meta.FeaturesPackage;
import org.eclipse.passage.lic.jface.resource.LicensingImages;
import org.eclipse.passage.lic.model.edit.LicEditPlugin;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component
public class LicesnsingImageRegistry {

	@Activate
	public void activate(BundleContext bundleContext) {
		IEclipseContext serviceContext = EclipseContextFactory.getServiceContext(bundleContext);
		IEventBroker eventBroker = serviceContext.get(IEventBroker.class);
		if (eventBroker != null) {
			eventBroker.subscribe(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE, null, e -> registerImages(), false);
		}
	}

	private void registerImages() {
		Display.getDefault().asyncExec(() -> doRegisterImages());
	}

	private void doRegisterImages() {
		LicensingImages.getImageRegistry();
		Map<String, String> paths = new HashMap<String, String>();
		String pattern = "$nl$/icons/full/obj16/%s"; //$NON-NLS-1$

		FeaturesPackage features = FeaturesPackage.eINSTANCE;
		paths.put(features.getFeatureSet().getName(), String.format(pattern, "feature.png")); //$NON-NLS-1$
		paths.put(features.getFeature().getName(), String.format(pattern, "feature.png")); //$NON-NLS-1$
		paths.put(features.getFeatureVersion().getName(), String.format(pattern, "feature.png")); //$NON-NLS-1$
		LicensingImages.declareImages(FeaturesEditPlugin.class, paths);

		LicPackage lic = LicPackage.eINSTANCE;
		paths.clear();
		paths.put(lic.getProductLine().getName(), String.format(pattern, "product.png")); //$NON-NLS-1$
		paths.put(lic.getProduct().getName(), String.format(pattern, "product.png")); //$NON-NLS-1$
		paths.put(lic.getProductVersion().getName(), String.format(pattern, "product.png")); //$NON-NLS-1$
		paths.put(lic.getProductVersionFeature().getName(), String.format(pattern, "feature.png")); //$NON-NLS-1$

		paths.put(lic.getUserOrigin().getName(), String.format(pattern, "user.png")); //$NON-NLS-1$
		paths.put(lic.getUser().getName(), String.format(pattern, "user.png")); //$NON-NLS-1$

		paths.put(lic.getLicensePack().getName(), String.format(pattern, "license.png")); //$NON-NLS-1$
		paths.put(lic.getLicenseGrant().getName(), String.format(pattern, "license.png")); //$NON-NLS-1$
		LicensingImages.declareImages(LicEditPlugin.class, paths);

	}

}
