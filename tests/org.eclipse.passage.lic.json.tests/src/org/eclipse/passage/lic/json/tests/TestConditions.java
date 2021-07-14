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
package org.eclipse.passage.lic.json.tests;

import static org.junit.Assert.fail;
import static org.junit.Assume.assumeNoException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.passage.lic.api.EvaluationType;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.base.conditions.BaseCondition;
import org.eclipse.passage.lic.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.base.conditions.MatchingRuleCompatible;
import org.eclipse.passage.lic.base.conditions.MatchingRuleEquivalent;
import org.eclipse.passage.lic.base.conditions.MatchingRulePerfect;
import org.eclipse.passage.lic.internal.json.ConditionPack;
import org.eclipse.passage.lic.internal.json.JsonObjectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;

public final class TestConditions {

	private final List<Condition> conditions = Arrays.asList(//
			new BaseCondition("aaa", "who-are-you-guys?", //$NON-NLS-1$ //$NON-NLS-2$
					new BaseVersionMatch("1.1.1", new MatchingRuleEquivalent()), //$NON-NLS-1$
					new BaseValidityPeriodClosed(ZonedDateTime.now(), ZonedDateTime.now().plusSeconds(1)), //
					new BaseEvaluationInstructions(new EvaluationType.Of("mental-test"), "cow-says=moo;cat-says=meow")), //$NON-NLS-1$ //$NON-NLS-2$
			new BaseCondition("bbb", "extraterrestrial-verifier", //$NON-NLS-1$ //$NON-NLS-2$
					new BaseVersionMatch("17.0.8", new MatchingRuleCompatible()), //$NON-NLS-1$
					new BaseValidityPeriodClosed(ZonedDateTime.now(), ZonedDateTime.now().plusSeconds(2)), //
					new BaseEvaluationInstructions(new EvaluationType.Of("barrier-body-asessment"), //$NON-NLS-1$
							"heads=1;heands=2;legs=2")), //$NON-NLS-1$
			new BaseCondition("ccc", "good-witch", //$NON-NLS-1$ //$NON-NLS-2$
					new BaseVersionMatch("0.32.17.patch5", new MatchingRulePerfect()), //$NON-NLS-1$
					new BaseValidityPeriodClosed(ZonedDateTime.now(), ZonedDateTime.now().plusSeconds(2)), //
					new BaseEvaluationInstructions(new EvaluationType.Of("physical"), "thermal-conductivity=water")) //$NON-NLS-1$ //$NON-NLS-2$
	);

	Collection<Condition> conditions() {
		return conditions;
	}

	String textual() {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			output.write(new JsonObjectMapper().get().writeValueAsBytes(new ConditionPack(conditions)));
			return new String(output.toByteArray(), "UTF-8"); //$NON-NLS-1$
		} catch (JsonProcessingException e) {
			fail("Is not intendd to fail on valid data"); //$NON-NLS-1$
		} catch (IOException e) {
			assumeNoException(e); // file system misbehavior must not fail test
		}
		throw new RuntimeException("unreachable"); //$NON-NLS-1$
	}

}
