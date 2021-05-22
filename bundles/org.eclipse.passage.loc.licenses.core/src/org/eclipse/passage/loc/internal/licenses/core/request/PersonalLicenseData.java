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
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.internal.api.PersonalLicenseRequest;

public final class PersonalLicenseData extends GeneralLicenseData implements PersonalLicenseRequest {

	private final Supplier<UserDescriptor> user;

	public PersonalLicenseData(Supplier<UserDescriptor> user, Supplier<LicensePlanDescriptor> plan,
			Supplier<ProductVersionDescriptor> product, Supplier<LocalDate> from, Supplier<LocalDate> until) {
		super(plan, product, from, until);
		Objects.requireNonNull(user, "PersonalLicenseData::user"); //$NON-NLS-1$
		this.user = user;
	}

	@Override
	public String user() {
		return user.get().getContact().getEmail();
	}

	@Override
	public String userFullName() {
		return user.get().getContact().getName();
	}

	@Override
	public String conditionType() {
		return user.get().getPreferredEvaluationType();
	}

	@Override
	public String conditionExpression() {
		return user.get().getPreferredEvaluationExpression();
	}
}
