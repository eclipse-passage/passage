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

import org.eclipse.passage.lbc.internal.api.RequestedCertificate;
import org.eclipse.passage.lbc.internal.api.Requester;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

/**
 * @since 1.0
 */
public final class BaseRequestedCertificate implements RequestedCertificate {

	private final Requester requester;
	private final ExaminationCertificate certificate;

	public BaseRequestedCertificate(ExaminationCertificate certificate, Requester requester) {
		this.requester = requester;
		this.certificate = certificate;
	}

	@Override
	public Requester requester() {
		return requester;
	}

	@Override
	public ExaminationCertificate certificate() {
		return certificate;
	}

}
