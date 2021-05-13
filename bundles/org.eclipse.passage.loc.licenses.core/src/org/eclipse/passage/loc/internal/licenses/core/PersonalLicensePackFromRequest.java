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

import org.eclipse.passage.lic.internal.licenses.model.EmptyPersonalLicensePack;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanFeatureDescriptor;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.loc.internal.api.PersonalLicenseRequest;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;

@SuppressWarnings("restriction")
final class PersonalLicensePackFromRequest implements Supplier<PersonalLicensePack> {

	private final PersonalLicenseRequest request;
	private final LicenseRegistry licenses;

	PersonalLicensePackFromRequest(PersonalLicenseRequest request, LicenseRegistry licesnses) {
		this.request = request;
		this.licenses = licesnses;
	}

	@Override
	public PersonalLicensePack get() {
		PersonalLicensePack pack = new EmptyPersonalLicensePack().get();
		installRequisites(pack);
		installGrants(pack);
		return pack;
	}

	private void installRequisites(PersonalLicensePack pack) {
		pack.getLicense().getUser().setIdentifier(request.user());
		pack.getLicense().getUser().setName(request.userFullName());
		pack.getLicense().getProduct().setIdentifier(request.productIdentifier());
		pack.getLicense().getProduct().setVersion(request.productVersion());
		pack.getLicense().setPlan(request.plan());
	}

	private void installGrants(PersonalLicensePack pack) {
		LicensePlanDescriptor plan = licenses.getLicensePlan(pack.getLicense().getPlan());
		for (LicensePlanFeatureDescriptor feature : plan.getLicensePlanFeatures()) {
			pack.getGrants().add(new LicenseGrantFromRequest(feature, request).get());
		}
	}

}
