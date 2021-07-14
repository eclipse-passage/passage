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
package org.eclipse.passage.loc.api.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.eclipse.passage.lic.api.MandatoryService;
import org.eclipse.passage.loc.internal.api.ClassSupply;
import org.eclipse.passage.loc.internal.api.ComposedClassSupply;
import org.eclipse.passage.loc.internal.api.InstanceSupply;
import org.junit.Test;

public class ComposedClassMetadataTest {

	private final InstanceSupply<?> metadata = new InstanceSupply<Object>() {

		@Override
		public Optional<Object> supply() {
			return Optional.empty();
		}
	};
	private final ClassSupply searcher = (c, r) -> Optional.of(metadata);

	@Test(expected = NullPointerException.class)
	public void nullConsider() {
		new ComposedClassSupply().consider(null);
	}

	@Test(expected = NullPointerException.class)
	public void nullForget() {
		new ComposedClassSupply().forget(null);
	}

	@Test
	public void positive() {
		MandatoryService resolution = null;
		ComposedClassSupply composed = new ComposedClassSupply();
		assertFalse(composed.find(getClass(), resolution).isPresent());
		composed.consider(searcher);
		assertEquals(metadata, composed.find(getClass(), resolution).get());
		composed.forget(searcher);
		assertFalse(composed.find(getClass(), resolution).isPresent());
	}
}
