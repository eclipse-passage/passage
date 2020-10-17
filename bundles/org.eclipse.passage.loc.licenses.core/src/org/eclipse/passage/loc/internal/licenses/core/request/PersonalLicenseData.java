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
package org.eclipse.passage.loc.internal.licenses.core.request;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.internal.api.PersonalLicenseRequest;

public final class PersonalLicenseData extends GeneralLicenseData implements PersonalLicenseRequest {

	private final UserDescriptor user;

	public PersonalLicenseData(UserDescriptor user, LicensePlanDescriptor plan, ProductVersionDescriptor product,
			LocalDate from, LocalDate until) {
		super(plan, product, from, until);
		Objects.requireNonNull(user, "PersonalLicenseData::user"); //$NON-NLS-1$
		this.user = user;
	}

	public PersonalLicenseData(UserDescriptor user, LicensePlanDescriptor plan, ProductVersionDescriptor product,
			Date from, Date until) {
		super(plan, product, from, until);
		Objects.requireNonNull(user, "PersonalLicenseData::user"); //$NON-NLS-1$
		this.user = user;
	}

	@Override
	public String user() {
		return user.getEmail();
	}

	@Override
	public String userFullName() {
		return user.getFullName();
	}

	@Override
	public String conditionType() {
		return user.getPreferredConditionType();
	}

	@Override
	public String conditionExpression() {
		return user.getPreferredConditionExpression();
	}
}
