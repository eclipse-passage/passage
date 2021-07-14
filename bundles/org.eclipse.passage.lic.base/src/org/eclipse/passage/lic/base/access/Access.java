/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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

import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.access.GrantLockAttempt;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.diagnostic.Trouble;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.base.diagnostic.BaseDiagnostic;
import org.eclipse.passage.lic.base.diagnostic.SumOfDiagnostics;
import org.eclipse.passage.lic.base.diagnostic.code.NoRequirements;
import org.eclipse.passage.lic.base.restrictions.CertificateIsRestrictive;

/**
 * Top-level access cycle
 * 
 * @since 2.1
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
		if (empty(certificate.data().get())) {
			return unknownFeature(feature, certificate.diagnostic());
		}
		ServiceInvocationResult<GrantLockAttempt> lock = new Lock(framework).lock(certificate.data().get());
		return new BaseServiceInvocationResult<>(//
				new SumOfDiagnostics().apply(certificate.diagnostic(), lock.diagnostic()), //
				lock.data());
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

	private ServiceInvocationResult<GrantLockAttempt> unknownFeature(String feature, Diagnostic diagnostic) {
		return new BaseServiceInvocationResult<>(//
				new SumOfDiagnostics().apply(//
						diagnostic, //
						new BaseDiagnostic(new Trouble(new NoRequirements(), feature))//
				));
	}

	private boolean empty(ExaminationCertificate certificate) {
		return certificate.restrictions().isEmpty() && certificate.satisfied().isEmpty();
	}

}
