/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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

import org.eclipse.passage.lic.api.AccessCycleConfiguration;
import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.acquire.GrantsTraceService;
import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.api.agreements.AgreementAcceptanceService;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionEvaluatorsRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionPasringRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.ExpressionTokenAssessorsRegistry;
import org.eclipse.passage.lic.api.conditions.evaluation.PermissionEmittersRegistry;
import org.eclipse.passage.lic.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.api.conditions.mining.MinedConditionsRegistry;
import org.eclipse.passage.lic.api.conditions.mining.MiningEquipment;
import org.eclipse.passage.lic.api.inspection.RuntimeEnvironmentRegistry;
import org.eclipse.passage.lic.api.io.HashesRegistry;
import org.eclipse.passage.lic.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.api.requirements.ResolvedRequirementsRegistry;
import org.eclipse.passage.lic.api.restrictions.PermissionsExaminationServicesRegistry;
import org.osgi.framework.Bundle;

/**
 * @since 2.3
 */
@SuppressWarnings("restriction")
public final class NamespaceConfiguraton implements AccessCycleConfiguration {

	private final AccessCycleConfiguration delegate;
	private final BundleRequirementsForNamespace requirements;

	public NamespaceConfiguraton(String namespace, Supplier<LicensedProduct> product, Supplier<Bundle> bundle) {
		this(new FocusedAccessCycleConfiguration.Wide(product, bundle), new BundleRequirementsForNamespace(namespace));
	}

	public NamespaceConfiguraton(String namespace, AccessCycleConfiguration delegate) {
		this(delegate, new BundleRequirementsForNamespace(namespace));
	}

	public NamespaceConfiguraton(AccessCycleConfiguration delegate, BundleRequirementsForNamespace requirements) {
		this.delegate = delegate;
		this.requirements = requirements;
	}

	@Override
	public ResolvedRequirementsRegistry requirementResolvers() {
		return requirements;
	}

	@Override
	public MinedConditionsRegistry conditionMiners() {
		return delegate.conditionMiners();
	}

	@Override
	public MiningEquipment miningEquipment() {
		return delegate.miningEquipment();
	}

	@Override
	public StreamCodecRegistry codecs() {
		return delegate.codecs();
	}

	@Override
	public KeyKeeperRegistry keyKeepers() {
		return delegate.keyKeepers();
	}

	@Override
	public ConditionTransportRegistry transports() {
		return delegate.transports();
	}

	@Override
	public PermissionEmittersRegistry permissionEmitters() {
		return delegate.permissionEmitters();
	}

	@Override
	public ExpressionPasringRegistry expressionParsers() {
		return delegate.expressionParsers();
	}

	@Override
	public ExpressionEvaluatorsRegistry expressionEvaluators() {
		return delegate.expressionEvaluators();
	}

	@Override
	public ExpressionTokenAssessorsRegistry expressionAssessors() {
		return delegate.expressionAssessors();
	}

	@Override
	public RuntimeEnvironmentRegistry environments() {
		return delegate.environments();
	}

	@Override
	public PermissionsExaminationServicesRegistry examinators() {
		return delegate.examinators();
	}

	@Override
	public LicenseAcquisitionServicesRegistry acquirers() {
		return delegate.acquirers();
	}

	@Override
	public HashesRegistry hashes() {
		return delegate.hashes();
	}

	@Override
	public AgreementAcceptanceService acceptance() {
		return delegate.acceptance();
	}

	@Override
	public GrantsTraceService grantsTrace() {
		return delegate.grantsTrace();
	}

}
