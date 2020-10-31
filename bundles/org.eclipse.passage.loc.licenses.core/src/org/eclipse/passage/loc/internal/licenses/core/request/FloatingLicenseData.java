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
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.conditions.EvaluationInstructions;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.internal.api.FloatingLicenseRequest;

public final class FloatingLicenseData extends GeneralLicenseData implements FloatingLicenseRequest {

	private final Collection<UserDescriptor> users;
	private final int capacity;

	public FloatingLicenseData(Collection<UserDescriptor> users, LicensePlanDescriptor plan,
			ProductVersionDescriptor product, Date from, Date until, int capacity) {
		super(plan, product, from, until);
		Objects.requireNonNull(users, "PersonalLicenseData::users"); //$NON-NLS-1$
		this.users = users; // FIXME: work for caching function: keep Map and reimplement retrieves
		this.capacity = capacity;
	}

	public FloatingLicenseData(Collection<UserDescriptor> users, LicensePlanDescriptor plan,
			ProductVersionDescriptor product, LocalDate from, LocalDate until, int capacity) {
		super(plan, product, from, until);
		Objects.requireNonNull(users, "PersonalLicenseData::users"); //$NON-NLS-1$
		this.users = users;
		this.capacity = capacity;
	}

	@Override
	public Collection<String> users() {
		return users.stream()//
				.map(UserDescriptor::getEmail) //
				.collect(Collectors.toList());
	}

	@Override
	public EvaluationInstructions userAuthentication(String user) {
		UserDescriptor target = user(user);
		return new BaseEvaluationInstructions(//
				new EvaluationType.Of(target.getPreferredConditionExpression()), //
				target.getPreferredConditionType());
	}

	private UserDescriptor user(String identifier) {
		return users.stream()//
				.filter(user -> identifier.equals(user.getIdentifier()))//
				.findAny()//
				.get(); // yah, fail if not found, it's a development problem
	}

	@Override
	public int defaultCapacity() {
		return capacity;
	}

}
