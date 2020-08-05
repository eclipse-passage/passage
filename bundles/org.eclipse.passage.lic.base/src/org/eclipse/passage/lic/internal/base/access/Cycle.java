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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.internal.base.diagnostic.SumOfDiagnostics;

@SuppressWarnings("restriction")
abstract class Cycle<T> {

	private final Framework framework;
	private final String feature;
	private final List<Diagnostic> diagnostics;

	Cycle(Framework framework, String feature) {
		this.framework = framework;
		this.feature = feature;
		this.diagnostics = new ArrayList<>();
	}

	T apply() {
		return examine(this::requirements, this::permissions);
	}

	private T examine(Supplier<ServiceInvocationResult<Collection<Requirement>>> requirements, //
			Supplier<ServiceInvocationResult<Collection<Permission>>> permissions) {
		ServiceInvocationResult<Collection<Requirement>> reqs = requirements.get();
		if (failed(reqs)) {
			return stop();
		}
		if (empty(reqs)) {
			// no requirements means no restrictions anyway, which is always green light,
			// we just want to avoid heavy operations below
			return freeWayOut();
		}
		ServiceInvocationResult<Collection<Permission>> perms = permissions.get();
		if (failed(perms)) {
			return stop();
		}
		ServiceInvocationResult<ExaminationCertificate> examination = //
				restrictions(reqs.data().get(), perms.data().get());
		if (failed(examination)) {
			return stop();
		}
		return stop(examination.data().get());
	}

	private T stop() {
		return stopOnError(diagnostic());
	}

	private T stop(ExaminationCertificate certificate) {
		return stopOnCertificate(certificate, diagnostic());
	}

	/**
	 * Severe error cannot be fixed by new data (say, imported license). It must
	 * cause unavoidable denial, but for user's sake we'd like to expose the state
	 * of affairs to support one with the failure reason information.
	 */
	protected abstract T stopOnError(Diagnostic diagnostic);

	/**
	 * We successfully made it to and through the examination, and even have got a
	 * certificate!
	 */
	protected abstract T stopOnCertificate(ExaminationCertificate certificate, Diagnostic diagnostic);

	protected abstract T freeWayOut();

	private Diagnostic diagnostic() {
		return diagnostics.stream().reduce(new BaseDiagnostic(), new SumOfDiagnostics());
	}

	private boolean failed(ServiceInvocationResult<?> result) {
		return !result.diagnostic().severe().isEmpty();
	}

	private <U> boolean empty(ServiceInvocationResult<Collection<U>> collection) {
		return collection.data().map(Collection::isEmpty).orElse(true);
	}

	private <U> ServiceInvocationResult<U> scan(ServiceInvocationResult<U> result) {
		diagnostics.add(result.diagnostic());
		return result;
	}

	private ServiceInvocationResult<Collection<Requirement>> requirements() {
		return scan(new Requirements(//
				framework.accessCycleConfiguration().requirementResolvers().get(), //
				feature).get());
	}

	private ServiceInvocationResult<Collection<ConditionPack>> conditions() {
		return scan(new Conditions(//
				framework.accessCycleConfiguration().conditionMiners().get(), //
				framework.product(), //
				feature).get());
	}

	private ServiceInvocationResult<Collection<Permission>> permissions() {
		ServiceInvocationResult<Collection<ConditionPack>> conditions = conditions();
		if (failed(conditions) || empty(conditions)) {
			return new BaseServiceInvocationResult<Collection<Permission>>(conditions.diagnostic(),
					Collections.emptyList());
		}
		return scan(new Permissions(//
				framework.accessCycleConfiguration().permissionEmitters().get(), //
				conditions.data().get(), //
				framework.product()).get());
	}

	private ServiceInvocationResult<ExaminationCertificate> restrictions(Collection<Requirement> requirements,
			Collection<Permission> permissions) {
		return scan(new Restrictions(//
				framework.accessCycleConfiguration().examinators().get(), //
				requirements, //
				permissions, //
				framework.product()).get());
	}

}
