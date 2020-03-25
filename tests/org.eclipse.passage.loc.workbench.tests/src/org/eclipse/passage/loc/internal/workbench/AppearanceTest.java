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

import java.util.function.Supplier;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.passage.loc.jface.dialogs.Appearance;
import org.eclipse.swt.graphics.Image;
import org.junit.Test;

public class AppearanceTest {

	private final String title = new String();
	private final Supplier<Image> image = () -> null/* sorry, we don't have any graphical facilities in our pipelines */;
	private final LabelProvider labels = new LabelProvider();

	@Test(expected = NullPointerException.class)
	public void testNullTitle() {
		new Appearance(null, image, labels);
	}

	@Test(expected = NullPointerException.class)
	public void testNullId() {
		new Appearance(title, null, labels);
	}

	@Test(expected = NullPointerException.class)
	public void testNullLabels() {
		new Appearance(title, image, null);
	}

	@Test
	public void testPositive() {
		Appearance appearance = new Appearance(title, image, labels);
		assertEquals(title, appearance.title());
		assertEquals(image, appearance.image());
		assertEquals(labels, appearance.labelProvider());
	}

}
