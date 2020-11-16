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

import org.eclipse.passage.lic.internal.api.Framework;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.access.GrantLock;
import org.eclipse.passage.lic.internal.api.acquire.GrantAcqisition;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionService;
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.internal.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.conditions.evaluation.Permission;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.code.NoServicesOfType;
import org.eclipse.passage.lic.internal.base.i18n.AccessCycleMessages;
import org.eclipse.passage.lic.internal.base.restrictions.CertificateIsRestrictive;

//FIXME: YTBD, including the name
final class Lock {

	private final LicenseAcquisitionServicesRegistry acquirers;

	Lock(LicenseAcquisitionServicesRegistry acquirers) {
		this.acquirers = acquirers;
	}

	Lock(Framework framework) {
		this(framework.accessCycleConfiguration().acquirers());
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
	ServiceInvocationResult<GrantLock> lock(ExaminationCertificate certificate) {
		Permission permission = permission(certificate);
		ConditionMiningTarget target = permission.conditionOrigin().miner();
		if (acquirers.get().hasService(target)) {
			return noAcquisitionServiceForTarget(target);
		}
		ServiceInvocationResult<GrantAcqisition> grant = acquirers.get().service(target)//
				.acquire(permission.product(), permission.condition().feature());
		if (!grant.data().isPresent()) {
			return new BaseServiceInvocationResult<>(grant.diagnostic());
		}
		return new BaseServiceInvocationResult<>(//
				grant.diagnostic(), //
				new BaseGrantLock(certificate, grant.data().get())//
		);
	}

	ServiceInvocationResult<Boolean> unlock(GrantLock lock) {
		Permission permission = permission(lock.certificate());
		ConditionMiningTarget target = permission.conditionOrigin().miner();
		if (acquirers.get().hasService(target)) {
			return noAcquisitionServiceForTarget(target);
		}
		return acquirers.get().service(target).release(permission.product(), lock.grant());
	}

	private <T> ServiceInvocationResult<T> noAcquisitionServiceForTarget(ConditionMiningTarget target) {
		return new BaseServiceInvocationResult<T>(//
				new Trouble(//
						new NoServicesOfType(AccessCycleMessages.getString("Lock.no_service")), //$NON-NLS-1$
						String.format(//
								AccessCycleMessages.getString("Lock.no_service_for_target"), //$NON-NLS-1$
								target.toString())//
				));
	}

	/**
	 * Certificate must be non-restrictive: must contain at least one properly
	 * satisfied requirement
	 */
	private Permission permission(ExaminationCertificate certificate) {
		Requirement any = certificate.satisfied().iterator().next(); // guaranteed to exist
		return certificate.satisfaction(any); // guaranteed to exist and be not null
	}

}
