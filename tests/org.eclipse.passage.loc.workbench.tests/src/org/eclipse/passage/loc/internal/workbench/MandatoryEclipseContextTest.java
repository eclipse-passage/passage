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
package org.eclipse.passage.loc.internal.workbench;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.internal.contexts.EclipseContext;
import org.junit.Test;

@SuppressWarnings("restriction")
public class MandatoryEclipseContextTest {

	@Test(expected = NullPointerException.class)
	public void nullContext() {
		new MandatoryEclipseContext(null);
	}

	@Test(expected = NoSuchElementException.class)
	public void failedResolution() {
		IEclipseContext context = new EclipseContext(null);
		new MandatoryEclipseContext(context).get(getClass());
	}

	@Test
	public void positive() {
		IEclipseContext context = new EclipseContext(null);
		MandatoryEclipseContext resolution = new MandatoryEclipseContext(context);
		assertEquals(context, resolution.get());
		String instance = new String(getClass().getName());
		context.set(String.class, instance);
		assertEquals(instance, resolution.get(String.class));
	}

}
