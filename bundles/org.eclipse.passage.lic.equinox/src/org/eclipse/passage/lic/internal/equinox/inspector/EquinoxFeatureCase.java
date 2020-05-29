/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.equinox.inspector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.api.inspector.FeatureCase;
import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;

public class EquinoxFeatureCase implements FeatureCase {

	private final EquinoxFeatureInspector inspector;
	private final List<LicensingRequirement> requirements;
	private final List<RestrictionVerdict> verdicts;
	private final Set<Class<?>> query;
	private final Map<String, Integer> failures;

	private final Iterable<String> featureIdentifiers;

	private final class NoRestriction implements RestrictionVerdict {

		private final LicensingRequirement requirement;
		private final LicensingConfiguration configuration;

		private NoRestriction(LicensingRequirement requirement, LicensingConfiguration configuration) {
			this.requirement = requirement;
			this.configuration = configuration;
		}

		@Override
		public String getRestrictionLevel() {
			return ""; //$NON-NLS-1$
		}

		@Override
		public int getRestrictionCode() {
			return 0;
		}

		@Override
		public LicensingRequirement getLicensingRequirement() {
			return requirement;
		}

		@Override
		public LicensingConfiguration getLicensingConfiguration() {
			return this.configuration;
		}
	}

	private final class WrappedVerdict implements RestrictionVerdict {
		private final RestrictionVerdict verdict;
		private final Integer code;

		private WrappedVerdict(RestrictionVerdict verdict, Integer code) {
			this.verdict = verdict;
			this.code = code;
		}

		@Override
		public String getRestrictionLevel() {
			return verdict.getRestrictionLevel();
		}

		@Override
		public int getRestrictionCode() {
			return code.intValue();
		}

		@Override
		public LicensingRequirement getLicensingRequirement() {
			return verdict.getLicensingRequirement();
		}

		@Override
		public LicensingConfiguration getLicensingConfiguration() {
			return verdict.getLicensingConfiguration();
		}
	}

	EquinoxFeatureCase(EquinoxFeatureInspector inspector, Iterable<String> features) {
		this.inspector = inspector;
		this.featureIdentifiers = features;
		requirements = new ArrayList<>();
		verdicts = new ArrayList<>();
		failures = new HashMap<>();
		query = new HashSet<Class<?>>();
		query.add(LicensingRequirement.class);
		query.add(RestrictionVerdict.class);
	}

	@Override
	public Iterable<String> getFeatureIdentifiers() {
		return featureIdentifiers;
	}

	@Override
	public Iterable<LicensingRequirement> getRequirements() {
		if (query.contains(LicensingRequirement.class)) {
			inspector.getRequirements(featureIdentifiers).forEach(requirements::add);
			query.remove(LicensingRequirement.class);
		}
		return requirements;
	}

	@Override
	public Iterable<RestrictionVerdict> getRestrictions() {
		if (query.contains(RestrictionVerdict.class)) {
			inspector.getRestrictions(featureIdentifiers).forEach(verdicts::add);
			query.remove(RestrictionVerdict.class);
		}
		return verdicts;
	}

	@Override
	public void close() {
		inspector.close(this);
	}

	public void reset() {
		requirements.clear();
		verdicts.clear();
		failures.clear();
		query.clear();
		query.add(LicensingRequirement.class);
		query.add(RestrictionVerdict.class);
	}

	void conditionNotValid(LicensingCondition condition, int code) {
		failures.put(condition.getFeatureIdentifier(), code);
	}

	public List<RestrictionVerdict> status() {
		Map<LicensingRequirement, RestrictionVerdict> base = StreamSupport
				.stream(getRestrictions().spliterator(), false)//
				.collect(Collectors.toMap(v -> v.getLicensingRequirement(), v -> v));
		return StreamSupport.stream(getRequirements().spliterator(), false)//
				.map(r -> wrap(base, r))//
				.collect(Collectors.toList());
	}

	private RestrictionVerdict wrap(Map<LicensingRequirement, RestrictionVerdict> base,
			LicensingRequirement requirement) {
		RestrictionVerdict verdict = base.get(requirement);
		if (verdict == null) {
			return new NoRestriction(requirement, inspector.getLicensingConfiguration());
		} else {
			Integer code = failures.get(requirement.getFeatureIdentifier());
			if (code == null) {
				return verdict;
			} else {
				return new WrappedVerdict(verdict, code);
			}
		}
	}
}
