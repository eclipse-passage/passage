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
package org.eclipse.passage.lic.api.tests.conditions.evaluation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

import org.eclipse.passage.lic.internal.api.conditions.evaluation.Emission;
import org.junit.Test;

@SuppressWarnings("restriction")
public abstract class EmissionContractTest {

	@Test(expected = Exception.class)
	public void failedEmissionCannotHoldPermission() {
		Emission failure = failed();
		assumeFalse(failure.successful());
		failure.permissions();
	}

	@Test
	public void successfulEmissionMustHoldPermission() {
		Emission success = successful();
		assumeTrue(success.successful());
		assertNotNull(success.permissions());
	}

	@Test
	public void failedEmissionMustHoldDiagnosis() {
		Emission failure = failed();
		assumeFalse(failure.successful());
		assertNotNull(failure.failureDiagnostic());
	}

	@Test(expected = Exception.class)
	public void successfulEmissionCannotHoldDiagnosis() {
		Emission success = successful();
		assumeTrue(success.successful());
		success.failureDiagnostic();

	}

	protected abstract Emission failed();

	protected abstract Emission successful();
}
