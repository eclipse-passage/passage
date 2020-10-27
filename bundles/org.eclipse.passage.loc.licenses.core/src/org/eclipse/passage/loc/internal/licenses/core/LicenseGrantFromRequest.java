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
package org.eclipse.passage.loc.internal.licenses.core;

import java.util.function.Supplier;

import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.loc.internal.api.PersonalLicenseRequest;

final class LicenseGrantFromRequest implements Supplier<LicenseGrant> {

	private final LicensePlanFeatureDescriptor feature;
	private final PersonalLicenseRequest request;

	public LicenseGrantFromRequest(LicensePlanFeatureDescriptor feature, PersonalLicenseRequest request) {
		this.feature = feature;
		this.request = request;
	}

	@Override
	public LicenseGrant get() {
		LicensesFactory licenseFactory = LicensesFactory.eINSTANCE;
		LicenseGrant grant = licenseFactory.createLicenseGrant();
		grant.setFeatureIdentifier(feature.getFeatureIdentifier());
		grant.setMatchVersion(feature.getMatchVersion());
		grant.setMatchRule(feature.getMatchRule());
		grant.setCapacity(1);
		grant.setConditionExpression(request.conditionExpression());
		grant.setConditionType(request.conditionType());
		grant.setValidFrom(request.validFrom());
		grant.setValidUntil(request.validUntil());
		return grant;
	}

}
