package org.eclipse.passage.lbc.base.tests;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;

public class FakeConditionPack implements ConditionPack {

	@Override
	public String origin() {
		return "floating"; //$NON-NLS-1$
	}

	@Override
	public Collection<Condition> conditions() {
		return Collections.emptyList();
	}

	@Override
	public int hashCode() {
		return Objects.hash(origin(), conditions());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof FakeConditionPack)) {
			return false;
		}
		ConditionPack pack = (ConditionPack) obj;
		return Objects.equals(pack.origin(), origin()) && Objects.equals(pack.conditions(), conditions());
	}

}
