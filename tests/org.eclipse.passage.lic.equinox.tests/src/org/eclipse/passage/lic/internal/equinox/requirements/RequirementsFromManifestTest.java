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
package org.eclipse.passage.lic.internal.equinox.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.base.diagnostic.NoErrors;
import org.eclipse.passage.lic.internal.base.diagnostic.NoSevereErrors;
import org.junit.Test;
import org.osgi.framework.FrameworkUtil;

public class RequirementsFromManifestTest {

	@Test
	public void empty() {
		ServiceInvocationResult<Collection<Requirement>> response = new RequirementsFromManifest(
				FrameworkUtil.getBundle(getClass())).get();
		assertTrue(new NoErrors().test(response.diagnostic()));
		assertTrue(response.data().isPresent());
		assertTrue(response.data().get().isEmpty());
	}

	@Test
	public void several() {
		ServiceInvocationResult<Collection<Requirement>> response = //
				new RequirementsFromManifest(new DataBundle().bundle()).get();
		assertFalse(new NoSevereErrors().test(response.diagnostic()));
		assertTrue(response.data().isPresent());
		assertEquals(3, response.data().get().size());
	}

	@Test(expected = NullPointerException.class)
	public void sourceIsMandatory() {
		new RequirementsFromManifest(null);
	}

}
