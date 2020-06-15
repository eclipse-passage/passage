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
package org.eclipse.passage.lic.api.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.eclipse.passage.lic.api.tests.fakes.FakeResolvedRequirements;
import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.junit.Test;

/**
 * <p>
 * Here we check severe assumptions Passage does about instance of
 * {@linkplain Framework} interface it operates with.
 * </p>
 * <p>
 * Each sealed {@linkplain Framework} implementation must follow the contract:
 * extend the test, supply a framework instance and succeed at every test.
 * </p>
 */
@SuppressWarnings("restriction")
public abstract class FrameworkContractTest {

	@Test
	public void exists() {
		assertNotNull(framework());
		assertTrue(framework().isPresent());
	}

	@Test
	public void canResolveRequirements() {
		Registry<StringServiceId, ResolvedRequirements> registry = framework().get().requirementsRegistry().get();
		assertNotNull(registry);
		assertNotNull(registry.services());
		assertTrue(registry.services().size() > 0);
	}

	@Test
	public void prohibitsRequirementsResolutionExtension() {
		assertTrue(readOnly(framework().get().requirementsRegistry().get()));
	}

	@Test
	public void prohibitsInjectionIntoRequirementResolutionServices() {
		Registry<StringServiceId, ResolvedRequirements> registry = framework().get().requirementsRegistry().get();
		int before = registry.services().size();
		registry.services().add(new FakeResolvedRequirements());
		assertTrue(before == registry.services().size());

	}

	protected abstract Optional<Framework> framework();

	protected abstract boolean readOnly(Registry<?, ?> registry);

}
