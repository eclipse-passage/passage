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
package org.eclipse.passage.lic.internal.base.tests.conditions.evaluation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;

import org.eclipse.passage.lic.api.tests.fakes.conditions.FakeCondition;
import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakeExpressionTokenAssessmentService;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.EvaluationType;
import org.eclipse.passage.lic.internal.api.conditions.ValidityPeriod;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Emission;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionEvaluatorsRegistry;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionPasringRegistry;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionTokenAssessorsRegistry;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.PermissionEmittingService;
import org.eclipse.passage.lic.internal.api.diagnostic.code.LicenseCheckFailed;
import org.eclipse.passage.lic.internal.api.diagnostic.code.LicenseDoesNotMatch;
import org.eclipse.passage.lic.internal.api.diagnostic.code.LicenseInvalid;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.conditions.BaseEvaluationInstructions;
import org.eclipse.passage.lic.internal.base.conditions.BaseValidityPeriodClosed;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.AndsProtocolExpressionParseService;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.BasePermissionEmittingService;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.SimpleMapExpressionEvaluationService;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class BasePermissionEmittingServiceTest {

	@Test(expected = NullPointerException.class)
	public void demandsParsers() {
		new BasePermissionEmittingService(null, assessors(), evaluators());
	}

	@Test(expected = NullPointerException.class)
	public void demandsAssessors() {
		new BasePermissionEmittingService(parsers(), null, evaluators());
	}

	@Test(expected = NullPointerException.class)
	public void demandsEvluators() {
		new BasePermissionEmittingService(parsers(), assessors(), null);
	}

	@Test
	public void singleConditionFailureIsContagious() {
		BiasedAssessor morphology = morphologyAssessor(2, 1);
		BiasedAssessor dialog = dialogAssessor(4, 3);
		Emission emission = service(morphology, dialog)//
				.emit(Arrays.asList(humanoid(2, 1), teller(5, 3)), product());
		assertTrue(emission.failed());
		assertTrue(emission.failureDiagnostic().troubles().size() == 1);
		assertEquals(2, morphology.askedKeys().size());
		assertTrue(dialog.askedKeys().contains("storytelling"));//$NON-NLS-1$
	}

	@Test
	public void differentEvaluationTypes() {
		// given: conditions of different evaluation types
		BiasedAssessor morphology = morphologyAssessor(2, 1);
		BiasedAssessor dialog = dialogAssessor(4, 3);
		// when: emit on valid data
		Emission emission = service(morphology, dialog)//
				.emit(Arrays.asList(//
						humanoid(2, 1), //
						teller(4, 3)//
				), product());
		// then: all conditions evaluated to permissions
		assertFalse(emission.failed());
		assertEquals(2, emission.permissions().size());
		assertEquals(2, morphology.askedKeys().size());
		assertEquals(2, dialog.askedKeys().size());
	}

	@Test
	public void unsupportedEvaluationTypeFailsEmission() {
		BiasedAssessor morphology = morphologyAssessor(4, 2);
		Emission emission = service(morphology).emit(Arrays.asList(teller(4, 3)), product());
		assertTrue(emission.failed());
		assertEquals(1, emission.failureDiagnostic().troubles().size());
		assertEquals(new LicenseCheckFailed(), emission.failureDiagnostic().troubles().get(0).code());
	}

	@Test
	public void corruptedExpressionFailsEmission() {
		Emission emission = service(morphologyAssessor(1, 1)).emit(Arrays.asList(corrupted()), product());
		assertTrue(emission.failed());
		assertEquals(new LicenseInvalid(), emission.failureDiagnostic().troubles().get(0).code());
	}

	@Test
	public void negativeAssessmentExpressionFailsEmission() {
		Emission emission = service(morphologyAssessor(1, 1)).emit(Arrays.asList(humanoid(2, 1)), product());
		assertTrue(emission.failed());
		assertEquals(new LicenseDoesNotMatch(), emission.failureDiagnostic().troubles().get(0).code());
	}

	private PermissionEmittingService service(BiasedAssessor... assessors) {
		return new BasePermissionEmittingService(parsers(), assessors(assessors), evaluators());
	}

	private ExpressionPasringRegistry parsers() {
		return () -> new ReadOnlyRegistry<>(Collections.singleton(new AndsProtocolExpressionParseService()));
	}

	private ExpressionTokenAssessorsRegistry assessors() {
		return () -> new ReadOnlyRegistry<>(Collections.singleton(new FakeExpressionTokenAssessmentService()));
	}

	private ExpressionEvaluatorsRegistry evaluators() {
		return () -> new ReadOnlyRegistry<>(Collections.singleton(new SimpleMapExpressionEvaluationService()));
	}

	private ExpressionTokenAssessorsRegistry assessors(BiasedAssessor... assessors) {
		return () -> new ReadOnlyRegistry<>(Arrays.asList(assessors));
	}

	private LicensedProduct product() {
		return new BaseLicensedProduct("test-condition-evaluation", "1.2.64"); //$NON-NLS-1$//$NON-NLS-2$
	}

	private Condition corrupted() {
		return new FakeCondition().withEvaluationInstructions(//
				new BaseEvaluationInstructions(//
						new EvaluationType.Of("morphology"), //$NON-NLS-1$
						"legs=;eyes=1")) //$NON-NLS-1$
				.withValidityPeriod(hour());
	}

	private Condition humanoid(int legs, int eyes) {
		return new FakeCondition().withEvaluationInstructions(//
				new BaseEvaluationInstructions(//
						new EvaluationType.Of("morphology"), //$NON-NLS-1$
						"legs=" + legs + ";eyes=" + eyes)) //$NON-NLS-1$//$NON-NLS-2$
				.withValidityPeriod(hour());
	}

	private Condition teller(int storytelling, int synonims) {
		return new FakeCondition().withEvaluationInstructions(//
				new BaseEvaluationInstructions(//
						new EvaluationType.Of("dialog"), //$NON-NLS-1$
						"storytelling=" + storytelling + ";synonims=" + synonims)) //$NON-NLS-1$//$NON-NLS-2$
				.withValidityPeriod(hour());
	}

	private ValidityPeriod hour() {
		return new BaseValidityPeriodClosed(//
				ZonedDateTime.now(), //
				ZonedDateTime.now().plusHours(1));
	}

	private BiasedAssessor morphologyAssessor(int legs, int eyes) {
		return new BiasedAssessor("morphology")// //$NON-NLS-1$
				.withAnswer("legs", Integer.toString(legs)) //$NON-NLS-1$
				.withAnswer("eyes", Integer.toString(eyes)); //$NON-NLS-1$
	}

	private BiasedAssessor dialogAssessor(int storytelling, int synonims) {
		return new BiasedAssessor("dialog")// //$NON-NLS-1$
				.withAnswer("storytelling", Integer.toString(storytelling)) //$NON-NLS-1$
				.withAnswer("synonims", Integer.toString(synonims)); //$NON-NLS-1$
	}
}
