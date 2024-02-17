/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support
 *******************************************************************************/
package org.eclipse.passage.lic.internal.base.tests.restrictions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.restrictions.ExaminationExplained;
import org.junit.Test;

public final class ExaminationExplainedTest {

	@Test(expected = NullPointerException.class)
	public void doesNotExplainNull() {
		new ExaminationExplained(null);
	}

	@Test
	public void explainsSensibleCeritificate() {
		String explanation = new ExaminationExplained(certificate()).get().trim();
		assertFalse(explanation.isEmpty());
		assertTrue(explanation.contains("woha!")); //$NON-NLS-1$
		assertTrue(explanation.contains("christmas")); //$NON-NLS-1$
		assertTrue(explanation.contains("just do not like you")); //$NON-NLS-1$
		assertTrue(explanation.contains("failed-feature")); //$NON-NLS-1$
	}

	private ExaminationCertificate certificate() {
		return new TestCertificates().verbose();
	}

}
