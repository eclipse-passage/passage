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
package org.eclipse.passage.lic.internal.licenses.model.migration;

import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

public final class EnsurePersonalPackLicense implements Function<PersonalLicensePack, PersonalLicenseRequisites> {

	@Override
	public PersonalLicenseRequisites apply(PersonalLicensePack pack) {
		return Optional.ofNullable(pack.getLicense()).orElseGet(() -> reset(pack));
	}

	private PersonalLicenseRequisites reset(PersonalLicensePack pack) {
		PersonalLicenseRequisites license = LicensesFactory.eINSTANCE.createPersonalLicenseRequisites();
		pack.setLicense(license);
		return license;
	}

}
