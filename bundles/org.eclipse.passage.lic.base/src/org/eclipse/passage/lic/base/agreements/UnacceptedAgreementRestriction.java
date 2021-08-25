/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.base.agreements;

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.agreements.AgreementToAccept;
import org.eclipse.passage.lic.api.restrictions.Restriction;
import org.eclipse.passage.lic.base.diagnostic.code.AgreementNotAccepted;
import org.eclipse.passage.lic.base.restrictions.BaseRestriction;

public final class UnacceptedAgreementRestriction implements Supplier<Restriction> {

	private final LicensedProduct product;
	private final AgreementToAccept agreement;

	public UnacceptedAgreementRestriction(LicensedProduct product, AgreementToAccept agreement) {
		this.product = product;
		this.agreement = agreement;
	}

	@Override
	public Restriction get() {
		return new BaseRestriction(product, agreement.origin(), new AgreementNotAccepted());
	}

}
