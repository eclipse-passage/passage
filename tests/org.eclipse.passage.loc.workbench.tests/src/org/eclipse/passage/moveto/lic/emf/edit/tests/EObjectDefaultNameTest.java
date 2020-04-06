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
import org.eclipse.passage.moveto.lic.emf.edit.EClassResources;
import org.eclipse.passage.moveto.lic.emf.edit.EObjectDefaultName;
import org.junit.Test;

public class EObjectDefaultNameTest {

	@Test(expected = NullPointerException.class)
	public void nullEClass() {
		new EObjectDefaultName((EClass) null);
	}

	@Test(expected = NullPointerException.class)
	public void nullEClassResources() {
		new EObjectDefaultName((EClassResources) null);
	}

	@Test
	public void positive() {
		EClassResources resources = new EClassResources(EcorePackage.eINSTANCE.getEReference());
		resources.adapterFactory().addAdapterFactory(new EcoreItemProviderAdapterFactory());
		assertEquals("New EReference", new EObjectDefaultName(resources).get()); //$NON-NLS-1$
	}

}
