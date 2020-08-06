/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lbc.base;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.passage.lbc.api.BackendConditionDispatcher;
import org.eclipse.passage.lbc.api.LicenseAlreadyTakenException;
import org.eclipse.passage.lbc.api.NoSuchTakenLicenseException;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;

/**
 * @since 1.0
 */
public class BaseConditionDispatcher implements BackendConditionDispatcher {

	private final List<ConditionPack> taken = new LinkedList();

	@Override
	public void take(ConditionPack conditionPack) throws LicenseAlreadyTakenException {
		if (taken.contains(conditionPack)) {
			throw new LicenseAlreadyTakenException(conditionPack);
		}
		taken.add(conditionPack);
	}

	@Override
	public void release(ConditionPack conditionPack) throws NoSuchTakenLicenseException {
		if (!taken.contains(conditionPack)) {
			throw new NoSuchTakenLicenseException(conditionPack);
		}
		taken.remove(conditionPack);
	}

}
