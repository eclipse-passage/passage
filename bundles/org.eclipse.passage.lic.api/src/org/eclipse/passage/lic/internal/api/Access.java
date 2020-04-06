package org.eclipse.passage.lic.internal.api;

import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;

public final class Access {

	private final Framework framework;

	public Access(Framework framework) {
		this.framework = framework;
	}

	public boolean canUse(String feature) {
		Set<Requirement> requirements = framework.requirementsRegistry().get().services().stream() //
				.map(ResolvedRequirements.Smart::new) //
				.flatMap(service -> service.forFeature(feature).stream()) //
				.collect(Collectors.toSet());
		if (requirements.isEmpty()) {
			return true;
		}
		// FIXME: EP: implement further
		return false;
	}

}
