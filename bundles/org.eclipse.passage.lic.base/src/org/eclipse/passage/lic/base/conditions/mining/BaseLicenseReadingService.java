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
package org.eclipse.passage.lic.base.conditions.mining;

import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.conditions.mining.LicenseReadingService;
import org.eclipse.passage.lic.api.conditions.mining.MiningEquipment;

/**
 * @since 2.1
 */
public final class BaseLicenseReadingService implements LicenseReadingService {

	private final LicensedProduct product;
	private final MiningEquipment equipment;

	public BaseLicenseReadingService(LicensedProduct product, MiningEquipment equipment) {
		this.product = product;
		this.equipment = equipment;
	}

	@Override
	public LicensedProduct id() {
		return product;
	}

	@Override
	public ServiceInvocationResult<Collection<ConditionPack>> read(Path license) {
		return new OnTheFlyConditions(license, equipment).all(product);
	}

	private static final class OnTheFlyConditions extends LocalConditions {

		private final Path license;

		private OnTheFlyConditions(Path license, MiningEquipment equipment) {
			super(new ConditionMiningTarget.Of("on-the-fly"), equipment); //$NON-NLS-1$
			this.license = license;
		}

		@Override
		protected Supplier<Path> base(LicensedProduct product) {
			return () -> license;
		}

	}
}
