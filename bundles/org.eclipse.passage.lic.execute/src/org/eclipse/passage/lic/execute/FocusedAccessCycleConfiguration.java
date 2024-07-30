/*******************************************************************************
 * Copyright (c) 2020, 2024 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API, implementation, support
 *******************************************************************************/
package org.eclipse.passage.lic.execute;

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.api.conditions.mining.MinedConditionsRegistry;
import org.osgi.framework.Bundle;

public abstract class FocusedAccessCycleConfiguration extends BaseAccessCycleConfiguration {

	protected LicensingDirection delegate; // lateinit, practically final and not null

	FocusedAccessCycleConfiguration(Supplier<LicensedProduct> product, Supplier<Bundle> bundle) {
		super(product, bundle);
	}

	@Override
	public MinedConditionsRegistry conditionMiners() {
		return delegate.conditionMiners();
	}

	@Override
	public LicenseAcquisitionServicesRegistry acquirers() {
		return delegate.acquirers();
	}

	/**
	 * Focuses Access Cycle Configuration on local license mining
	 */
	public static final class Personal extends FocusedAccessCycleConfiguration {

		public Personal(Supplier<LicensedProduct> product, Supplier<Bundle> bundle) {
			super(product, bundle);
			this.delegate = new PersonalLicensing(super::miningEquipment);
		}

	}

}
