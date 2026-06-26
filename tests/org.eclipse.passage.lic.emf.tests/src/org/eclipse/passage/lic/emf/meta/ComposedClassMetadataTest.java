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
package org.eclipse.passage.lic.emf.meta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.jupiter.api.Test;

public final class ComposedClassMetadataTest {

	private final EClass type = EcoreFactory.eINSTANCE.createEClass();
	private final EStructuralFeature id = EcoreFactory.eINSTANCE.createEAttribute();
	private final EStructuralFeature name = EcoreFactory.eINSTANCE.createEReference();
	private final EntityMetadata metadata = new PlainEntityMetadata(type, id, name);
	private final ClassMetadata searcher = c -> Optional.of(metadata);

	@Test
	public void nullConsider() {
		assertThrows(NullPointerException.class, () -> new ComposedClassMetadata().consider(null));
	}

	@Test
	public void nullForget() {
		assertThrows(NullPointerException.class, () -> new ComposedClassMetadata().forget(null));
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
