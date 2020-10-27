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

import org.eclipse.passage.lic.floating.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.licenses.LicensePackDescriptor;

/**
 * 
 * @since 0.5.0
 *
 */
public interface OperatorLicenseService {

	/**
	 * Create new instance of Personal License Pack to be verified and issued
	 */
	LicensePackDescriptor createLicensePack(PersonalLicenseRequest request);

	/**
	 * Physically issue new Personal License Pack according to all the data supplied
	 * by the given data.
	 */
	ServiceInvocationResult<IssuedLicense> issueLicensePack(PersonalLicenseRequest request,
			LicensePackDescriptor template);

	/**
	 * Create new instance of Floating License Pack to be verified and issued
	 */
	FloatingLicensePack createFloatingLicensePack(FloatingLicenseRequest request);

	/**
	 * Physically issue new Floating License Pack according to all the data supplied
	 * by the given data.
	 */
	ServiceInvocationResult<IssuedFloatingLicense> issueFloatingLicensePack(FloatingLicenseRequest request,
			FloatingLicensePack template);
}
