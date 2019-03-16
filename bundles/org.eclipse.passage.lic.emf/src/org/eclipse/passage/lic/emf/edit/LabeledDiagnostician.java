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
package org.eclipse.passage.lic.emf.edit;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.provider.IItemLabelProvider;

public class LabeledDiagnostician extends Diagnostician {
	private final AdapterFactory adapterFactory;

	public LabeledDiagnostician(AdapterFactory adapterFactory) {
		this.adapterFactory = adapterFactory;
	}

	@Override
	public String getObjectLabel(EObject eObject) {
		if (adapterFactory != null && !eObject.eIsProxy()) {
			IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory.adapt(eObject,
					IItemLabelProvider.class);
			if (itemLabelProvider != null) {
				return itemLabelProvider.getText(eObject);
			}
		}

		return super.getObjectLabel(eObject);
	}

}