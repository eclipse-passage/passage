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
package org.eclipse.passage.lic.internal.licenses.model;

import java.util.function.Supplier;

import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

public final class EmptyPersonalLicensePack implements Supplier<PersonalLicensePack> {

	@Override
	public PersonalLicensePack get() {
		PersonalLicensePack pack = LicensesFactory.eINSTANCE.createPersonalLicensePack();
		pack.setLicense(requisites());
		return pack;
	}

	private PersonalLicenseRequisites requisites() {
		PersonalLicenseRequisites license = LicensesFactory.eINSTANCE.createPersonalLicenseRequisites();
		license.setUser(LicensesFactory.eINSTANCE.createUserRef());
		license.setProduct(LicensesFactory.eINSTANCE.createProductRef());
		license.setValid(LicensesFactory.eINSTANCE.createValidityPeriodClosed());
		return license;
	}

}
