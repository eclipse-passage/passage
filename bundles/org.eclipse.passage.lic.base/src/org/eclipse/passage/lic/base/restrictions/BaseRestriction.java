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
package org.eclipse.passage.lic.base.restrictions;

import java.util.Objects;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.Restriction;

/**
 * 
 * @since 2.1
 */
public final class BaseRestriction implements Restriction {

	private final LicensedProduct product;
	private final Requirement requirement;
	private final TroubleCode reason;

	public BaseRestriction(LicensedProduct product, Requirement requirement, TroubleCode reason) {
		Objects.requireNonNull(product, "BaseRestriction::product"); //$NON-NLS-1$
		Objects.requireNonNull(requirement, "BaseRestriction::requirement"); //$NON-NLS-1$
		Objects.requireNonNull(reason, "BaseRestriction::reason"); //$NON-NLS-1$
		this.product = product;
		this.requirement = requirement;
		this.reason = reason;
	}

	@Override
	public LicensedProduct product() {
		return product;
	}

	@Override
	public Requirement unsatisfiedRequirement() {
		return requirement;
	}

	@Override
	public TroubleCode reason() {
		return reason;
	}

}
