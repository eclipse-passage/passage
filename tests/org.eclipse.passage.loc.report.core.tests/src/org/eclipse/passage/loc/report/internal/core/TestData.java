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
package org.eclipse.passage.loc.report.internal.core;

import java.util.Set;

import org.eclipse.passage.lic.internal.users.model.EmptyUser;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.lic.users.UserOriginDescriptor;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.lic.users.model.api.UserOrigin;
import org.eclipse.passage.lic.users.model.meta.UsersFactory;
import org.eclipse.passage.loc.yars.internal.api.Storage;

@SuppressWarnings("restriction")
public abstract class TestData<S extends Storage<?>> {

	public abstract Set<String> csv();

	public abstract S storage();

	protected final UserDescriptor user(String id, String email, String name) {
		User user = new EmptyUser().get();
		user.setIdentifier(id);
		user.getContact().setEmail(email);
		user.getContact().setName(name);
		return user;
	}

	protected final UserOriginDescriptor company(String[] data) {
		UserOrigin company = UsersFactory.eINSTANCE.createUserOrigin();
		company.setName(data[0]);
		return company;
	}

	protected final LicensePlanDescriptor plan(String id, String name) {
		LicensePlan plan = LicensesFactory.eINSTANCE.createLicensePlan();
		plan.setIdentifier(id);
		plan.setName(name);
		return plan;
	}

}
