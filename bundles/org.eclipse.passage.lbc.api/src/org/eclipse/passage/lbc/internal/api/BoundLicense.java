/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lbc.internal.api;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Represents a license that was loaded to the floating server to be operated.
 * 
 * @since 1.0
 */
public interface BoundLicense {

	// Condition identifier
	Supplier<Optional<String>> identifier();

	// Grant's capacity
	Supplier<Optional<Integer>> capacity();

	// How many times it was taken
	Supplier<Optional<Integer>> taken();

	// returns true if it is takeable and false if it is not.
	boolean takeable();

}
