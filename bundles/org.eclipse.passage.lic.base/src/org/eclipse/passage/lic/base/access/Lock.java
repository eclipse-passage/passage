/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
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
package org.eclipse.passage.lic.base.access;

import java.util.Collections;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.api.acquire.GrantAcquisition;
import org.eclipse.passage.lic.api.acquire.GrantsTraceService;
import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionService;
import org.eclipse.passage.lic.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.requirements.Requirement;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.api.restrictions.Restriction;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.code.NoServicesOfType;
import org.eclipse.passage.lic.base.diagnostic.code.ServiceFailedOnMorsel;
import org.eclipse.passage.lic.base.diagnostic.code.TentativeAccess;
import org.eclipse.passage.lic.base.restrictions.CertificateIsRestrictive;
import org.eclipse.passage.lic.base.restrictions.RequirementDemandsExecutionStop;
import org.eclipse.passage.lic.internal.base.i18n.AccessCycleMessages;

final class Lock {

	private final LicenseAcquisitionServicesRegistry acquirers;
	private final GrantsTraceService traces;

	Lock(LicenseAcquisitionServicesRegistry acquirers, GrantsTraceService traces) {
		this.acquirers = acquirers;
		this.traces = traces;
	}

	Lock(Framework framework) {
		this(framework.accessCycleConfiguration().acquirers(), framework.accessCycleConfiguration().grantsTrace());
	}

	/**
	 * Appeals to a LicenseAcquisitionService in order to lock an actual grant for a
	 * requested feature
	 * 
	 * @param valid, positive, not-restrictive certificate
	 * @see ExaminationCertificate
	 * @see CertificateIsRestrictive
	 * @see LicenseAcquisitionService
	 */
	ServiceInvocationResult<GrantLockAttempt> lock(ExaminationCertificate certificate) {
		if (notPermissive(certificate)) {
			return tentativeGrant(certificate);
		}
		Permission permission = permission(certificate);
		ConditionMiningTarget target = permission.conditionOrigin().miner();
		if (!acquirers.get().hasService(target)) {
			return noService(new BaseGrantLockAttempt.Failed(certificate), target);
		}
		ServiceInvocationResult<GrantAcquisition> grant = acquirers.get().service(target)//
				.acquire(permission.product(), permission.condition().feature());
		if (!grant.data().isPresent()) {
			return canIssueTentativeGrant(certificate) //
					? tentativeGrant(certificate) //
					: noGrant(certificate, grant);
		}
		traces.trace(grant.data().get());
		return grant(certificate, grant.data().get(), grant.diagnostic());
	}

	ServiceInvocationResult<Boolean> unlock(GrantLockAttempt lock) {
		if (!lock.successful()) {
			return wrongLockWarning(); // It's illegal state actually. Should we throw something?
		}
		if (new TentativeFeatureAccess().test(lock.grant())) {
			return new BaseServiceInvocationResult<>(Boolean.TRUE);
		}
		Permission permission = permission(lock.certificate());
		ConditionMiningTarget target = permission.conditionOrigin().miner();
		if (!acquirers.get().hasService(target)) {
			return noService(Boolean.FALSE, target);
		}
		ServiceInvocationResult<Boolean> response = acquirers.get().service(target)//
				.release(permission.product(), lock.grant());
		if (new GrantReleased().test(response)) {
			traces.forget(lock.grant());
		}
		return response;
	}

	private BaseServiceInvocationResult<GrantLockAttempt> noGrant(ExaminationCertificate certificate,
			ServiceInvocationResult<GrantAcquisition> grant) {
		return new BaseServiceInvocationResult<>(//
				grant.diagnostic(), //
				new BaseGrantLockAttempt.Failed(certificate));
	}

	private ServiceInvocationResult<GrantLockAttempt> grant(ExaminationCertificate certificate, GrantAcquisition grant,
			Diagnostic diagnostic) {
		return new BaseServiceInvocationResult<>(//
				diagnostic, //
				new BaseGrantLockAttempt.Successful(certificate, grant));
	}

	private ServiceInvocationResult<Boolean> wrongLockWarning() {
		return new BaseServiceInvocationResult<>(fail("Lock.wrong_release")); //$NON-NLS-1$
	}

	private BaseDiagnostic fail(String details) {
		return new BaseDiagnostic(//
				Collections.emptyList(), //
				Collections.singletonList(//
						new Trouble(//
								new ServiceFailedOnMorsel(), //
								AccessCycleMessages.getString(details))));
	}

	private <T> ServiceInvocationResult<T> noService(T data, ConditionMiningTarget target) {
		return new BaseServiceInvocationResult<T>(//
				new BaseDiagnostic(new Trouble(//
						new NoServicesOfType(AccessCycleMessages.getString("Lock.service_type")), //$NON-NLS-1$
						String.format(//
								AccessCycleMessages.getString("Lock.no_service_for_target"), //$NON-NLS-1$
								target.toString())//
				)), //
				data);
	}

	/**
	 * {@code Warning} and {@code Info} {@code requirement}s can stay unsatisfied
	 * and still a feature must be accessible for usage
	 */
	private boolean notPermissive(ExaminationCertificate certificate) {
		return certificate.satisfied().isEmpty();
	}

	/**
	 * Certificate must be permissive: must contain at least one properly satisfied
	 * requirement // #569004
	 */
	private Permission permission(ExaminationCertificate certificate) {
		Requirement any = certificate.satisfied().iterator().next(); // guaranteed to exist
		return certificate.satisfaction(any); // guaranteed to exist and be not null
	}

	private ServiceInvocationResult<GrantLockAttempt> tentativeGrant(ExaminationCertificate certificate) {
		return grant(certificate, new TentativeFeatureAccess(feature(certificate)).get(), tentativeReport(certificate));
	}

	private Diagnostic tentativeReport(ExaminationCertificate certificate) {
		return new BaseDiagnostic(//
				Collections.emptyList(), //
				certificate.restrictions().stream()//
						.map(this::explained)//
						.collect(Collectors.toList()));
	}

	private Trouble explained(Restriction restriction) {
		return new Trouble(new TentativeAccess(), //
				String.format(//
						AccessCycleMessages.getString("Lock.temp_access"), //$NON-NLS-1$
						feature(restriction.unsatisfiedRequirement())));
	}

	/**
	 * Certificate is guaranteed to be not empty: either restrictions or
	 * satisfactions are present
	 */
	private String feature(ExaminationCertificate certificate) {
		return feature(//
				certificate.restrictions().isEmpty() //
						? certificate.satisfied().iterator().next()//
						: certificate.restrictions().iterator().next().unsatisfiedRequirement()//
		);
	}

	private String feature(Requirement requirement) {
		return requirement.feature().identifier();
	}

	private boolean canIssueTentativeGrant(ExaminationCertificate certificate) {
		return certificate.satisfied().stream().noneMatch(new RequirementDemandsExecutionStop());
	}

}
