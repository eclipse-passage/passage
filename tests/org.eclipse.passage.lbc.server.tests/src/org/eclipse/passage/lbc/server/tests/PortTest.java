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
package org.eclipse.passage.lbc.server.tests;

import static org.junit.Assert.assertEquals;

import org.eclipse.passage.lbc.server.jetty.Port;
import org.junit.Test;

public class PortTest {

	@Test
	public void positive() {
		assertEquals(new Port.Custom(8080).get(), new Port.OfArgument("8080").get().get()); //$NON-NLS-1$
	}

	@Test
	public void negative() {
		assertEquals(new Port.Default().get(), new Port.OfArgument("not a port").get().get()); //$NON-NLS-1$
	}

}
