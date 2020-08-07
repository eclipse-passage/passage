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
package org.eclipse.passage.lbc.internal.base;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.passage.lbc.internal.api.BackendLicenseLock;
import org.eclipse.passage.lbc.internal.api.LicenseAlreadyLockedException;
import org.eclipse.passage.lbc.internal.api.LicenseNotLockedException;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;

/**
 * @since 1.0
 */
public class BaseLicenseLock implements BackendLicenseLock {

	private final List<ConditionPack> locked = new LinkedList<ConditionPack>();

	@Override
	public void lock(ConditionPack conditionPack) throws LicenseAlreadyLockedException {
		if (isLocked(conditionPack)) {
			throw new LicenseAlreadyLockedException(conditionPack);
		}
		locked.add(conditionPack);
	}

	@Override
	public void release(ConditionPack conditionPack) throws LicenseNotLockedException {
		if (!isLocked(conditionPack)) {
			throw new LicenseNotLockedException(conditionPack);
		}
		locked.remove(conditionPack);
	}

	private boolean isLocked(ConditionPack conditionPack) {
		return locked.contains(conditionPack);
	}

}
