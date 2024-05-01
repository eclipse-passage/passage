/*******************************************************************************
 * Copyright (c) 2018, 2024 ArSysOp
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

import java.util.Collection;
import java.util.Optional;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;

public interface OperatorLicenseService {

	/**
	 * Create new instance of Personal License Pack to be verified and issued
	 * 
	 */
	PersonalLicensePack createLicensePack(PersonalLicenseRequest request);

	/**
	 * Physically issue new Personal License Pack according to all the data supplied
	 * by the given data.
	 * 
	 */
	ServiceInvocationResult<IssuedLicense> issueLicensePack(PersonalLicensePack template);

	/**
	 * Create new instance of Floating License Pack to be verified and issued. Base
	 * data comes from the request, the rest can come from the existing license
	 * pack.
	 * 
	 * @since 1.1.0
	 */
	FloatingLicensePack createFloatingLicensePack(FloatingLicenseRequest request,
			Optional<FloatingLicensePack> template);

	/**
	 * Physically issue new Floating License Pack according to all the data supplied
	 * by the given data, optionally accompanied with the personal Floating License
	 * Access files.
	 * 
	 */
	ServiceInvocationResult<IssuedFloatingLicense> issueFloatingLicensePack(FloatingLicensePack pack,
			Collection<FloatingLicenseAccess> configs);

}
