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
package org.eclipse.passage.lbc.internal.base.chains;

import java.util.Collections;
import java.util.function.Function;

import org.eclipse.passage.lbc.internal.api.RequestedCertificate;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.base.BaseServiceInvocationResult;
import org.eclipse.passage.lic.internal.base.restrictions.BaseExaminationCertificate;

@SuppressWarnings("restriction")
public final class Acquire implements Function<RequestedCertificate, ServiceInvocationResult<ExaminationCertificate>> {

	@Override
	public ServiceInvocationResult<ExaminationCertificate> apply(RequestedCertificate t) {
		return new BaseServiceInvocationResult<>(
				new BaseExaminationCertificate(Collections.emptyMap(), Collections.emptyList()));
	}

}
