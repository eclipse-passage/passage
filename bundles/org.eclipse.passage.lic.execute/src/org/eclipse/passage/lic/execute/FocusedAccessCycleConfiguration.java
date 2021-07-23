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
package org.eclipse.passage.lic.execute;

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.osgi.framework.FrameworkUtil;

abstract class FocusedAccessCycleConfiguration extends BaseAccessCycleConfiguration {

	protected LicensingDirection delegate; // lateinit, practically final and not null

	FocusedAccessCycleConfiguration(Supplier<LicensedProduct> product) {
		super(product, () -> FrameworkUtil.getBundle(FocusedAccessCycleConfiguration.class));
	}

	@Override
	public MinedConditionsRegistry conditionMiners() {
		return delegate.conditionMiners();
	}

	@Override
	public LicenseAcquisitionServicesRegistry acquirers() {
		return delegate.acquirers();
	}

	@Override
	public HashesRegistry hashes() {
		return delegate.hashes();
	}

	/**
	 * Focuses Access Cycle Configuration on local license mining
	 */
	static final class Personal extends FocusedAccessCycleConfiguration {

		Personal(Supplier<LicensedProduct> product) {
			super(product);
			this.delegate = new PersonalLicensing(super::miningEquipment);
		}

	}

	/**
	 * Focuses Access Cycle Configuration on remote license mining
	 */
	static final class Floating extends FocusedAccessCycleConfiguration {

		Floating(Supplier<LicensedProduct> product) {
			super(product);
			this.delegate = new FloatingLicensing(super.keyKeepers(), super.codecs(), super.transports());
		}

	}

	/**
	 * Adapts Access Cycle Configuration to both local and remote license mining
	 */
	static final class Wide extends FocusedAccessCycleConfiguration {

		Wide(Supplier<LicensedProduct> product) {
			super(product);
			this.delegate = new LicensingDirection.Joint(//
					new PersonalLicensing(super::miningEquipment), //
					new FloatingLicensing(super.keyKeepers(), super.codecs(), super.transports()));
		}

	}

}
