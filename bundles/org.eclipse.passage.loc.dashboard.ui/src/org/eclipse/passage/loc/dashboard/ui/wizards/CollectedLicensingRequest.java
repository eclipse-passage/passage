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
import java.util.UUID;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;
import org.eclipse.passage.loc.internal.api.LicensingRequest;

final class CollectedLicensingRequest implements LicensingRequest {

	private final ZoneId zone = ZoneId.systemDefault();
	private final String uuid = UUID.randomUUID().toString();
	private final Date stamp = new Date();

	private final LicensePlanDescriptor plan;
	private final UserDescriptor user;
	private final ProductVersionDescriptor product;
	private final LocalDate from;
	private final LocalDate until;

	CollectedLicensingRequest(LicensePlanDescriptor plan, UserDescriptor user, ProductVersionDescriptor product,
			LocalDate from, LocalDate until) {
		this.plan = plan;
		this.user = user;
		this.product = product;
		this.from = from;
		this.until = until;
	}

	@Override
	public Date getValidUntil() {
		return Date.from(until.atStartOfDay(zone).toInstant());
	}

	@Override
	public Date getValidFrom() {
		return Date.from(from.atStartOfDay(zone).toInstant());
	}

	@Override
	public String getUserIdentifier() {
		return user.getEmail();
	}

	@Override
	public String getUserFullName() {
		return user.getFullName();
	}

	@Override
	public String getProductVersion() {
		return product.getVersion();
	}

	@Override
	public String getProductIdentifier() {
		return product.getProduct().getIdentifier();
	}

	@Override
	public String getPlanIdentifier() {
		return plan.getIdentifier();
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
		return user.getPreferredConditionType();
	}

	@Override
	public String getConditionExpression() {
		return user.getPreferredConditionExpression();
	}

}
