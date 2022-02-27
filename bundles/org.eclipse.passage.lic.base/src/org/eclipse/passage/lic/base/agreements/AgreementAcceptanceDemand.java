/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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

import java.util.Collections;
import java.util.List;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.agreements.ResolvedAgreement;
import org.eclipse.passage.lic.api.requirements.Feature;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionLevel;

public final class AgreementAcceptanceDemand implements Requirement {

	private final Feature feature;
	private final List<ResolvedAgreement> agreement;

	public AgreementAcceptanceDemand(ResolvedAgreement agreement, LicensedProduct product) {
		this.feature = new GlobalAgreementSupportFeature(product).get();
		this.agreement = Collections.singletonList(agreement);
	}

	@Override
	public Feature feature() {
		return feature;
	}

	@Override
	public RestrictionLevel restrictionLevel() {
		return new RestrictionLevel.Fatal();
	}

	@Override
	public List<ResolvedAgreement> agreements() {
		return agreement;
	}

	@Override
	public Object source() {
		return "a license"; //$NON-NLS-1$
	}

}
