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

import org.eclipse.passage.lic.api.access.FeaturePermission;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;

public class FeaturePermissions {

	private FeaturePermissions() {
		// block
	}

	public static FeaturePermission createDefault(LicensingConfiguration configuration, LicensingCondition condition) {
		Date leaseTime = new Date();
		Date expireTime = condition.getValidUntil();
		return new BaseFeaturePermission(configuration, condition, leaseTime, expireTime);
	}

	public static FeaturePermission create(LicensingConfiguration configuration, LicensingCondition condition,
			Date lease, Date expire) {
		return new BaseFeaturePermission(configuration, condition, lease, expire);
	}

}
