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
package org.eclipse.passage.lic.api.agreements;

import java.util.Optional;

import org.eclipse.passage.lic.api.diagnostic.Trouble;

public interface AgreementState {

	/**
	 * Sort of identifier for agreement. Does not have semantics, used only to
	 * distinguish agreements on exposing to the end user.
	 */
	String name();

	/**
	 * @return is the agreement has been actively accepted by the end user or not.
	 */
	boolean accepted();

	/**
	 * Agreement text, stored in an encoding-unaware structure.
	 */
	byte[] content();

	/**
	 * Path to the agreement content file deployed.
	 * 
	 * @return
	 */
	// Path located();

	/**
	 * Agreement acceptance check could possible fail due to a wide variety of
	 * reasons, not only because the agreement has not indeed be accepted. Is the
	 * case state is still 'not accepted' and diagnostic is supplied
	 */
	Optional<Trouble> error();

}
