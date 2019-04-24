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

import org.eclipse.passage.lic.api.requirements.LicensingRequirement;
import org.eclipse.passage.lic.api.restrictions.RestrictionVerdict;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;

class BaseRestrictionVerdict implements RestrictionVerdict {

	private final LicensingConfiguration licensingConfiguration;
	private final LicensingRequirement licensingRequirement;
	private final String restrictionPolicy;
	private final int restrictionCode;

	BaseRestrictionVerdict(LicensingConfiguration configuration, LicensingRequirement requirement, String policy,
			int code) {
		this.licensingConfiguration = configuration;
		this.licensingRequirement = requirement;
		this.restrictionPolicy = policy;
		this.restrictionCode = code;
	}

	@Override
	public LicensingConfiguration getLicensingConfiguration() {
		return licensingConfiguration;
	}

	@Override
	public LicensingRequirement getLicensingRequirement() {
		return licensingRequirement;
	}

	@Override
	public String getRestrictionLevel() {
		return restrictionPolicy;
	}

	@Override
	public int getRestrictionCode() {
		return restrictionCode;
	}

}
