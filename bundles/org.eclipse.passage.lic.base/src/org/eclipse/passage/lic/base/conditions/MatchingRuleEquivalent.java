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
package org.eclipse.passage.lic.base.conditions;

/**
 * <p>
 * We check two versions: {@code required} (comes from requirements) and
 * {@code allowed} (originated in licenses) to find out if the requirement
 * version is s <i>equivalent</i> to the allowed one or not.
 * </p>
 * <p>
 * Key segment to check is {@code service}. For successful matching
 * {@code required.service} must be greater or equal to
 * {@code allowed.service}.All higher segments (major, minor) are expected to be
 * equal for successful match. All lower segments (qualifier) are ignored and do
 * not affect the matching.
 * </p>
 * <p>
 * So, formally, {@code required} and {@code allowed} are <i>equivalent</i> iff
 * </p>
 * <ul>
 * <li>their {@code major} segments are equal AND</li>
 * <li>their {@code minor} segments are equal AND</li>
 * <li>{@code required.service} >= {@code allowed.service}</li>
 * </ul>
 * <p>
 * {@code default required} always matches to any {@code allowed} version.
 * </p>
 * 
 * @since 2.1
 */
public final class MatchingRuleEquivalent extends StrictMatchingRule {

	@Override
	public String identifier() {
		return "equivalent"; //$NON-NLS-1$
	}

	@Override
	protected boolean safeMatch(String required, String allowed) {
		return new RequiredVersionVsAllowedVersion(required, allowed).match(2);
	}

}
