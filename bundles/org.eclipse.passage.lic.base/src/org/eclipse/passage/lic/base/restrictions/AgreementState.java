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
package org.eclipse.passage.lic.base.restrictions;

import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.Restriction;
import org.eclipse.passage.lic.base.diagnostic.code.AgreementNotAccepted;

final class AgreementState {

	private final LicensedProduct product;
	private final List<Requirement> requirements;

	AgreementState(LicensedProduct product, List<Requirement> requirements) {
		this.product = product;
		this.requirements = requirements;
	}

	AgreementState(LicensedProduct product, Requirement requirement) {
		this(product, Collections.singletonList(requirement));
	}

	AgreementState(LicensedProduct product) {
		this(product, Collections.emptyList());
	}

	List<Restriction> restrictions() {
		// TODO: produce proper restrictions
		throw new UnsupportedOperationException();
	}

	private Restriction agreementNotAccepted(Requirement requirement, LicensedProduct product) {
		return new BaseRestriction(product, requirement, new AgreementNotAccepted());
	}

	// TODO: assessment for list of ResolvedAgreements
	static final class Sum implements BinaryOperator<AgreementState> {

		@Override
		public AgreementState apply(AgreementState first, AgreementState second) {
			// TODO Auto-generated method stub
			// TODO: fail on summing states of different products
			return null;
		}

	}

}
