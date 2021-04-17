/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.loc.internal.licenses.core.issue;

import org.eclipse.emf.common.util.EList;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.UserGrant;

@SuppressWarnings("restriction")
final class UserGrantsAmountReduction implements Reduction<FloatingLicensePack> {

	private final int amount = 1;

	@Override
	public void accept(FloatingLicensePack license) {
		EList<UserGrant> users = license.getUsers();
		if (users.size() > amount) {
			for (int i = users.size() - 1; i >= amount; i--) {
				users.remove(i);
			}
		}
	}

}
