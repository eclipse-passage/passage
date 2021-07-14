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
package org.eclipse.passage.lic.api.tests.version;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeFalse;
import static org.junit.Assume.assumeTrue;

import org.eclipse.passage.lic.api.version.SemanticVersion;
import org.junit.Test;

public abstract class SemanticVersionContractTest {

	@Test(expected = RuntimeException.class)
	public void notExistingQualifierRetrievalMustFail() {
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
