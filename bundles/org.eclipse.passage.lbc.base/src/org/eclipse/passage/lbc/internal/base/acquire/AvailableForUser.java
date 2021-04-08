/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lbc.internal.base.acquire;

import java.util.function.Predicate;

import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;

final class AvailableForUser implements Predicate<FloatingLicensePack> {

	private final String user;

	AvailableForUser(String user) {
		this.user = user;
	}

	@Override
	public boolean test(FloatingLicensePack license) {
		return license.getUsers().stream() //
				.filter(grant -> user.equals(grant.getUser()))//
				.findAny()//
				.isPresent();
	}

}
