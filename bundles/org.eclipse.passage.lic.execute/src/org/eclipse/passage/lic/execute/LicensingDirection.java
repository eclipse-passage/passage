/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.api.registry.Registry;
import org.eclipse.passage.lic.api.registry.Service;
import org.eclipse.passage.lic.api.registry.ServiceId;
import org.eclipse.passage.lic.base.registry.JointRegistry;
import org.eclipse.passage.lic.base.registry.ReadOnlyRegistry;

interface LicensingDirection {

	public MinedConditionsRegistry conditionMiners();

	public LicenseAcquisitionServicesRegistry acquirers();

	public HashesRegistry hashes();

	static final class Joint implements LicensingDirection {

		private final List<LicensingDirection> delegates;

		Joint(LicensingDirection... delegates) {
			this.delegates = Arrays.asList(delegates);
		}

		private <I extends ServiceId, S extends Service<I>> Registry<I, S> registries(
				Function<LicensingDirection, Supplier<Registry<I, S>>> retrieve) {
			return new ReadOnlyRegistry<>(//
					new JointRegistry<>(delegates.stream()//
							.map(delegate -> retrieve.apply(delegate).get())//
							.collect(Collectors.toList())));
		}

		@Override
		public MinedConditionsRegistry conditionMiners() {
			return () -> registries(LicensingDirection::conditionMiners);
		}

		@Override
		public LicenseAcquisitionServicesRegistry acquirers() {
			return () -> registries(LicensingDirection::acquirers);
		}

		@Override
		public HashesRegistry hashes() {
			return () -> registries(LicensingDirection::hashes);
		}

	}

}
