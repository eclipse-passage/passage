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

/**
 * Does not tolerate {@code null}s as a version for matching.
 * 
 * @since 2.1
 */
public abstract class StrictMatchingRule implements MatchingRule {

	@Override
	public int hashCode() {
		return Objects.hash(identifier());
	}

	@Override
	public boolean equals(Object obj) {
		if (!MatchingRule.class.isInstance(obj)) {
			return false;
		}
		return identifier().equals(((MatchingRule) obj).identifier());
	}

	@Override
	public String toString() {
		return identifier();
	}

	@Override
	public final boolean match(String required, String allowed) {
		Objects.requireNonNull(required, "StrctMatchingRule::match::required"); //$NON-NLS-1$
		Objects.requireNonNull(allowed, "StrctMatchingRule::match::allowed"); //$NON-NLS-1$
		return safeMatch(required, allowed);
	}

	protected abstract boolean safeMatch(String required, String allowed);

}
