/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.licenses.convert;

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.conditions.MatchingRule;
import org.eclipse.passage.lic.api.conditions.VersionMatch;
import org.eclipse.passage.lic.base.conditions.BaseVersionMatch;
import org.eclipse.passage.lic.base.conditions.MatchingRuleDefault;
import org.eclipse.passage.lic.base.conditions.MatchingRuleForIdentifier;
import org.eclipse.passage.lic.licenses.VersionMatchDescriptor;

public final class PVersionMatch implements Supplier<VersionMatch> {

	private final VersionMatchDescriptor descriptor;

	public PVersionMatch(VersionMatchDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	@Override
	public VersionMatch get() {
		return new BaseVersionMatch(descriptor.getVersion(), rule(descriptor.getRule()));
	}

	/**
	 * It looks like default matching rule is not persisted my EMF, this we expect
	 * {@code null} here
	 */
	private MatchingRule rule(String origin) {
		if (origin == null) {
			return new MatchingRuleDefault();
		}
		return new MatchingRuleForIdentifier(origin).get();
	}

}
