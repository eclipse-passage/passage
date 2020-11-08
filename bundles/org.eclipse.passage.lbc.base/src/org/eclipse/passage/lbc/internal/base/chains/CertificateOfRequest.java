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

import java.util.function.Function;

import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.api.RequestedCertificate;
import org.eclipse.passage.lbc.internal.api.persistence.Deserialization;
import org.eclipse.passage.lbc.internal.base.BaseRequestedCertificate;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

public final class CertificateOfRequest implements Function<BackendLicensingRequest, RequestedCertificate> {

	private final Deserialization<ExaminationCertificate> deserialization;

	public CertificateOfRequest(Deserialization<ExaminationCertificate> deserialization) {
		this.deserialization = deserialization;
	}

	@Override
	public RequestedCertificate apply(BackendLicensingRequest request) {
		return new BaseRequestedCertificate(deserialization.apply(request.body()).data().get(), request.requester());
	}

}
