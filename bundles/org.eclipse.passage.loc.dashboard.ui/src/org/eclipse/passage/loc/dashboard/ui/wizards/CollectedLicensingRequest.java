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
import org.eclipse.passage.loc.internal.api.PersonalLicenseRequest;

final class CollectedLicensingRequest implements PersonalLicenseRequest {

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
	public Date validUntil() {
		return Date.from(until.atStartOfDay(zone).toInstant());
	}

	@Override
	public Date validFrom() {
		return Date.from(from.atStartOfDay(zone).toInstant());
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
	public String productVersion() {
		return product.getVersion();
	}

	@Override
	public String productIdentifier() {
		return product.getProduct().getIdentifier();
	}

	@Override
	public String plan() {
		return plan.getIdentifier();
	}

	@Override
	public String identifier() {
		return uuid;
	}

	@Override
	public Date creationDate() {
		return stamp;
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
