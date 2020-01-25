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
package org.eclipse.passage.loc.jface;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

public interface LocImages {

	String IMG_TOOL_ADD = "IMG_TOOL_ADD"; //$NON-NLS-1$
	String IMG_TOOL_EDIT = "IMG_TOOL_EDIT"; //$NON-NLS-1$
	String IMG_TOOL_REMOVE = "IMG_TOOL_REMOVE"; //$NON-NLS-1$
	
	public Image getImage(String identifier);
	public ImageDescriptor getImageDescriptor(String identifier);

}
