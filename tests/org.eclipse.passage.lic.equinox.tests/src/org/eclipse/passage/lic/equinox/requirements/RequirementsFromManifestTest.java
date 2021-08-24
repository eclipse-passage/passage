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
package org.eclipse.passage.lic.equinox.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.agreements.ResolvedAgreement;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.base.diagnostic.NoErrors;
import org.eclipse.passage.lic.base.diagnostic.NoSevereErrors;
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
		assertTrue(new NoSevereErrors().test(response.diagnostic()));
		assertTrue(response.data().isPresent());
		assertEquals(3, response.data().get().size());
		assertE(response.data().get());
	}

	@Test(expected = NullPointerException.class)
	public void sourceIsMandatory() {
		new RequirementsFromManifest(null);
	}

	private void assertE(Collection<Requirement> requirements) {
		Optional<Requirement> e = requirements.stream()//
				.filter(r -> "E".equals(r.feature().identifier())) //$NON-NLS-1$
				.findAny();//
		assertTrue(e.isPresent());
		assertEquals(2, e.get().agreements().size());
		List<String> paths = e.get().agreements().stream()//
				.map(ResolvedAgreement::path)//
				.collect(Collectors.toList());
		assertTrue(paths.contains("Honor Euler.txt")); //$NON-NLS-1$
		assertTrue(paths.contains("comp_lics/EULERS IDENTITY")); //$NON-NLS-1$
	}

}
