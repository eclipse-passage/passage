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

/**
 * Does not event tries to parse the given strings. Matching is successful iff
 * {@code required} string representing version is absolutely equal to
 * {@code allowed}. Comparison is case sensitive.
 * 
 * @author user
 * @since 2.1
 *
 */
public final class MatchingRulePerfect extends StrictMatchingRule {

	@Override
	public String identifier() {
		return "perfect"; //$NON-NLS-1$
	}

	@Override
	protected boolean safeMatch(String required, String allowed) {
		return Objects.equals(required, allowed);
	}

}
