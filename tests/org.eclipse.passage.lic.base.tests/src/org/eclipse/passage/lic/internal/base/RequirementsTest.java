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
package org.eclipse.passage.lic.internal.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.eclipse.passage.lic.base.tests.requirements.Unsatisfiable;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class RequirementsTest {

	@Test
	public void noResolversMeansNoAccess() {
		Set<Requirement> requirements = new Requirements(//
				new SabotagedFramework().requirementsRegistry().get(), //
				"feature0" //$NON-NLS-1$
		).get();
		assertEquals(1, requirements.size());
		assertTrue(new Unsatisfiable().test(requirements.iterator().next()));
	}

}
