package org.eclipse.passage.lic.api.tests.fakes;

import org.eclipse.passage.lic.internal.api.requirements.Feature;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.RestrictionLevel;

@SuppressWarnings("restriction")
public final class FakeRequirement implements Requirement{

	private final Feature feature;
	private final RestrictionLevel level;
	
	
	public FakeRequirement(Feature feature, RestrictionLevel level) {
		this.feature = feature;
		this.level = level;
	}

	public FakeRequirement(String feature, String level) {
		this(new FakeFeature(feature), new RestrictionLevel.Of(level));
	}

	public FakeRequirement(String feature) {
		this(new FakeFeature(feature), new RestrictionLevel.Error());
	}

	public FakeRequirement() {
		this(new FakeFeature(), new RestrictionLevel.Error());
	}

	@Override
	public Feature feature() {
		return feature;
	}

	@Override
	public RestrictionLevel restrictionLevel() {
		return level;
	}

	@Override
	public Object source() {
		return "API Tests";
	}

}
