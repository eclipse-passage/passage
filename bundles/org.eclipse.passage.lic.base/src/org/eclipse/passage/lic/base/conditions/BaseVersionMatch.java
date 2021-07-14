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

import java.util.Objects;

import org.eclipse.passage.lic.api.conditions.MatchingRule;
import org.eclipse.passage.lic.api.conditions.VersionMatch;

/**
 * @since 2.1
 */
public final class BaseVersionMatch implements VersionMatch {

	private final String version;
	private final MatchingRule rule;

	public BaseVersionMatch(String version, MatchingRule rule) {
		Objects.requireNonNull(version, "BaseVersionMatch::Version"); //$NON-NLS-1$
		Objects.requireNonNull(rule, "BaseVersionMatch::MatchingRule"); //$NON-NLS-1$
		this.version = version;
		this.rule = rule;
	}

	@Override
	public String version() {
		return version;
	}

	@Override
	public MatchingRule rule() {
		return rule;
	}

}
