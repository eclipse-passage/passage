/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.lic.integration.tests.permissionobservatory;

import java.util.Date;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.access.FeaturePermission;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;

/**
 * <p>
 * {@linkplain FakePermissionEmitter} service leases these short-TTL
 * permissions, configured for several seconds to frm irth to expiration.
 * </p>
 * 
 * @since 0.6
 */
class FakePermission implements FeaturePermission {
	private final LicensingConfiguration configuration;
	private final LicensingCondition condition;
	private final int ttl;
	private final Date now = new Date();

	FakePermission(LicensingConfiguration licensingConfiguration, LicensingCondition condition, int ttl) {
		this.configuration = licensingConfiguration;
		this.condition = condition;
		this.ttl = ttl;
	}

	@Override
	public LicensingConfiguration getLicensingConfiguration() {
		return configuration;
	}

	@Override
	public LicensingCondition getLicensingCondition() {
		return condition;
	}

	@Override
	public Date getLeaseDate() {
		return now;
	}

	@Override
	public Date getExpireDate() {
		return new Date(now.getTime() + ttl * 1000); // permission is valid for TTL seconds
	}
}
