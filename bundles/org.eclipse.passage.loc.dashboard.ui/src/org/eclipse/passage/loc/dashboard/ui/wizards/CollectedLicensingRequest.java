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
package org.eclipse.passage.loc.dashboard.ui.wizards;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.internal.api.LicensingRequest;

final class CollectedLicensingRequest implements LicensingRequest {

	private final ZoneId zone = ZoneId.systemDefault();
	private final String uuid = UUID.randomUUID().toString();
	private final Date stamp = new Date();

	private final Supplier<Optional<LicensePlanDescriptor>> plan;
	private final Supplier<Optional<UserDescriptor>> user;
	private final Supplier<Optional<ProductVersionDescriptor>> product;
	private final Supplier<Optional<List<LocalDate>>> period;

	CollectedLicensingRequest(//
			Supplier<Optional<LicensePlanDescriptor>> plan, //
			Supplier<Optional<UserDescriptor>> user, //
			Supplier<Optional<ProductVersionDescriptor>> product, //
			Supplier<Optional<List<LocalDate>>> period) {
		this.plan = plan;
		this.user = user;
		this.product = product;
		this.period = period;
	}

	@Override
	public Date getValidUntil() {
		return Date.from(period.get().get().get(1).atStartOfDay(zone).toInstant());
	}

	@Override
	public Date getValidFrom() {
		return Date.from(period.get().get().get(0).atStartOfDay(zone).toInstant());
	}

	@Override
	public String getUserIdentifier() {
		return user.get().get().getEmail();
	}

	@Override
	public String getUserFullName() {
		return user.get().get().getFullName();
	}

	@Override
	public String getProductVersion() {
		return product.get().get().getVersion();
	}

	@Override
	public String getProductIdentifier() {
		return product.get().get().getProduct().getIdentifier();
	}

	@Override
	public String getPlanIdentifier() {
		return plan.get().get().getIdentifier();
	}

	@Override
	public String getIdentifier() {
		return uuid;
	}

	@Override
	public Date getCreationDate() {
		return stamp;
	}

	@Override
	public String getConditionType() {
		return user.get().get().getPreferredConditionType();
	}

	@Override
	public String getConditionExpression() {
		return user.get().get().getPreferredConditionExpression();
	}

}
