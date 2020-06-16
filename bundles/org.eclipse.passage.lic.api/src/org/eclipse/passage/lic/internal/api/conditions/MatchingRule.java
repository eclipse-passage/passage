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
package org.eclipse.passage.lic.internal.api.conditions;

/**
 * <p>
 * Defines the way a actual string (tested) can be matched with a predefined
 * one. (beacon).
 * </p>
 * <p>
 * For example {@code perfect match} for any string type would mean absolute
 * equality, and {@code not less} match rule for a {@code version} strings would
 * mean that match is successful iff the tested version is greater or equal than
 * the beacon one.
 * </p>
 * 
 * @see VersionMatch
 * @see Condition
 */
public abstract interface MatchingRule {

	/**
	 * Descriptive identifier of a rule.
	 * 
	 * @return
	 */
	String identifier();

	/**
	 * Find out if the {@code tested} string {@code matches} with the {@code beacon}
	 * one.
	 */
	boolean match(String tested, String beacon);

}
