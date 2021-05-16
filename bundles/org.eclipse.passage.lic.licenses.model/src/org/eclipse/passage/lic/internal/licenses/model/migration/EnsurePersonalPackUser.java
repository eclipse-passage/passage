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
package org.eclipse.passage.lic.internal.licenses.model.migration;

import java.util.function.Function;

import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.UserRef;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

public final class EnsurePersonalPackUser implements Function<PersonalLicensePack, UserRef> {

	@Override
	public UserRef apply(PersonalLicensePack pack) {
		PersonalLicenseRequisites license = new EnsurePersonalPackLicense().apply(pack);
		UserRef user = license.getUser();
		if (user == null) {
			user = LicensesFactory.eINSTANCE.createUserRef();
			license.setUser(user);
		}
		return user;
	}

}
