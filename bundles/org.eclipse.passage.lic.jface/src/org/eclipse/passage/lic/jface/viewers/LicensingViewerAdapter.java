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
package org.eclipse.passage.lic.jface.viewers;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;

public interface LicensingViewerAdapter {

	String getLabel(Object element);
	
	String getLabel(Object element, int columnIndex);
	
	ImageDescriptor getImageDescriptor(Object element);

	ImageDescriptor getImageDescriptor(Object element, int columnIndex);
	
	FontData getFont(Object element);

	FontData getFont(Object element, int columnIndex);
	
	RGB getBackground(Object element);

	RGB getBackground(Object element, int columnIndex);
	
	RGB getForeground(Object element);
	
	RGB getForeground(Object element, int columnIndex);
	
	void dispose();
	
}
