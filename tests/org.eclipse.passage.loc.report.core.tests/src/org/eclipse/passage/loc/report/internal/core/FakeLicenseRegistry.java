/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.loc.report.internal.core;

import java.util.Collection;
import java.util.List;

import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.loc.internal.licenses.LicenseRegistry;

public final class FakeLicenseRegistry implements LicenseRegistry {

	private final List<LicensePlanDescriptor> plans;

	public FakeLicenseRegistry(List<LicensePlanDescriptor> plans) {
		this.plans = plans;
	}

	@Override
	public Collection<LicensePlanDescriptor> getLicensePlans() {
		return plans;
	}

	@Override
	public LicensePlanDescriptor getLicensePlan(String licensePlanId) {
		return plans.stream()//
				.filter(plan -> plan.getIdentifier().equals(licensePlanId)) //
				.findFirst() //
				.get();
	}

}
