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
package org.eclipse.passage.lic.base.conditions;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.conditions.MatchingRule;

/**
 * @since 2.1
 */
public final class MatchingRuleForIdentifier implements Supplier<MatchingRule> {
	private final String identifier;

	public MatchingRuleForIdentifier(String identifier) {
		Objects.requireNonNull(identifier, "MatchingRuleForIdentifier::identifier"); //$NON-NLS-1$
		this.identifier = identifier;
	}

	public MatchingRuleForIdentifier(Optional<String> identifier) {
		Objects.requireNonNull(identifier, "MatchingRuleForIdentifier::identifier"); //$NON-NLS-1$
		this.identifier = identifier.isPresent() ? identifier.get() : new MatchingRuleDefault().identifier();
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
