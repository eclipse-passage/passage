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
package org.eclipse.passage.lic.base.restrictions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.eclipse.passage.lic.base.LicensingVersions;
import org.eclipse.passage.lic.runtime.LicensingRequirement;
import org.eclipse.passage.lic.runtime.FeaturePermission;
import org.eclipse.passage.lic.runtime.LicensingCondition;
import org.eclipse.passage.lic.runtime.PermissionExaminer;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;
import org.eclipse.passage.lic.runtime.events.ConditionEvents;

public abstract class BasePermissionExaminer implements PermissionExaminer {

	@Override
	public Iterable<RestrictionVerdict> examine(Iterable<LicensingRequirement> requirements,
			Iterable<FeaturePermission> permissions) {
		Map<String, List<LicensingRequirement>> required = new HashMap<>();
		for (LicensingRequirement requirement : requirements) {
			String featureId = requirement.getFeatureIdentifier();
			List<LicensingRequirement> list = required.computeIfAbsent(featureId, key -> new ArrayList<>());
			list.add(requirement);
		}
		
		List<RestrictionVerdict> verdicts = new ArrayList<>();
		
		Set<String> features = required.keySet();
		List<LicensingCondition> leased = new ArrayList<>();
		for (String featureId : features) {
			List<LicensingRequirement> requirementList = required.get(featureId);
			List<RestrictionVerdict> examined = examineFeatures(requirementList, permissions, leased);
			verdicts.addAll(examined);
		}
		if (!leased.isEmpty()) {
			postEvent(ConditionEvents.CONDITIONS_LEASED, Collections.unmodifiableList(leased));
		}
		return Collections.unmodifiableList(verdicts);
	}
	
	protected List<RestrictionVerdict> examineFeatures(List<LicensingRequirement> requirements, Iterable<FeaturePermission> permissions, List<LicensingCondition> conditions) {
		List<LicensingRequirement> unsatisfied = new ArrayList<>(requirements);
		for (FeaturePermission permission : permissions) {
			List<LicensingRequirement> covered = new ArrayList<>();
			for (LicensingRequirement requirement : unsatisfied) {
				if (isCovered(requirement, permission)) {
					covered.add(requirement);
					conditions.add(permission.getLicensingCondition());
				}
			}
			unsatisfied.removeAll(covered);
		}
		
		List<RestrictionVerdict> verdicts = new ArrayList<>();
		for (LicensingRequirement requirement : unsatisfied) {
			verdicts.add(createVerdict(requirement, RestrictionVerdicts.CODE_NOT_AUTHORIZED));
		}
		return Collections.unmodifiableList(verdicts);
	}

	protected RestrictionVerdict createVerdict(LicensingRequirement requirement, int code) {
		return RestrictionVerdicts.create(requirement, code);
	}
	
	protected boolean isCovered(LicensingRequirement requirement, FeaturePermission permission) {
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
