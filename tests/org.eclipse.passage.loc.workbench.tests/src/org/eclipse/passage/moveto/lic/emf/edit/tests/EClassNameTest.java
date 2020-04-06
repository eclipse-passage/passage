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
package org.eclipse.passage.moveto.lic.emf.edit.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.passage.moveto.lic.emf.edit.EClassName;
import org.eclipse.passage.moveto.lic.emf.edit.EClassResources;
import org.junit.Test;

public class EClassNameTest {

	@Test(expected = NullPointerException.class)
	public void nullEClass() {
		new EClassName((EClass) null);
	}

	@Test(expected = NullPointerException.class)
	public void nullEClassResources() {
		new EClassName((EClassResources) null);
	}

	@Test
	public void positive() {
		EClassResources resources = new EClassResources(EcorePackage.eINSTANCE.getEReference());
		resources.adapterFactory().addAdapterFactory(new EcoreItemProviderAdapterFactory());
		assertEquals("EReference", new EClassName(resources).get()); //$NON-NLS-1$
	}

}
