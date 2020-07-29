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
package org.eclipse.passage.lic.internal.base.access;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.restrictions.BaseExaminationCertificate;

@SuppressWarnings("restriction")
final class WithExaminationCertificate<T> {

	private final Framework framework;
	private final String feature;

	WithExaminationCertificate(Framework framework, String feature) {
		this.framework = framework;
		this.feature = feature;
	}

	T apply(Function<Optional<ExaminationCertificate>, T> decision) {
		Collection<Requirement> requirements = requirements();
		if (requirements.isEmpty()) { // just to avoid heavy operations below
			return decision.apply(
					Optional.of(new BaseExaminationCertificate(Collections.emptyList(), Collections.emptyList())));
		}
		ServiceInvocationResult<Collection<ConditionPack>> mining = conditions();
		if (!mining.successful()) {
			return decision.apply(Optional.empty());
		}
		Collection<Permission> permissions = permissions(mining.data());
		if (permissions.isEmpty()) {
			return decision.apply(Optional.empty());
		}
		ServiceInvocationResult<ExaminationCertificate> examination = restrictions(permissions, requirements);
		if (!examination.successful()) {
			return decision.apply(Optional.empty());
		}
		return decision.apply(Optional.of(examination.data()));
	}

	private Collection<Requirement> requirements() {
		return new Requirements(//
				framework.accessCycleConfiguration().requirementResolvers().get(), //
				feature).get();
	}

	private ServiceInvocationResult<Collection<ConditionPack>> conditions() {
		return new Conditions(//
				framework.accessCycleConfiguration().conditionMiners().get(), //
				framework.product(), //
				feature).get();
	}

	private Collection<Permission> permissions(Collection<ConditionPack> conditions) {
		return new Permissions(//
				framework.accessCycleConfiguration().permissionEmitters().get(), //
				conditions, //
				framework.product()).get();
	}

	private ServiceInvocationResult<ExaminationCertificate> restrictions(Collection<Permission> permissions,
			Collection<Requirement> requirements) {
		return new Restrictions(//
				framework.accessCycleConfiguration().examinators().get(), //
				requirements, //
				permissions, //
				framework.product()).get();
	}

}
