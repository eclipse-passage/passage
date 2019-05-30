/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.api;

import java.util.Date;

import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.products.ProductVersionDescriptor;
import org.eclipse.passage.lic.users.UserDescriptor;

/**
 * 
 * @since 0.5.0
 *
 */
public interface OperatorLicenseService {

	/**
	 * 
	 * @param licensePlan
	 * @param user
	 * @param productVersion
	 * @param from
	 * @param until
	 * @return the License Pack to be verified and issued
	 * 
	 */
	LicensePackDescriptor createLicensePack(LicensePlanDescriptor licensePlan, UserDescriptor user,
			ProductVersionDescriptor productVersion, Date from, Date until);

	LicensingResult issueLicensePack(LicensePackDescriptor licensePack);

}
