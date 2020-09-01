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
package org.eclipse.passage.lbc.chains.transform;

import java.util.function.Function;

import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.api.RequestedCertificate;
import org.eclipse.passage.lbc.internal.base.BaseRequestedCertificate;
import org.eclipse.passage.lbc.json.Deserialization;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

@SuppressWarnings("restriction")
public final class CertificateOfRequest implements Function<BackendLicensingRequest, RequestedCertificate> {

	@Override
	public RequestedCertificate apply(BackendLicensingRequest request) {
		return new BaseRequestedCertificate(
				new Deserialization<>(ExaminationCertificate.class).apply(request.body()).data().get(),
				request.requester());
	}

}
