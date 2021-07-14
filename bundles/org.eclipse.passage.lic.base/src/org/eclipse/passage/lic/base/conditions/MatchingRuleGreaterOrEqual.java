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
 * version is s <i>greater or equal</i> the the allowed one or not.
 * </p>
 * <p>
 * Key segment to check is {@code major}. For successful matching
 * {@code required.majour} must be greater or equal to {@code allowed.major}.
 * All lower segments (minor, service, qualifier) are ignored and do not affect
 * the matching.
 * </p>
 * <p>
 * {@code default required} always matches to any {@code allowed} version.
 * </p>
 * 
 * @since 2.1
 */
public final class MatchingRuleGreaterOrEqual extends StrictMatchingRule {

	@Override
	public String identifier() {
		return "greaterOrEqual"; //$NON-NLS-1$
	}

	@Override
	protected boolean safeMatch(String required, String allowed) {
		return new RequiredVersionVsAllowedVersion(required, allowed).match(0);
	}

}
