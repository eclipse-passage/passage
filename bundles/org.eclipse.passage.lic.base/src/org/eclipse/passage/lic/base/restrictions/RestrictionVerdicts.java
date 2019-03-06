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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.passage.lic.base.requirements.ConfigurationRequirements;
import org.eclipse.passage.lic.runtime.LicensingRequirement;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;

public class RestrictionVerdicts {
	
	public static final int CODE_NOT_AUTHORIZED = 401;

	public static final int CODE_CONFIGURATION_ERROR = 500;

	private RestrictionVerdicts() {
		// block
	}


	public static BaseRestrictionVerdict createConfigurationError(String featureId, Object source) {
		LicensingRequirement requirement = ConfigurationRequirements.createConfigurationError(featureId, source);
		int code = CODE_CONFIGURATION_ERROR;
		return createError(requirement, code);
	}

	public static BaseRestrictionVerdict create(LicensingRequirement requirement, int code) {
		String policy = LICENSING_RESTRICTION_LEVEL_ERROR;
		if (requirement != null) {
			policy = requirement.getRestrictionLevel();
		}
		return new BaseRestrictionVerdict(requirement, policy, code);
	}

	public static BaseRestrictionVerdict createError(LicensingRequirement requirement, int code) {
		String policy = LICENSING_RESTRICTION_LEVEL_ERROR;
		return new BaseRestrictionVerdict(requirement, policy, code);
	}

	public static Iterable<BaseRestrictionVerdict> createConfigurationError(LicensingRequirement requirement) {
		int code = CODE_CONFIGURATION_ERROR;
		return Collections.singletonList(createError(requirement, code));
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
		RestrictionVerdict last = list.get(list.size()-1);
		return last;
	}


}
