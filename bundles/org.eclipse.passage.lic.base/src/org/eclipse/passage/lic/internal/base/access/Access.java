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
import org.eclipse.passage.lic.internal.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.restrictions.CertificateIsRestrictive;

/**
 * Top-level access cycle
 */
public final class Access {

	private final Framework framework;

	public Access(Framework framework) {
		this.framework = framework;
	}

	public boolean canUse(String feature) {
		return new Allow(framework, feature).apply();
	}

	public ServiceInvocationResult<ExaminationCertificate> assess() {
		return new Assess(framework).apply();
	}

	public ServiceInvocationResult<GrantLockAttempt> acquire(String feature) {
		ServiceInvocationResult<ExaminationCertificate> certificate = new Assess(framework, feature).apply();
		if (new CertificateIsRestrictive().test(certificate.data())) {
			return failOnAccess(certificate);
		}
		return new Lock(framework).lock(certificate.data().get());
	}

	public ServiceInvocationResult<Boolean> release(GrantLockAttempt lock) {
		return new Lock(framework).unlock(lock);
	}

	private ServiceInvocationResult<GrantLockAttempt> failOnAccess(
			ServiceInvocationResult<ExaminationCertificate> assessment) {
		if (assessment.data().isPresent()) {
			return new BaseServiceInvocationResult<>(assessment.diagnostic(),
					new BaseGrantLockAttempt.Failed(assessment.data().get()));
		}
		return new BaseServiceInvocationResult<>(assessment.diagnostic());
	}

}
