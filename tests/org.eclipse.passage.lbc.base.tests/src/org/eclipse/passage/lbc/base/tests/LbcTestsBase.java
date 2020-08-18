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
package org.eclipse.passage.lbc.base.tests;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;

import org.eclipse.passage.lbc.internal.api.Requester;
import org.eclipse.passage.lbc.internal.base.BaseRequester;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriod;
import org.eclipse.passage.lic.internal.api.conditions.VersionMatch;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;
import org.eclipse.passage.lic.internal.base.conditions.BaseCondition;
import org.eclipse.passage.lic.internal.base.conditions.BaseConditionPack;
import org.eclipse.passage.lic.internal.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.internal.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.internal.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.internal.base.conditions.MatchingRuleDefault;

@SuppressWarnings("restriction")
public abstract class LbcTestsBase {

	protected ProductIdentifier identifier() {
		return new ProductIdentifier("identifier"); //$NON-NLS-1$
	}

	protected ProductVersion version() {
		return new ProductVersion("version"); //$NON-NLS-1$
	}

	protected Requester requester() {
		return new BaseRequester("process", "hardware", "feature"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	protected String userValue() {
		return "hardware"; //$NON-NLS-1$
	}

	protected String identifierValue() {
		return "identifier"; //$NON-NLS-1$
	}

	protected String versionValue() {
		return "version"; //$NON-NLS-1$
	}

	protected String origin() {
		return "floating"; //$NON-NLS-1$
	}

	protected ConditionPack conditionPack() {
		return new BaseConditionPack(origin(), Collections.singletonList(condition()));
	}

	protected Condition condition() {
		return new BaseCondition(identifierValue(), versionMatch(), validityPeriod(), evaluationInstructions());
	}

	private BaseEvaluationInstructions evaluationInstructions() {
		return new BaseEvaluationInstructions(new EvaluationType.Hardware(), "expression"); //$NON-NLS-1$
	}

	private ValidityPeriod validityPeriod() {
		return new BaseValidityPeriodClosed(ZonedDateTime.now(),
				ZonedDateTime.of(2021, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault())); // $NON-NLS-1$
	}

	private VersionMatch versionMatch() {
		return new BaseVersionMatch("version", new MatchingRuleDefault()); //$NON-NLS-1$
	}

}
