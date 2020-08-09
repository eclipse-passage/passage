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
import org.eclipse.passage.lic.internal.api.diagnostic.Diagnostic;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;

final class Expose extends Cycle<ServiceInvocationResult<ExaminationCertificate>> {

	Expose(Framework framework, String feature) {
		super(framework, feature);
	}

	@Override
	protected ServiceInvocationResult<ExaminationCertificate> stopOnError(Diagnostic diagnostic) {
		return new BaseServiceInvocationResult<ExaminationCertificate>(diagnostic);
	}

	@Override
	protected ServiceInvocationResult<ExaminationCertificate> stopOnCertificate(ExaminationCertificate certificate,
			Diagnostic diagnostic) {
		return new BaseServiceInvocationResult<ExaminationCertificate>(diagnostic, certificate);
	}

	@Override
	protected ServiceInvocationResult<ExaminationCertificate> freeWayOut() {
		return new BaseServiceInvocationResult<ExaminationCertificate>();
	}

}
