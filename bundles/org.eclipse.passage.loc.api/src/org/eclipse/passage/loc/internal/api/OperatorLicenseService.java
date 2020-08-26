/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.loc.internal.api;

import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;

/**
 * 
 * @since 0.5.0
 *
 */
public interface OperatorLicenseService {

	/**
	 * 
	 * @param request
	 * @return the License Pack to be verified and issued
	 * 
	 */
	LicensePackDescriptor createLicensePack(LicensingRequest request);

	LicensingResult issueLicensePack(LicensingRequest request, LicensePackDescriptor template);

}
