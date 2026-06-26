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
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.jupiter.api.Test;

public final class PlainEntityMetadataTest {

	private final EClass type = EcoreFactory.eINSTANCE.createEClass();
	private final EStructuralFeature id = EcoreFactory.eINSTANCE.createEAttribute();
	private final EStructuralFeature name = EcoreFactory.eINSTANCE.createEReference();

	@Test
	public void nullType() {
		assertThrows(NullPointerException.class, () -> new PlainEntityMetadata(null, id, name));
	}

	@Test
	public void nullId() {
		assertThrows(NullPointerException.class, () -> new PlainEntityMetadata(type, null, name));
	}

	@Test
	public void nullName() {
		assertThrows(NullPointerException.class, () -> new PlainEntityMetadata(type, id, null));
	}

	@Test
	public void positive() {
		PlainEntityMetadata metadata = new PlainEntityMetadata(type, id, name);
		assertEquals(type, metadata.eClass());
		assertEquals(id, metadata.identification());
		assertEquals(name, metadata.naming());
	}
}
