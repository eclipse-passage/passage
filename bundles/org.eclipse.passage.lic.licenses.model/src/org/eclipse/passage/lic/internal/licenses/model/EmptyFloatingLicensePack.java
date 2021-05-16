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

import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

public final class EmptyFloatingLicensePack implements Supplier<FloatingLicensePack> {

	@Override
	public FloatingLicensePack get() {
		FloatingLicensePack pack = LicensesFactory.eINSTANCE.createFloatingLicensePack();
		pack.setLicense(requisites());
		return pack;
	}

	private FloatingLicenseRequisites requisites() {
		FloatingLicenseRequisites license = LicensesFactory.eINSTANCE.createFloatingLicenseRequisites();
		license.setCompany(LicensesFactory.eINSTANCE.createCompanyRef());
		license.setProduct(LicensesFactory.eINSTANCE.createProductRef());
		license.setValid(LicensesFactory.eINSTANCE.createValidityPeriodClosed());
		return license;
	}

}
