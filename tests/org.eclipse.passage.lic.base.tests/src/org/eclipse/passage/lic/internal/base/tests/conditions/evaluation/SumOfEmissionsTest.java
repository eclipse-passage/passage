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

import java.util.Collections;

import org.eclipse.passage.lic.api.tests.fakes.conditions.evaluation.FakePermission;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Emission;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Emission.Failed;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Emission.Successful;
import org.eclipse.passage.lic.internal.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.internal.base.conditions.evaluation.SumOfEmissions;
import org.eclipse.passage.lic.internal.base.diagnostic.BaseFailureDiagnostic;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class SumOfEmissionsTest {

	@Test
	public void sumOfSuccessesIsSuccess() {
		assertFalse(new SumOfEmissions().apply(success(), success()).failed());
	}

	@Test
	public void sumOfSuccessesAndFailureIsFailure() {
		assertTrue(new SumOfEmissions().apply(success(), failure()).failed());
	}

	@Test
	public void sumOfFailureAndSuccessIsFailure() {
		assertTrue(new SumOfEmissions().apply(failure(), success()).failed());
	}

	@Test
	public void sumOfFailuresIsFailure() {
		assertTrue(new SumOfEmissions().apply(failure(), failure()).failed());
	}

	@Test(expected = NullPointerException.class)
	public void prohibitsNullFirstOperand() {
		new SumOfEmissions().apply(null, success());
	}

	@Test(expected = NullPointerException.class)
	public void prohibitsNullSecondOperand() {
		new SumOfEmissions().apply(success(), null);
	}

	@Test
	public void sumsPermissions() {
		assertEquals(2, //
				new SumOfEmissions().apply(//
						new Emission.Successful(new FakePermission()), //
						new Emission.Successful(new FakePermission()))//
						.permissions().size());
	}

	@Test
	public void sumsDiagnostics() {
		assertEquals(2, new SumOfEmissions().apply(failure(), failure()).failureDiagnostic().troubles().size());
	}

	private Successful success() {
		return new Emission.Successful(Collections.emptyList());
	}

	private Failed failure() {
		return new Emission.Failed(new BaseFailureDiagnostic(new TroubleCode.Of(0, "0"), "")); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
