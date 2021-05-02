/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.emf.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.passage.lic.internal.emf.i18n.EmfMessages;
import org.junit.Test;

public class ErrorMessagesTest {

	private final ErrorMessages cut = new ErrorMessages();

	@Test
	public void fufilled() {
		assertTrue(cut.apply(EcoreFactory.eINSTANCE.createEClass()).isPresent());
	}

	@Test
	public void ok() {
		assertFalse(cut.apply(EcoreFactory.eINSTANCE.createEObject()).isPresent());
	}

	@Test
	public void invalid() {
		assertEquals(EmfMessages.LicensingEcore_input_invalid, cut.apply(null).get());
	}

}
