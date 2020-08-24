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
package org.eclipse.passage.lbc.internal.base;

import java.io.IOException;
import java.util.Optional;

import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.api.CertificateRequestData;
import org.eclipse.passage.lbc.internal.api.Requester;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;
import org.eclipse.passage.lic.internal.json.JsonObjectMapper;

/**
 * @since 1.0
 */
public final class BaseCertificateRequestData implements CertificateRequestData {

	private final BackendLicensingRequest request;

	public BaseCertificateRequestData(BackendLicensingRequest request) {
		this.request = request;
	}

	@Override
	public Requester requester() {
		return request.requester();
	}

	@Override
	public Optional<ExaminationCertificate> certificate() {
		try {
			return Optional.of(new JsonObjectMapper().get().readValue(request.body(), ExaminationCertificate.class));
		} catch (IOException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

}
