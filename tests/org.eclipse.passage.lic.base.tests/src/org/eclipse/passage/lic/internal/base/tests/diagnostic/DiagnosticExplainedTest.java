/*******************************************************************************
 * Copyright (c) 2020, 2026 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.internal.base.tests.diagnostic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.diagnostic.TroubleCode;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.junit.jupiter.api.Test;

public final class DiagnosticExplainedTest {

	@Test
	public void doesNotExplainNull() {
		assertThrows(NullPointerException.class, () -> new DiagnosticExplained(null));
	}

	@Test
	public void explainsEmpty() {
		assertTrue(new DiagnosticExplained(new BaseDiagnostic()).get().trim().length() > 0);
	}

	@Test
	public void explainsDetails() {
		String explanation = new DiagnosticExplained(diagnostic()).get();
		assertTrue(explanation.contains("777")); //$NON-NLS-1$
		assertTrue(explanation.contains("555")); //$NON-NLS-1$
		assertTrue(explanation.contains("severe demand explanation")); //$NON-NLS-1$
		assertTrue(explanation.contains("bearable demand explanation")); //$NON-NLS-1$
		assertTrue(explanation.contains("fail")); //$NON-NLS-1$
	}

	private Diagnostic diagnostic() {
		return new BaseDiagnostic(//
				Arrays.asList(//
						new Trouble(//
								new TroubleCode.Of(777, "test-severe-demand"), //$NON-NLS-1$
								"severe demand explanation", //$NON-NLS-1$
								new Exception()) //
				), Arrays.asList(//
						new Trouble(//
								new TroubleCode.Of(555, "test-bearable-demand"), //$NON-NLS-1$
								"bearable demand explanation") //$NON-NLS-1$
				)//
		);
	}
}
