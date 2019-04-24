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

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_FATAL;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_WARN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.base.requirements.LicensingRequirements;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.restrictions.RestrictionVerdict;

public class RestrictionVerdicts {

	public static final int CODE_NOT_AUTHORIZED = 401;

	public static final int CODE_CONFIGURATION_ERROR = 500;

	private RestrictionVerdicts() {
		// block
	}

	public static BaseRestrictionVerdict createConfigurationError(LicensingConfiguration configuration,
			String featureId) {
		LicensingRequirement requirement = LicensingRequirements.createConfigurationError(featureId, configuration);
		int code = CODE_CONFIGURATION_ERROR;
		return createError(configuration, requirement, code);
	}

	public static BaseRestrictionVerdict create(LicensingConfiguration configuration, LicensingRequirement requirement,
			int code) {
		String policy = LICENSING_RESTRICTION_LEVEL_ERROR;
		if (requirement != null) {
			policy = requirement.getRestrictionLevel();
		}
		return new BaseRestrictionVerdict(configuration, requirement, policy, code);
	}

	public static BaseRestrictionVerdict createError(LicensingConfiguration configuration,
			LicensingRequirement requirement, int code) {
		String policy = LICENSING_RESTRICTION_LEVEL_ERROR;
		return new BaseRestrictionVerdict(configuration, requirement, policy, code);
	}

	public static Iterable<BaseRestrictionVerdict> createConfigurationError(LicensingConfiguration configuration,
			LicensingRequirement requirement) {
		int code = CODE_CONFIGURATION_ERROR;
		return Collections.singletonList(createError(configuration, requirement, code));
	}

	public static RestrictionVerdict resolveLastVerdict(Iterable<RestrictionVerdict> verdicts) {
		if (verdicts == null) {
			return null;
		}
		List<RestrictionVerdict> list = new ArrayList<>();
		verdicts.forEach(list::add);
		if (list.isEmpty()) {
			return null;
		}
		Collections.sort(list, new RestrictionVerdictComparator());
		RestrictionVerdict last = list.get(list.size() - 1);
		return last;
	}

	public static RestrictionVerdict resolveLastVerdict(Iterable<RestrictionVerdict> verdicts, String featureId) {
		if (featureId == null) {
			return resolveLastVerdict(verdicts);
		}
		if (verdicts == null) {
			return null;
		}
		List<RestrictionVerdict> candidates = new ArrayList<>();
		for (RestrictionVerdict verdict : verdicts) {
			LicensingRequirement requirement = verdict.getLicensingRequirement();
			if (requirement == null) {
				continue;
			}
			if (Objects.equals(featureId, requirement.getFeatureIdentifier())) {
				candidates.add(verdict);
			}
		}
		return resolveLastVerdict(candidates);
	}

	public static boolean shouldPauseExecution(RestrictionVerdict verdict) {
		if (verdict == null) {
			return false;
		}
		String level = verdict.getRestrictionLevel();
		return LICENSING_RESTRICTION_LEVEL_WARN.equals(level) || LICENSING_RESTRICTION_LEVEL_ERROR.equals(level)
				|| LICENSING_RESTRICTION_LEVEL_FATAL.equals(level);
	}

	public static boolean shouldInterruptExecution(RestrictionVerdict verdict) {
		if (verdict == null) {
			return false;
		}
		String level = verdict.getRestrictionLevel();
		return LICENSING_RESTRICTION_LEVEL_ERROR.equals(level) || LICENSING_RESTRICTION_LEVEL_FATAL.equals(level);
	}

}
