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
import org.eclipse.passage.lic.internal.api.acquire.LicenseAcquisitionServicesRegistry;
import org.eclipse.passage.lic.internal.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.internal.api.diagnostic.Trouble;
import org.eclipse.passage.lic.internal.api.requirements.Requirement;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.diagnostic.code.NoServicesOfType;

//FIXME: YTBD, including the name
final class Lock {

	private final LicenseAcquisitionServicesRegistry acquirers;

	Lock(LicenseAcquisitionServicesRegistry acquirers) {
		this.acquirers = acquirers;
	}

	Lock(Framework framework) {
		this(framework.accessCycleConfiguration().acquirers());
	}

	ServiceInvocationResult<GrantLock> lock(ExaminationCertificate certificate) {
		if (acquirers.get().services().isEmpty()) {
			return noAcquisitionService();
		}
		ConditionMiningTarget target = target(certificate);
		if (acquirers.get().hasService(target)) {
			return noAcquisitionServiceForTarget(target);
		}
		// FIXME: appeal to proper service for Acquisition
		return null;
	}

	ServiceInvocationResult<Boolean> unlock(GrantAcqisition grant) {
		return null; // FIXME: YTBD
	}

	// FIXME: i18n
	private ServiceInvocationResult<GrantLock> noAcquisitionService() {
		return new BaseServiceInvocationResult<>(//
				new Trouble(//
						new NoServicesOfType("license acquisition"), // //$NON-NLS-1$
						"")); //$NON-NLS-1$
	}

//FIXME: i18n
	private ServiceInvocationResult<GrantLock> noAcquisitionServiceForTarget(ConditionMiningTarget target) {
		return new BaseServiceInvocationResult<>(//
				new Trouble(//
						new NoServicesOfType("license acquisition"), // //$NON-NLS-1$
						"")); //$NON-NLS-1$
	}

	// FIXME: more care here
	private ConditionMiningTarget target(ExaminationCertificate certificate) {
		Requirement key = certificate.satisfied().iterator().next();
		return certificate.satisfaction(key).conditionOrigin().miner();

	}

}
