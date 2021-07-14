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
package org.eclipse.passage.seal.demo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.Optional;

import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.Service;
import org.eclipse.passage.lic.api.registry.ServiceId;
import org.eclipse.passage.lic.api.registry.StringServiceId;
import org.eclipse.passage.lic.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.api.requirements.ResolvedRequirementsRegistry;
import org.eclipse.passage.lic.equinox.requirements.BundleRequirements;
import org.eclipse.passage.lic.equinox.requirements.ComponentRequirements;
import org.eclipse.passage.seal.internal.demo.DemoFrameworkSupplier;
import org.junit.Test;

public final class DemoFrameworkContentTest {

	@Test
	public void providesBothEquinoxRequirementServices() {
		Registry<StringServiceId, ResolvedRequirements> registry = registry();
		assertHasService(registry, new BundleRequirements());
		assertHasService(registry, new ComponentRequirements());
	}

	private <I extends ServiceId, S extends Service<I>> void assertHasService(//
			Registry<I, S> registry, S template) {
		assertTrue(registry.hasService(template.id()));
		assertNotNull(registry.service(template.id()));
		assertEquals(template.getClass(), registry.service(template.id()).getClass());
	}

	private Registry<StringServiceId, ResolvedRequirements> registry() {
		Optional<ResolvedRequirementsRegistry> registry = //
				Optional.ofNullable(framework().accessCycleConfiguration().requirementResolvers());
		assumeTrue(registry.isPresent());
		return registry.get().get();
	}

	private Framework framework() {
		Optional<Framework> framework = new DemoFrameworkSupplier().get();
		assumeTrue(framework.isPresent());
		return framework.get();
	}

}
