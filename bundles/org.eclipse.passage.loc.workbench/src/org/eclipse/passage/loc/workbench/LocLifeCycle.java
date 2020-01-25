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
package org.eclipse.passage.loc.workbench;

import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.window.Window;
import org.eclipse.passage.lic.jface.ImageFinder;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;

public class LocLifeCycle {

	@PostContextCreate
	void postContextCreate(IApplicationContext context) {
		String windowImages = context.getBrandingProperty("windowImages"); //$NON-NLS-1$
		Bundle brandingBundle = context.getBrandingBundle();
		Window.setDefaultImages(ImageFinder.createImageDescriptors(brandingBundle, windowImages, ",").stream() //$NON-NLS-1$
				.map(id -> id.createImage()).toArray(Image[]::new));
	}

}
