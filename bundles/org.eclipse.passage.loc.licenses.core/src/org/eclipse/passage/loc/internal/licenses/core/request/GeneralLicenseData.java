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
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.loc.internal.api.GeneralLicenseRequest;

public abstract class GeneralLicenseData implements GeneralLicenseRequest {

	private final ZoneId zone = ZoneId.systemDefault();
	private final String uuid = UUID.randomUUID().toString();
	private final Date stamp = new Date();

	private final Supplier<LicensePlanDescriptor> plan;
	private final Supplier<ProductVersionDescriptor> product;
	private final Supplier<Date> from;
	private final Supplier<Date> until;

	protected GeneralLicenseData(Supplier<LicensePlanDescriptor> plan, Supplier<ProductVersionDescriptor> product,
			Supplier<LocalDate> from, Supplier<LocalDate> until) {
		noNulls(plan, product, from, until);
		this.plan = plan;
		this.product = product;
		this.from = () -> Date.from(from.get().atStartOfDay(zone).toInstant());
		this.until = () -> Date.from(until.get().atStartOfDay(zone).toInstant());
	}

	private void noNulls(Object planObj, Object productObj, Object fromObj, Object untilObj) {
		String cls = getClass().getSimpleName();
		Objects.requireNonNull(planObj, cls + "::plan"); //$NON-NLS-1$
		Objects.requireNonNull(productObj, cls + "::product"); //$NON-NLS-1$
		Objects.requireNonNull(fromObj, cls + "::from"); //$NON-NLS-1$
		Objects.requireNonNull(untilObj, cls + "::until"); //$NON-NLS-1$
	}

	@Override
	public final Date validUntil() {
		return until.get();
	}

	@Override
	public final Date validFrom() {
		return from.get();
	}

	@Override
	public final String productVersion() {
		return product.get().getVersion();
	}

	@Override
	public final String productIdentifier() {
		return product.get().getProduct().getIdentifier();
	}

	@Override
	public final String plan() {
		return plan.get().getIdentifier();
	}

	@Override
	public final String identifier() {
		return uuid;
	}

	@Override
	public final Date creationDate() {
		return stamp;
	}

}
