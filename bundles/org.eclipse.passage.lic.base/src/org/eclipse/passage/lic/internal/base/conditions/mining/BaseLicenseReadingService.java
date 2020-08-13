/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.base.conditions.mining;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.LicenseReadingService;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;

@SuppressWarnings("restriction")
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
	public ServiceInvocationResult<ConditionPack> read(Path license) {
		ServiceInvocationResult<Collection<ConditionPack>> all = //
				new OnTheFlyConditions(license.getParent(), equipment).all(product);
		if (!all.diagnostic().severe().isEmpty() || !all.data().isPresent()) {
			return new BaseServiceInvocationResult<>(all.diagnostic());
		}
		return new BaseServiceInvocationResult<>(//
				all.diagnostic(), //
				oneOf(all.data().get(), license.normalize()));
	}

	private Optional<ConditionPack> oneOf(Collection<ConditionPack> packs, Path license) {
		return packs.stream()//
				.filter(pack -> originatedHere(pack, license)) //
				.findFirst();
	}

	private boolean originatedHere(ConditionPack pack, Path license) {
		return Paths.get(pack.origin()).normalize().equals(license);
	}

	private static final class OnTheFlyConditions extends LocalConditions {

		private final Path folder;

		private OnTheFlyConditions(Path folder, MiningEquipment equipment) {
			super(new StringServiceId("on-the-fly"), equipment); //$NON-NLS-1$
			this.folder = folder;
		}

		@Override
		protected Path base() {
			return folder;
		}

	}
}
