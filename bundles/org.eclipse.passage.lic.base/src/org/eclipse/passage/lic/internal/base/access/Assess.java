/*******************************************************************************
 * Copyright (c) 2020, 2025 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *     ArSysOp - further support and improvements
 *******************************************************************************/
package org.eclipse.passage.lic.internal.base.access;

import org.eclipse.passage.lic.api.FeatureIdentifier;
import org.eclipse.passage.lic.api.Framework;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.base.BaseServiceInvocationResult;

final class Assess extends Cycle<ServiceInvocationResult<ExaminationCertificate>> {

	Assess(Framework framework, FeatureIdentifier feature) {
		super(framework, feature);
	}

	Assess(Framework framework) {
		super(framework);
	}

	@Override
	protected ServiceInvocationResult<ExaminationCertificate> stopOnError(Diagnostic diagnostic) {
		return new BaseServiceInvocationResult<>(diagnostic);
	}

	@Override
	protected ServiceInvocationResult<ExaminationCertificate> stopOnCertificate(ExaminationCertificate certificate,
			Diagnostic diagnostic) {
		if (feature().isPresent()) {
			return acquire(certificate, diagnostic);
		}
		return new BaseServiceInvocationResult<>(diagnostic, certificate);
	}

	private ServiceInvocationResult<ExaminationCertificate> acquire(ExaminationCertificate certificate,
			Diagnostic diagnostic) {
		// TODO: YTBD
		return new BaseServiceInvocationResult<>(diagnostic, certificate);
	}

}
