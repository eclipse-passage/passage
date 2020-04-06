package org.eclipse.passage.lic.internal.equinox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.Optional;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.Service;
import org.eclipse.passage.lic.internal.api.registry.ServiceId;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirementsRegistry;
import org.eclipse.passage.lic.internal.equinox.requirements.BundleRequirements;
import org.eclipse.passage.lic.internal.equinox.requirements.ComponentRequirements;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class EquinoxFrameworkSupplierTest {

	@Test
	public void accessible() {
		assertTrue(new FrameworkSupplier().get().isPresent());
	}

	@Test
	public void providesRequrementsRegistry() {
		Optional<ResolvedRequirementsRegistry> registry = //
				Optional.ofNullable(framework().requirementsRegistry());
		assertTrue(registry.isPresent());
	}

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

	private Framework framework() {
		Optional<Framework> framework = new FrameworkSupplier().get();
		assumeTrue(framework.isPresent());
		return framework.get();
	}

	private Registry<StringServiceId, ResolvedRequirements> registry() {
		Optional<ResolvedRequirementsRegistry> registry = //
				Optional.ofNullable(framework().requirementsRegistry());
		assumeTrue(registry.isPresent());
		return registry.get().get();
	}

}
