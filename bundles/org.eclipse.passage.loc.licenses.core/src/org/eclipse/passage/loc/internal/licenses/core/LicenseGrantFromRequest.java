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
import org.eclipse.passage.lic.licenses.model.api.ValidityPeriodClosed;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.loc.internal.api.PersonalLicenseRequest;

@SuppressWarnings("restriction")
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
		grant.getFeature().setIdentifier(feature.getFeature().getIdentifier());
		grant.getFeature().getVersionMatch().setVersion(feature.getFeature().getVersionMatch().getVersion());
		grant.getFeature().getVersionMatch().setRule(feature.getFeature().getVersionMatch().getRule());
		grant.setCapacity(1);
		grant.getUserAuthentication().setExpression(request.conditionExpression());
		grant.getUserAuthentication().setType(request.conditionType());
		((ValidityPeriodClosed) grant.getValid()).setFrom(request.validFrom());
		((ValidityPeriodClosed) grant.getValid()).setUntil(request.validUntil());
		return grant;
	}

}
