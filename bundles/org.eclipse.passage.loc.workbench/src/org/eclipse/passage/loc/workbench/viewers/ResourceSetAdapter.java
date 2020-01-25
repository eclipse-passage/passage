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
package org.eclipse.passage.loc.workbench.viewers;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.jface.viewers.StructuredViewer;

public class ResourceSetAdapter extends EContentAdapter {
	
	private final StructuredViewer viewer;

	public ResourceSetAdapter(StructuredViewer viewer) {
		this.viewer = viewer;
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		//FIXME: removing calls to super for now, revisit later
		if (viewer.getControl().isDisposed()) {
			return;
		}
		viewer.refresh();
	}

}
