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
package org.eclipse.passage.lic.licenses.migration.tests.tobemoved;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lic.api.tests.conditions.mining.ConditionTransportContractTest;
import org.eclipse.passage.lic.internal.api.EvaluationType;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransport;
import org.eclipse.passage.lic.internal.base.conditions.BaseCondition;
import org.eclipse.passage.lic.internal.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.internal.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.internal.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleDefault;
import org.eclipse.passage.lic.internal.licenses.migration.tobemoved.XmiConditionTransport;

//@Ignore // fix TODOs to make it test something reasonable
@SuppressWarnings("restriction")
public class XmiConditionTransportTest extends ConditionTransportContractTest {
	@Override
	protected ConditionTransport transport() {
		return new XmiConditionTransport();
	}

	@Override
	protected Collection<Condition> conditions() {
		return Collections.singleton(new BaseCondition(//
				"aaa", //$NON-NLS-1$
				"doodle", //$NON-NLS-1$
				new BaseVersionMatch(//
						"1.2.3", //$NON-NLS-1$
						new MatchingRuleDefault()), //
				new BaseValidityPeriodClosed(//
						ZonedDateTime.now(), //
						ZonedDateTime.now().plusYears(1)), //
				new BaseEvaluationInstructions(//
						new EvaluationType.Hardware(), //
						"key=value"))); //$NON-NLS-1$
	}

	@Override
	protected String serialized(Collection<Condition> condition) {
		return "TODO: get an xmi-persistence license text"; //$NON-NLS-1$
	}

	@Override
	protected String serializedInvalid() {
		return "TODO: get an xmi-persistence license text and edit it"; //$NON-NLS-1$
	}

}
