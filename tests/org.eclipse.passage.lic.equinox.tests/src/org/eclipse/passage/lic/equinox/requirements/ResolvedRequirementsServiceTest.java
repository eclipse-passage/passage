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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.tests.ResolvedRequirementsContractTest;
import org.eclipse.passage.lic.base.requirements.RequirementsFeatureFilter;
import org.junit.Test;
import org.osgi.framework.InvalidSyntaxException;

@SuppressWarnings("restriction")
abstract class ResolvedRequirementsServiceTest extends ResolvedRequirementsContractTest {

	@Test
	public void allRequirements() throws InvalidSyntaxException {
		ServiceInvocationResult<Collection<Requirement>> requirements = service().all();
		assertTrue(invalidMorsels() <= requirements.diagnostic().severe().size());
		assertTrue(requirements.data().isPresent());
		assertTrue(requirements.data().get().stream() //
				.collect(Collectors.toSet())//
				.containsAll(expectations()));
	}

	@Test
	public void requirementsForFeature() throws InvalidSyntaxException {
		Requirement single = single();
		ServiceInvocationResult<Collection<Requirement>> filtered = new RequirementsFeatureFilter(
				single.feature().identifier())//
						.get()//
						.apply(service().all());
		assertEquals(//
				Collections.singleton(single), //
				new HashSet<Requirement>(filtered.data().get()));
	}

	protected abstract Class<?> serviceClass();

	protected abstract Set<Requirement> expectations();

	protected abstract int invalidMorsels();

}
