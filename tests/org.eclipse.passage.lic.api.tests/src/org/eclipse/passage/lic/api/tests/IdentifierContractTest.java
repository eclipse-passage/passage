/*******************************************************************************
 * Copyright (c) 2025, 2026 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - ongoing support
 *******************************************************************************/

package org.eclipse.passage.lic.api.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.GrantIdentifier;
import org.eclipse.passage.lic.api.UserIdentifier;
import org.junit.jupiter.api.Test;

public abstract class IdentifierContractTest<T> {

	@Test
	public final void doesNotTolerateNullInput() {
		assertThrows(NullPointerException.class, () -> identifierForInput(null));
	}

	@Test
	public final void doesNotSupplyNull() {
		assertNotNull(id(theMostInvalidIdentifier()));
	}

	@Test
	public final void canBeMapKey() {
		String input = "same-input"; //$NON-NLS-1$
		T left = identifierForInput(input);
		T right = identifierForInput(input);
		assertEquals(left.hashCode(), right.hashCode());
		assertTrue(left.equals(right));
	}

	protected abstract String id(T identifier);

	protected abstract T identifierForInput(String input);

	protected abstract T theMostInvalidIdentifier();

	public static abstract class Feature extends IdentifierContractTest<FeatureIdentifier> {

		@Override
		protected final String id(FeatureIdentifier identifier) {
			return identifier.identifier();
		}

	}

	public static abstract class User extends IdentifierContractTest<UserIdentifier> {

		@Override
		protected final String id(UserIdentifier identifier) {
			return identifier.identifier();
		}

	}

	public static abstract class Grant extends IdentifierContractTest<GrantIdentifier> {

		@Override
		protected final String id(GrantIdentifier identifier) {
			return identifier.identifier();
		}

	}

}
