/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
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

import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.eclipse.passage.lic.products.model.api.ProductVersion;
import org.eclipse.passage.lic.users.model.api.User;
import org.eclipse.passage.loc.internal.api.PersonalLicenseRequest;

public final class PersonalLicenseData extends GeneralLicenseData implements PersonalLicenseRequest {

	private final Supplier<User> user;

	public PersonalLicenseData(Supplier<User> user, Supplier<LicensePlan> plan, Supplier<ProductVersion> product,
			Supplier<LocalDate> from, Supplier<LocalDate> until) {
		super(plan, product, from, until);
		this.user = Objects.requireNonNull(user);
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
