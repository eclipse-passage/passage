package org.eclipse.passage.seal.internal.demo;

import java.util.Arrays;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirementsRegistry;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.internal.equinox.requirements.BundleRequirements;
import org.eclipse.passage.lic.internal.equinox.requirements.ComponentRequirements;

@SuppressWarnings("restriction")
final class DemoFramework implements Framework {
	
	private final Registry<StringServiceId, ResolvedRequirements> requirements;
	static final Framework demo = new DemoFramework();

	private DemoFramework() {
		requirements = //
				new ReadOnlyRegistry<StringServiceId, ResolvedRequirements>(Arrays.asList(//
						new BundleRequirements(), //
						new ComponentRequirements()) //
				);
	}

	@Override
	public ResolvedRequirementsRegistry requirementsRegistry() {
		return () -> requirements;
	}

}
