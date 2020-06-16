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
 * 
 * <p>
 * Part of licensing {@linkplain Condition} that declares license demands
 * regarding the feature version.
 * </p>
 * <p>
 * This way or another a license defines which version of a feature is allowed
 * to use. It also defines <i>how far</i> from this specified version can be the
 * actual version of the feature that has been plugged to the product under
 * licensing.
 * </p>
 * 
 * @see Condition
 */
public interface VersionMatch {

	/**
	 * @return {@code version} definition string, can contain wildcards like
	 *         {@code 1.12.*} meaning <i>any build of version {@code 1.12}</i>
	 */
	String version();

	/**
	 * @return rule of version matching, like "perfect match" or "equal or greater".
	 * @see MatchingRule
	 */
	MatchingRule rule();

}
