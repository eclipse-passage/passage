/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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
package org.eclipse.passage.loc.users.emfforms.parts;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emfforms.spi.swt.treemasterdetail.util.CreateElementCallback;
import org.eclipse.passage.lic.users.model.api.LicenseOwner;
import org.eclipse.passage.lic.users.model.meta.UsersFactory;

public class UsersCreateElementCallback implements CreateElementCallback {

	@Override
	public void initElement(EObject parent, EReference reference, EObject newObject) {
		if (newObject instanceof LicenseOwner) {
			LicenseOwner lo = (LicenseOwner) newObject;
			lo.setContact(UsersFactory.eINSTANCE.createContact());
		}
	}

	@Override
	public boolean beforeCreateElement(Object newElement) {
		return true;
	}

	@Override
	public void afterCreateElement(Object newElement) {

	}

}
