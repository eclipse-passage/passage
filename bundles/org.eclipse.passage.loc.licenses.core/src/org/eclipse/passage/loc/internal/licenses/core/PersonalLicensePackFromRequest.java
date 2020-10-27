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

import org.eclipse.emf.common.util.EList;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.eclipse.passage.loc.internal.api.PersonalLicenseRequest;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;

final class PersonalLicensePackFromRequest implements Supplier<LicensePack> {

	private final PersonalLicenseRequest request;
	private final LicenseRegistry licenses;

	PersonalLicensePackFromRequest(PersonalLicenseRequest request, LicenseRegistry licesnses) {
		this.request = request;
		this.licenses = licesnses;
	}

	@Override
	public LicensePack get() {
		LicensesFactory licenseFactory = LicensesFactory.eINSTANCE;
		LicensePack pack = licenseFactory.createLicensePack();
		pack.setRequestIdentifier(request.identifier());
		pack.setUserIdentifier(request.user());
		pack.setUserFullName(request.userFullName());
		pack.setProductIdentifier(request.productIdentifier());
		pack.setProductVersion(request.productVersion());
		String planIdentifier = request.plan();
		pack.setPlanIdentifier(planIdentifier);
		LicensePlanDescriptor plan = licenses.getLicensePlan(planIdentifier);
		if (plan == null) {
			return pack; // FIXME: ServiceInvocationResult<LicensePack> should probably be used
		}
		Iterable<? extends LicensePlanFeatureDescriptor> features = plan.getLicensePlanFeatures();
		EList<LicenseGrant> grants = pack.getLicenseGrants();
		for (LicensePlanFeatureDescriptor planFeature : features) {
			LicenseGrant grant = new LicenseGrantFromRequest(planFeature, request).get();
			grants.add(grant);
		}
		return pack;
	}

}
