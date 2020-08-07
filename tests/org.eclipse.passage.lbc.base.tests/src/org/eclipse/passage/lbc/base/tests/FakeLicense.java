package org.eclipse.passage.lbc.base.tests;

import java.util.Collection;
import java.util.Objects;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;

public class FakeLicense implements ConditionPack {

	private final String origin;
	private final Collection<Condition> conditions;

	public FakeLicense(String origin, Collection<Condition> conditions) {
		this.origin = origin;
		this.conditions = conditions;
	}

	@Override
	public String origin() {
		return origin;
	}

	@Override
	public Collection<Condition> conditions() {
		return conditions;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof FakeLicense)) {
			return false;
		}
		FakeLicense license = (FakeLicense) obj;
		return license.origin().equals(origin());
	}

	@Override
	public int hashCode() {
		return Objects.hash(origin);
	}

}
