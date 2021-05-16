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

import java.util.function.Function;

import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicenseRequisites;
import org.eclipse.passage.lic.licenses.model.api.ProductRef;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;

public final class EnsurePersonalPackProduct implements Function<PersonalLicensePack, ProductRef> {

	@Override
	public ProductRef apply(PersonalLicensePack pack) {
		PersonalLicenseRequisites license = new EnsurePersonalPackLicense().apply(pack);
		ProductRef product = license.getProduct();
		if (product == null) {
			product = LicensesFactory.eINSTANCE.createProductRef();
			license.setProduct(product);
		}
		return product;
	}

}
