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
package org.eclipse.passage.lic.internal.base;

import java.util.ArrayList;

import org.eclipse.passage.lic.internal.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditions;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirements;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirementsRegistry;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;

@SuppressWarnings("restriction")
final class SabotagedFramework implements Framework {

	private final AccessCycleConfiguration config = new SabotagedAccessCycleConfiguration();
	private final LicensedProduct product = new BaseLicensedProduct("test-product", "1.0.0"); //$NON-NLS-1$//$NON-NLS-2$

	@Override
	public LicensedProduct product() {
		return product;
	}

	@Override
	public AccessCycleConfiguration accessCycleConfiguration() {
		return config;
	}

	private static class SabotagedAccessCycleConfiguration implements AccessCycleConfiguration {

		@Override
		public ResolvedRequirementsRegistry requirementsRegistry() {
			return new NoRequirementResolvers();
		}

		@Override
		public MinedConditionsRegistry conditionsRegistry() {
			return new NoConditionMiners();
		}

	}

	private static class NoRequirementResolvers implements ResolvedRequirementsRegistry {

		@Override
		public Registry<StringServiceId, ResolvedRequirements> get() {
			return new ReadOnlyRegistry<StringServiceId, ResolvedRequirements>(new ArrayList<>());
		}

	}

	private static class NoConditionMiners implements MinedConditionsRegistry {

		@Override
		public Registry<StringServiceId, MinedConditions> get() {
			return new ReadOnlyRegistry<StringServiceId, MinedConditions>(new ArrayList<>());
		}

	}

}
