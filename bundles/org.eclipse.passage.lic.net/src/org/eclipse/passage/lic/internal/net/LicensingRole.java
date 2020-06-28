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
package org.eclipse.passage.lic.internal.net;

import java.util.Map;
import java.util.function.Function;

import org.eclipse.passage.lic.internal.api.conditions.UserRole;
import org.eclipse.passage.lic.internal.base.BaseNamedData;

@SuppressWarnings("restriction")
public final class LicensingRole extends BaseNamedData<UserRole> {

	public LicensingRole(Function<String, UserRole> retrieve) {
		super(retrieve);
	}

	public LicensingRole(Map<String, String> data) {
		super(key -> new UserRole.Of(data.get(key)));
	}

	public LicensingRole(UserRole role) {
		super(key -> role);
	}

	@Override
	public String key() {
		return "role"; //$NON-NLS-1$
	}

}
