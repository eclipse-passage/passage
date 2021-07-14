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
package org.eclipse.passage.lic.base.conditions.evaluation;

import java.time.ZonedDateTime;
import java.util.Objects;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ConditionOrigin;
import org.eclipse.passage.lic.api.conditions.evaluation.Permission;

/**
 * @since 2.1
 */
public final class BasePermission implements Permission {

	private final LicensedProduct product;
	private final Condition condition;
	private final ZonedDateTime lease;
	private final ZonedDateTime expiration;
	private final ConditionOrigin origin;

	public BasePermission(LicensedProduct product, Condition condition, ZonedDateTime lease, ZonedDateTime expiration,
			ConditionOrigin origin) {
		Objects.requireNonNull(product, "BasePermission::product"); //$NON-NLS-1$
		Objects.requireNonNull(condition, "BasePermission::condition"); //$NON-NLS-1$
		Objects.requireNonNull(lease, "BasePermission::lease"); //$NON-NLS-1$
		Objects.requireNonNull(expiration, "BasePermission::expiration"); //$NON-NLS-1$
		Objects.requireNonNull(origin, "BasePermission::conditionOrigin"); //$NON-NLS-1$
		if (!lease.isBefore(expiration)) {
			throw new IllegalArgumentException("`Lease` date must strictly less than `expriation` date."); //$NON-NLS-1$
		}
		this.product = product;
		this.condition = condition;
		this.lease = lease;
		this.expiration = expiration;
		this.origin = origin;
	}

	@Override
	public LicensedProduct product() {
		return product;
	}

	@Override
	public Condition condition() {
		return condition;
	}

	@Override
	public ZonedDateTime leaseDate() {
		return lease;
	}

	@Override
	public ZonedDateTime expireDate() {
		return expiration;
	}

	@Override
	public ConditionOrigin conditionOrigin() {
		return origin;
	}

}
