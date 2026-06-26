/*******************************************************************************
 * Copyright (c) 2021, 2026 ArSysOp
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
package org.eclipse.passage.lic.emf.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.junit.jupiter.api.Test;

public final class ExtractEObjectTest {

	@Test
	public void resource() {
		Resource resource = new ResourceImpl();
		assertFalse(new ExtractEObject().apply(resource).isPresent());
		EObject eo = EcoreFactory.eINSTANCE.createEObject();
		resource.getContents().add(eo);
		assertEquals(eo, new ExtractEObject().apply(resource).get());
	}

	@Test
	public void eobject() {
		EObject eo = EcoreFactory.eINSTANCE.createEObject();
		assertEquals(eo, new ExtractEObject().apply(eo).get());
	}

	@Test
	public void invalid() {
		assertFalse(new ExtractEObject().apply(null).isPresent());
		assertFalse(new ExtractEObject().apply(this).isPresent());
	}

}
