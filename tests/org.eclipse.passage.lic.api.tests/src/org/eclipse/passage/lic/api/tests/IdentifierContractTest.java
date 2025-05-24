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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.GrantIdentifier;
import org.eclipse.passage.lic.api.UserIdentifier;
import org.junit.Test;

public abstract class IdentifierContractTest<T> {

	@Test(expected = NullPointerException.class)
	public final void doesNotTolerateNullInput() {
		identifierForInput(null);
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
