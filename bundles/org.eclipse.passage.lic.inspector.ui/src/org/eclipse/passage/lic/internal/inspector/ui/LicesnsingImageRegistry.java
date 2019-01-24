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
package org.eclipse.passage.lic.internal.inspector.ui;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.passage.lic.inspector.ui.LicInspectorUi;
import org.eclipse.passage.lic.jface.LicensingImages;
import org.eclipse.passage.lic.model.meta.LicPackage;
import org.eclipse.swt.graphics.Image;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component
public class LicesnsingImageRegistry implements LicensingImages {

	private ImageRegistry imageRegistry;

	@Activate
	public void activate() {
		imageRegistry = new ImageRegistry();
		addBaseImages();
		addModelImages();
		addInspectorImages();
	}

	protected void addBaseImages() {
		String pattern = "platform:/plugin/org.eclipse.passage.lic.jface/icons/obj16/%s"; //$NON-NLS-1$
		register(IMG_DEFAULT, String.format(pattern, "passage.png")); //$NON-NLS-1$
		register(IMG_LEVEL_OK, String.format(pattern, "ok.png")); //$NON-NLS-1$
		register(IMG_LEVEL_INFO, String.format(pattern, "info.png")); //$NON-NLS-1$
		register(IMG_LEVEL_WARN, String.format(pattern, "warn.png")); //$NON-NLS-1$
		register(IMG_LEVEL_ERROR, String.format(pattern, "error.png")); //$NON-NLS-1$
		register(IMG_LEVEL_FATAL, String.format(pattern, "fatal.png")); //$NON-NLS-1$
	}

	protected void addModelImages() {
		String pattern = "platform:/plugin/org.eclipse.passage.lic.model.edit/icons/full/obj16/%s"; //$NON-NLS-1$
		LicPackage lic = LicPackage.eINSTANCE;

		register(lic.getFeatureSet().getName(), String.format(pattern, "feature.png")); //$NON-NLS-1$
		register(lic.getFeature().getName(), String.format(pattern, "feature.png")); //$NON-NLS-1$
		register(lic.getFeatureVersion().getName(), String.format(pattern, "feature.png")); //$NON-NLS-1$

		register(lic.getProductLine().getName(), String.format(pattern, "product.png")); //$NON-NLS-1$
		register(lic.getProduct().getName(), String.format(pattern, "product.png")); //$NON-NLS-1$
		register(lic.getProductVersion().getName(), String.format(pattern, "product.png")); //$NON-NLS-1$
		register(lic.getProductVersionFeature().getName(), String.format(pattern, "feature.png")); //$NON-NLS-1$

		register(lic.getUserOrigin().getName(), String.format(pattern, "user.png")); //$NON-NLS-1$
		register(lic.getUser().getName(), String.format(pattern, "user.png")); //$NON-NLS-1$

		register(lic.getLicensePack().getName(), String.format(pattern, "license.png")); //$NON-NLS-1$
		register(lic.getLicenseGrant().getName(), String.format(pattern, "license.png")); //$NON-NLS-1$
	}

	protected void addInspectorImages() {
		String pattern = "platform:/plugin/org.eclipse.passage.lic.inspector.ui/icons/etool16/%s"; //$NON-NLS-1$
		register(LicInspectorUi.IMG_INSPECTOR, String.format(pattern, "hw-inspector.png")); //$NON-NLS-1$
	}

	private void register(String key, String url) {
		try {
			ImageDescriptor created = ImageDescriptor.createFromURL(new URL(url));
			imageRegistry.put(key, created);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Deactivate
	public void deactivate() {
		imageRegistry.dispose();
		;
		imageRegistry = null;
	}

	@Override
	public Image getImage(String identifier) {
		return imageRegistry.get(identifier);
	}

	@Override
	public ImageDescriptor getImageDescriptor(String identifier) {
		return imageRegistry.getDescriptor(identifier);
	}

}
