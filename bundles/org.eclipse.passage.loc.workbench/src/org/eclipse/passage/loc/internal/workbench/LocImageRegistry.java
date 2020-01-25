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
package org.eclipse.passage.loc.internal.workbench;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.passage.loc.jface.LocImages;
import org.eclipse.swt.graphics.Image;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component
public class LocImageRegistry implements LocImages {

	private ImageRegistry imageRegistry;

	@Activate
	public void activate() {
		imageRegistry = new ImageRegistry();
		addBaseImages();
	}

	protected void addBaseImages() {
		String pattern = "platform:/plugin/org.eclipse.passage.loc.workbench/images/%s"; //$NON-NLS-1$
		register(IMG_TOOL_ADD, String.format(pattern, "add.png")); //$NON-NLS-1$
		register(IMG_TOOL_EDIT, String.format(pattern, "edit.png")); //$NON-NLS-1$
		register(IMG_TOOL_REMOVE, String.format(pattern, "remove.png")); //$NON-NLS-1$
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
