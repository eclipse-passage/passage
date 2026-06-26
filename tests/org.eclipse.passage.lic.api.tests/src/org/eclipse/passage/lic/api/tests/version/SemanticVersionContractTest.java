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
package org.eclipse.passage.lic.api.tests.version;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.eclipse.passage.lic.api.version.SemanticVersion;
import org.junit.jupiter.api.Test;

public abstract class SemanticVersionContractTest {

	@Test
	public void notExistingQualifierRetrievalMustFail() {
		assertThrows(RuntimeException.class, () -> mustFail());
	}

	private void mustFail() {
		SemanticVersion version = withoutQualifier();
		assumeFalse(version.hasQualifier());
		version.qualifier();
	}

	@Test
	public void existingQualifierMustPresent() {
		SemanticVersion version = withQualifier();
		assumeTrue(version.hasQualifier());
		assertNotNull(version.qualifier());
	}

	protected abstract SemanticVersion withoutQualifier();

	protected abstract SemanticVersion withQualifier();
}
