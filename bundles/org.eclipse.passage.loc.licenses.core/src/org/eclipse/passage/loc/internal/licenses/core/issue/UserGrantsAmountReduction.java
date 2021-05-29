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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.UserGrant;
import org.eclipse.passage.loc.internal.licenses.core.i18n.ReductionMessages;

final class UserGrantsAmountReduction implements Reduction<FloatingLicensePack> {

	private final Logger log = LogManager.getLogger(getClass());
	private final int amount = 5;

	@Override
	public void accept(FloatingLicensePack license) {
		EList<UserGrant> users = license.getUsers();
		if (users.size() > amount) {
			log.warn(String.format(ReductionMessages.UserGrantsAmountReduction_reduction_usergrant_amount, amount));
			for (int i = users.size() - 1; i >= amount; i--) {
				UserGrant victim = users.remove(i);
				log.warn(String.format(ReductionMessages.UserGrantsAmountReduction_reduction_usergrant_user,
						victim.getUser()));
			}
		}
	}

}
