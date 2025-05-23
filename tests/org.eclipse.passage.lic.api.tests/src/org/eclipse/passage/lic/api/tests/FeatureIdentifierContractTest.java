/*******************************************************************************
 * Copyright (c) 2025 ArSysOp
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

package org.eclipse.passage.lic.api.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.junit.Test;

public abstract class FeatureIdentifierContractTest {

	@Test(expected = NullPointerException.class)
	public void doesNotTolerateNullInput() {
		featureIdentifier(null);
	}

	@Test
	public void canBeMapKey() {
		String input = "same-input"; //$NON-NLS-1$
		FeatureIdentifier left = featureIdentifier(input);
		FeatureIdentifier right = featureIdentifier(input);
		assertEquals(left.hashCode(), right.hashCode());
		assertTrue(left.equals(right));
	}

	protected abstract FeatureIdentifier featureIdentifier(String input);

}
