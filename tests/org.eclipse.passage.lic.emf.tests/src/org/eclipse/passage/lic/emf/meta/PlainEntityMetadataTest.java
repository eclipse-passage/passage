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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.junit.Test;

public final class PlainEntityMetadataTest {

	private final EClass type = EcoreFactory.eINSTANCE.createEClass();
	private final EStructuralFeature id = EcoreFactory.eINSTANCE.createEAttribute();
	private final EStructuralFeature name = EcoreFactory.eINSTANCE.createEReference();

	@Test(expected = NullPointerException.class)
	public void nullType() {
		new PlainEntityMetadata(null, id, name);
	}

	@Test(expected = NullPointerException.class)
	public void nullId() {
		new PlainEntityMetadata(type, null, name);
	}

	@Test(expected = NullPointerException.class)
	public void nullName() {
		new PlainEntityMetadata(type, id, null);
	}

	@Test
	public void positive() {
		PlainEntityMetadata metadata = new PlainEntityMetadata(type, id, name);
		assertEquals(type, metadata.eClass());
		assertEquals(id, metadata.identification());
		assertEquals(name, metadata.naming());
	}
}
