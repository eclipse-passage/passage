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
package org.eclipse.passage.loc.workbench.emfforms.renderers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.conditions.MatchingRule;
import org.eclipse.passage.lic.api.version.SemanticVersion;
import org.eclipse.passage.lic.base.version.BaseSemanticVersion;

final class MatchingRuleExplained {

	private final List<Sample> samples;

	public MatchingRuleExplained(MatchingRule rule, SemanticVersion base) {
		this.samples = scale(base).stream()//
				.map(required -> new Sample(rule, required, base))//
				.collect(Collectors.toList());
	}

	private List<SemanticVersion> scale(SemanticVersion base) {
		List<SemanticVersion> scale = new ArrayList<>();
		if (base.major() > 0) {
			scale.add(new BaseSemanticVersion(base.major() - 1, base.minor(), base.service()));
		}
		if (base.minor() > 0) {
			scale.add(new BaseSemanticVersion(base.major(), base.minor() - 1, base.service()));
		}
		if (base.service() > 0) {
			scale.add(new BaseSemanticVersion(base.major(), base.minor(), base.service() - 1));
		}
		scale.add(base);
		scale.add(new BaseSemanticVersion(base.major(), base.minor(), base.service() + 1));
		scale.add(new BaseSemanticVersion(base.major(), base.minor() + 1, base.service()));
		scale.add(new BaseSemanticVersion(base.major() + 1, base.minor(), base.service()));
		return scale;
	}

	List<Sample> samples() {
		return samples;
	}

	static final class Sample {

		private final boolean ok;
		private final SemanticVersion version;

		private Sample(MatchingRule rule, SemanticVersion version, SemanticVersion base) {
			this.ok = rule.match(version.toString(), base.toString());
			this.version = version;
		}

		boolean ok() {
			return ok;
		}

		String text() {
			return version.toString();
		}
	}

}
