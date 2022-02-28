/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
package org.eclipse.passage.lic.api.agreements;

import org.eclipse.passage.lic.api.requirements.Requirement;

public interface AgreementToAccept {

	/**
	 * <p>
	 * Agreement can be demanded for acceptance right where a licensing Requirement
	 * for a feature is declared Thus agreement is bound to a physical Requirement.
	 * </p>
	 * <p>
	 * It if also possible to demand an agreement acceptance in the body of a
	 * license. In this case an instance of {@linkplain AgreementAcceptanceDemand}
	 * is instantiated to represent a requirement under this demand. It references a
	 * product-representing-feature (it's id and name correspond to the ones of the
	 * product).
	 * </p>
	 */
	Requirement origin();

	ResolvedAgreement definition();

	AgreementState acceptance();

}
