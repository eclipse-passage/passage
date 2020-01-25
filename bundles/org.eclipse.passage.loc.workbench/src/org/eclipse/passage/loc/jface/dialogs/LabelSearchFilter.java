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
package org.eclipse.passage.loc.jface.dialogs;

import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.Viewer;

public class LabelSearchFilter extends ViewerSearchFilter<String> {

	public LabelSearchFilter() {
		super(String.class);
	}
	
	@Override
	protected String convertElement(Viewer viewer, Object parentElement, Object element, Class<String> elementClass) {
		if (viewer instanceof ContentViewer) {
			ContentViewer contentViewer = (ContentViewer) viewer;
			IBaseLabelProvider baseLabelProvider = contentViewer.getLabelProvider();
			if (baseLabelProvider instanceof ILabelProvider) {
				ILabelProvider labelProvider = (ILabelProvider) baseLabelProvider;
				return labelProvider.getText(element);
			}
		}
		return null;
	}

	@Override
	protected boolean selectElement(Viewer viewer, Object parentElement, String element, String searchText) {
		if (getSearchPattern().matcher(element).matches()) {
			return true;
		}
		return false;
	}

}
