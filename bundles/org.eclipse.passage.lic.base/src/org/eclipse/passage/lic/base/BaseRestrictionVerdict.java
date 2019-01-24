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

import org.eclipse.passage.lic.runtime.ConfigurationRequirement;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;

public class BaseRestrictionVerdict implements RestrictionVerdict {
	
	private final ConfigurationRequirement configurationRequirement;
	private final String restrictionPolicy;

	BaseRestrictionVerdict(ConfigurationRequirement configurationRequirement, String restrictionPolicy) {
		this.configurationRequirement = configurationRequirement;
		this.restrictionPolicy = restrictionPolicy;
	}

	@Override
	public ConfigurationRequirement getConfigurationRequirement() {
		return configurationRequirement;
	}

	@Override
	public String getRestrictionLevel() {
		return restrictionPolicy;
	}

}
