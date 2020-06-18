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
package org.eclipse.passage.lic.internal.base.conditions.contract;

import org.eclipse.passage.lic.internal.api.conditions.MatchingRule;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRulePerfect;

@SuppressWarnings("restriction")
public final class MatchingRulePerfectContractTest extends BaseMatchingRuleContractTest {

	@Override
	protected MatchingRule rule() {
		return new MatchingRulePerfect();
	}

}
