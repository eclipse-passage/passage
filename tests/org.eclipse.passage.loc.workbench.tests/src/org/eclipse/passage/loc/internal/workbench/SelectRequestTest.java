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

import java.util.Collections;
import java.util.function.Supplier;

import org.eclipse.passage.loc.jface.dialogs.Appearance;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.junit.AfterClass;
import org.junit.Test;

public class SelectRequestTest {

	private final static Image image = new Image(Display.getDefault(), 10, 10);

	private final Class<Object> target = Object.class;
	private final String domain = new String();
	private final Supplier<Iterable<Object>> input = () -> Collections.emptyList();
	private final Appearance appearance = new Appearance(domain, image);

	@Test(expected = NullPointerException.class)
	public void testNullType() {
		new SelectRequest<Object>(null, domain, input, appearance);
	}

	@Test(expected = NullPointerException.class)
	public void testNullId() {
		new SelectRequest<Object>(target, null, input, appearance);
	}

	@Test(expected = NullPointerException.class)
	public void testNullName() {
		new SelectRequest<Object>(target, domain, null, appearance);
	}

	@Test(expected = NullPointerException.class)
	public void testNullAppearance() {
		new SelectRequest<Object>(target, domain, input, null);
	}

	@Test
	public void testPositive() {
		SelectRequest<Object> request = new SelectRequest<Object>(target, domain, input, appearance);
		assertEquals(target, request.target());
		assertEquals(domain, request.domain());
		assertEquals(input, request.input());
		assertEquals(appearance, request.appearance());
	}

	@AfterClass
	public static void afterClass() {
		image.dispose();
	}
}
