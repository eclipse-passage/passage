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
package org.eclipse.passage.loc.internal.licenses.core.request;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.EvaluationInstructions;
import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.internal.api.FloatingLicenseRequest;

public final class FloatingLicenseData extends GeneralLicenseData implements FloatingLicenseRequest {

	private final Supplier<Collection<UserDescriptor>> users;
	private final Supplier<Integer> capacity;

	public FloatingLicenseData(Supplier<Collection<UserDescriptor>> users, Supplier<LicensePlanDescriptor> plan,
			Supplier<ProductVersionDescriptor> product, Supplier<LocalDate> from, Supplier<LocalDate> until,
			Supplier<Integer> capacity) {
		super(plan, product, from, until);
		Objects.requireNonNull(users, "PersonalLicenseData::users"); //$NON-NLS-1$
		this.users = users;
		this.capacity = capacity;
	}

	@Override
	public Collection<String> users() {
		return users.get().stream()//
				.map(user -> user.getContact().getEmail()) //
				.collect(Collectors.toList());
	}

	@Override
	public EvaluationInstructions userAuthentication(String user) {
		UserDescriptor target = user(user);
		return new BaseEvaluationInstructions(//
				new EvaluationType.Of(target.getPreferredEvaluationExpression()), //
				target.getPreferredEvaluationType());
	}

	private UserDescriptor user(String identifier) {
		return users.get().stream()//
				.filter(user -> identifier.equals(user.getContact().getEmail()))//
				.findAny()//
				.get(); // yah, fail if not found, it's a development problem
	}

	@Override
	public int defaultCapacity() {
		return capacity.get();
	}

}
