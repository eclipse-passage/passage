package org.eclipse.passage.lic.api.tests.fakes;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;

@SuppressWarnings("restriction")
public final class FakeResolvedRequirements implements ResolvedRequirements{

	private final Collection<Requirement> requirements;
	
	public FakeResolvedRequirements(Collection<Requirement> requirements) {
		this.requirements = requirements;
	}
	
	public FakeResolvedRequirements() {
		this.requirements = Collections.emptySet();
	}

	@Override
	public StringServiceId id() {
		return new StringServiceId("fake-req-res");
	}

	@Override
	public Collection<Requirement> all() {
		return requirements;
	}

}
