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
package org.eclipse.passage.lic.internal.base.tests.requirements;

import java.util.ArrayList;

import org.eclipse.passage.lic.internal.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionEvaluatorsRegistry;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionPasringRegistry;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.ExpressionTokenAssessorsRegistry;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.PermissionEmittersRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.conditions.mining.LicenseReadingService;
import org.eclipse.passage.lic.internal.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.internal.api.inspection.RuntimeEnvironmentRegistry;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.api.io.UnemployedCodecs;
import org.eclipse.passage.lic.internal.api.registry.Registry;
import org.eclipse.passage.lic.internal.api.registry.Service;
import org.eclipse.passage.lic.internal.api.registry.ServiceId;
import org.eclipse.passage.lic.internal.api.requirements.ResolvedRequirementsRegistry;
import org.eclipse.passage.lic.internal.api.restrictions.PermissionsExaminationServicesRegistry;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
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

		private <I extends ServiceId, S extends Service<I>> Registry<I, S> noService() {
			return new ReadOnlyRegistry<I, S>(new ArrayList<>());
		}

		@Override
		public ResolvedRequirementsRegistry requirementResolvers() {
			return () -> noService();
		}

		@Override
		public MinedConditionsRegistry conditionMiners() {
			return () -> noService();
		}

		@Override
		public StreamCodecRegistry codecs() {
			return () -> noService();
		}

		@Override
		public KeyKeeperRegistry keyKeepers() {
			return () -> noService();
		}

		@Override
		public ConditionTransportRegistry transports() {
			return () -> noService();
		}

		@Override
		public PermissionEmittersRegistry permissionEmitters() {
			return () -> noService();
		}

		@Override
		public ExpressionPasringRegistry expressionParsers() {
			return () -> noService();
		}

		@Override
		public ExpressionEvaluatorsRegistry expressionEvaluators() {
			return () -> noService();
		}

		@Override
		public ExpressionTokenAssessorsRegistry expressionAssessors() {
			return () -> noService();
		}

		@Override
		public RuntimeEnvironmentRegistry environments() {
			return () -> noService();
		}

		@Override
		public PermissionsExaminationServicesRegistry examinators() {
			return () -> noService();
		}

		@Override
		public LicenseAcquisitionServicesRegistry acquirers() {
			return () -> noService();
		}

	}

	@Override
	public LicenseReadingService licenseReader() {
		throw new UnsupportedOperationException();
	}

	@Override
	public UnemployedCodecs unemployedCodecs() {
		throw new UnsupportedOperationException();
	}

}
