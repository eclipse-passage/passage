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
package org.eclipse.passage.lic.internal.base.permission;

import java.util.Date;

import org.eclipse.passage.lic.api.access.FeaturePermission;
import org.eclipse.passage.lic.internal.base.permission.observatory.Observatory;
import org.eclipse.passage.lic.internal.base.permission.observatory.Limited;

/**
 * Adapter for {@linkplain FeaturePermission} to implement {@linkplain Limited}
 * interface to be properly tracked by {@linkplain Observatory}
 * 
 * @since 0.6
 */
public final class LimitedPermission implements Limited {
	private final FeaturePermission permission;

	public LimitedPermission(FeaturePermission permission) {
		this.permission = permission;
	}

	@Override
	public boolean expired() {
		Date now = new Date();
		return now.before(permission.getLeaseDate()) || now.after(permission.getExpireDate());
	}

	@Override
	public int hashCode() {
		return permission.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		return permission.equals(object);
	}
}
