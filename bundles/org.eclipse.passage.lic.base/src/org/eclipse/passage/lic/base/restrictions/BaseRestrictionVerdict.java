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

import org.eclipse.passage.lic.runtime.LicensingRequirement;
import org.eclipse.passage.lic.runtime.RestrictionVerdict;

public class BaseRestrictionVerdict implements RestrictionVerdict {
	
	private final LicensingRequirement licensingRequirement;
	private final String restrictionPolicy;
	private final int restrictionCode;

	BaseRestrictionVerdict(LicensingRequirement configurationRequirement, String restrictionPolicy, int restrictionCode) {
		this.licensingRequirement = configurationRequirement;
		this.restrictionPolicy = restrictionPolicy;
		this.restrictionCode = restrictionCode;
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
