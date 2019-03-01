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

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_RESTRICTION_LEVEL_ERROR;

import java.util.Collections;

import org.eclipse.passage.lic.runtime.ConfigurationRequirement;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;

public class RestrictionVerdicts {
	
	private RestrictionVerdicts() {
		// block
	}


	public static BaseRestrictionVerdict createConfigurationError(String featureId, LicensingConfiguration configuration) {
		ConfigurationRequirement requirement = ConfigurationRequirements.createConfigurationError(featureId, configuration);
		return createError(requirement);
	}

	public static BaseRestrictionVerdict createError(ConfigurationRequirement requirement) {
		String policy = LICENSING_RESTRICTION_LEVEL_ERROR;
		return new BaseRestrictionVerdict(requirement, policy);
	}

	public static Iterable<BaseRestrictionVerdict> createErrorIterable(ConfigurationRequirement requirement) {
		return Collections.singletonList(createError(requirement));
	}


}
