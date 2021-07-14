/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.api.conditions;

import org.eclipse.passage.lic.api.requirements.Requirement;

/**
 * <p>
 * Defines the way a two strings can be matched.
 * </p>
 * <p>
 * In scope of condition evaluation we find out if <i>version comes from
 * {@linkplain Requirement}</i> ({@code required}) {@code match} to version
 * definition string that comes from a license of source through
 * {@linkplain Condition} ({@code allowed}).
 * </p>
 * <p>
 * For example {@code perfect match} match rule would mean absolute equality.
 * </p>
 * 
 * @see VersionMatch
 * @see Condition
 * @since 2.1
 */
public abstract interface MatchingRule {

	/**
	 * Descriptive identifier of a rule.
	 * 
	 * @return
	 */
	String identifier();

	/**
	 * Find out if the {@code required} ({@code actual}) string {@code matches} with
	 * the {@code allowed} one ({@code expected}).
	 */
	boolean match(String required, String allowed);

}
