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
package org.eclipse.passage.lic.base.access;

import java.util.Date;

import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;

public class FeaturePermissions {

	private FeaturePermissions() {
		// block
	}

	public static BaseFeaturePermission createDefault(LicensingCondition condition, LicensingConfiguration configuration) {
		Date leaseTime = new Date();
		Date expireTime = condition.getValidUntil();
		return new BaseFeaturePermission(condition, configuration, leaseTime, expireTime);
	}

	public static BaseFeaturePermission create(LicensingCondition condition, LicensingConfiguration configuration, Date lease, Date expire) {
		return new BaseFeaturePermission(condition, configuration, lease, expire);
	}

}
