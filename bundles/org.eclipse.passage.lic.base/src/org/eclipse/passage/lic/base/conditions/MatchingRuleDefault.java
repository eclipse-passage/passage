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
 * @since 2.1
 */
public final class MatchingRuleDefault extends StrictMatchingRule {

	private final StrictMatchingRule assignee;

	public MatchingRuleDefault() {
		assignee = new MatchingRuleCompatible();
	}

	@Override
	public String identifier() {
		return assignee.identifier();
	}

	@Override
	protected boolean safeMatch(String required, String allowed) {
		return assignee.safeMatch(required, allowed);
	}

}
