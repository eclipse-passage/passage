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
package org.eclipse.passage.lic.internal.equinox.io;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.eclipse.passage.lic.equinox.ProductContacts;
import org.junit.Test;

public class ProductContactsTest {

	@Test
	public void hasContacts() {
		String contacts = new ProductContacts().get();
		assertNotNull(contacts);
		assertNotEquals("", contacts); //$NON-NLS-1$
	}

}
