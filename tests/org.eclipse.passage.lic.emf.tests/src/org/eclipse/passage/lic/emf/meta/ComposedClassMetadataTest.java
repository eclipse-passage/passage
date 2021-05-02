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
package org.eclipse.passage.lic.emf.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.Test;

public final class ComposedClassMetadataTest {

	private final EClass type = EcoreFactory.eINSTANCE.createEClass();
	private final EStructuralFeature id = EcoreFactory.eINSTANCE.createEAttribute();
	private final EStructuralFeature name = EcoreFactory.eINSTANCE.createEReference();
	private final EntityMetadata metadata = new PlainEntityMetadata(type, id, name);
	private final ClassMetadata searcher = c -> Optional.of(metadata);

	@Test(expected = NullPointerException.class)
	public void nullConsider() {
		new ComposedClassMetadata().consider(null);
	}

	@Test(expected = NullPointerException.class)
	public void nullForget() {
		new ComposedClassMetadata().forget(null);
	}

	@Test
	public void positive() {
		ComposedClassMetadata composed = new ComposedClassMetadata();
		assertFalse(composed.find(getClass()).isPresent());
		composed.consider(searcher);
		assertEquals(metadata, composed.find(getClass()).get());
		composed.forget(searcher);
		assertFalse(composed.find(getClass()).isPresent());
	}
}
