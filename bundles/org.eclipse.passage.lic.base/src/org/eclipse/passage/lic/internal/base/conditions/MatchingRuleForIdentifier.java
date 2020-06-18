package org.eclipse.passage.lic.internal.base.conditions;

import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.conditions.MatchingRule;

@SuppressWarnings("restriction")
public final class MatchingRuleForIdentifier implements Supplier<MatchingRule> {
	private final String identifier;

	public MatchingRuleForIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public MatchingRule get() {
		switch (identifier.toLowerCase()) {
		case "compatible": { //$NON-NLS-1$
			return new MatchingRuleCompatible();
		}
		case "equivalent": { //$NON-NLS-1$
			return new MatchingRuleEquivalent();
		}
		case "greaterorequal": { //$NON-NLS-1$
			return new MatchingRuleGreaterOrEqual();
		}
		case "perfect": { //$NON-NLS-1$
			return new MatchingRulePerfect();
		}
		default: {
			return new MatchingRuleDefault();
		}
		}
	}

}
