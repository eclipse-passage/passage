package org.eclipse.passage.lbc.internal.equinox.conditions;

import java.util.Collection;

import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;

public class TransferableConditionPack extends EObjectImpl implements ConditionPack {

	private final String origin;
	private final Collection<Condition> conditions;

	public TransferableConditionPack(ConditionPack conditionPack) {
		this.conditions = conditionPack.conditions();
		this.origin = conditionPack.origin();
	}

	@Override
	public String origin() {
		return origin;
	}

	@Override
	public Collection<Condition> conditions() {
		return conditions;
	}

}
