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
package org.eclipse.passage.lic.jface.tests;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.eclipse.passage.lic.internal.jface.dialogs.licensing.ProductContacts;
import org.junit.jupiter.api.Test;

public class ProductContactsTest {

	@Test
	public void hasContacts() {
		String contacts = new ProductContacts().get();
		assertNotNull(contacts);
		assertNotEquals("", contacts); //$NON-NLS-1$
	}

}
