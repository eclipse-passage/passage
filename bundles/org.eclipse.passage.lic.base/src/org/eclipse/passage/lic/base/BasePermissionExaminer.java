/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.eclipse.passage.lic.base.LicensingEvents.LicensingConditionEvents;
import org.eclipse.passage.lic.runtime.ConfigurationRequirement;
import org.eclipse.passage.lic.runtime.FeaturePermission;
import org.eclipse.passage.lic.runtime.LicensingCondition;
import org.eclipse.passage.lic.runtime.PermissionExaminer;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;

public abstract class BasePermissionExaminer implements PermissionExaminer {

	@Override
	public Iterable<RestrictionVerdict> examine(Iterable<ConfigurationRequirement> requirements,
			Iterable<FeaturePermission> permissions) {
		Map<String, List<ConfigurationRequirement>> required = new HashMap<>();
		for (ConfigurationRequirement requirement : requirements) {
			String featureId = requirement.getFeatureIdentifier();
			List<ConfigurationRequirement> list = required.computeIfAbsent(featureId, key -> new ArrayList<>());
			list.add(requirement);
		}
		
		List<RestrictionVerdict> verdicts = new ArrayList<>();
		
		Set<String> features = required.keySet();
		List<LicensingCondition> leased = new ArrayList<>();
		for (String featureId : features) {
			List<ConfigurationRequirement> requirementList = required.get(featureId);
			List<RestrictionVerdict> examined = examineFeatures(requirementList, permissions, leased);
			verdicts.addAll(examined);
		}
		if (!leased.isEmpty()) {
			postEvent(LicensingConditionEvents.CONDITIONS_LEASED, Collections.unmodifiableList(leased));
		}
		return Collections.unmodifiableList(verdicts);
	}
	
	protected List<RestrictionVerdict> examineFeatures(List<ConfigurationRequirement> requirements, Iterable<FeaturePermission> permissions, List<LicensingCondition> conditions) {
		List<ConfigurationRequirement> unsatisfied = new ArrayList<>(requirements);
		for (FeaturePermission permission : permissions) {
			List<ConfigurationRequirement> covered = new ArrayList<>();
			for (ConfigurationRequirement requirement : unsatisfied) {
				if (isCovered(requirement, permission)) {
					covered.add(requirement);
					conditions.add(permission.getLicensingCondition());
				}
			}
			unsatisfied.removeAll(covered);
		}
		
		List<RestrictionVerdict> verdicts = new ArrayList<>();
		for (ConfigurationRequirement requirement : unsatisfied) {
			verdicts.add(createVerdict(requirement, RestrictionVerdicts.CODE_NOT_AUTHORIZED));
		}
		return Collections.unmodifiableList(verdicts);
	}

	protected RestrictionVerdict createVerdict(ConfigurationRequirement requirement, int code) {
		return RestrictionVerdicts.create(requirement, code);
	}
	
	protected boolean isCovered(ConfigurationRequirement requirement, FeaturePermission permission) {
		LicensingCondition condition = permission.getLicensingCondition();
		if (condition == null) {
			return false;
		}
		if (!Objects.equals(requirement.getFeatureIdentifier(), condition.getFeatureIdentifier())) {
			return false;
		}
		return LicensingVersions.isMatch(requirement.getFeatureVersion(), condition.getMatchVersion(), condition.getMatchRule());
	}

	protected abstract void postEvent(String topic, Object data);

}
