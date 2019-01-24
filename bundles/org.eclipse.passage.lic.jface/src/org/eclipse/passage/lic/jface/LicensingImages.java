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
package org.eclipse.passage.lic.jface;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

public interface LicensingImages {
	
	String IMG_DEFAULT = "IMG_DEFAULT"; //$NON-NLS-1$

	String IMG_LEVEL_OK = "IMG_LEVEL_OK"; //$NON-NLS-1$
	String IMG_LEVEL_INFO = "IMG_LEVEL_INFO"; //$NON-NLS-1$
	String IMG_LEVEL_WARN = "IMG_LEVEL_WARN"; //$NON-NLS-1$
	String IMG_LEVEL_ERROR = "IMG_LEVEL_ERROR"; //$NON-NLS-1$
	String IMG_LEVEL_FATAL = "IMG_LEVEL_FATAL"; //$NON-NLS-1$
	
	public Image getImage(String identifier);
	public ImageDescriptor getImageDescriptor(String identifier);
	
}
