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

import java.util.Objects;

import org.eclipse.passage.lic.internal.api.access.GrantLock;
import org.eclipse.passage.lic.internal.api.acquire.GrantAcqisition;
import org.eclipse.passage.lic.internal.api.restrictions.ExaminationCertificate;

final class BaseGrantLock implements GrantLock {

	private final ExaminationCertificate certificate;
	private final GrantAcqisition grant;

	public BaseGrantLock(ExaminationCertificate certificate, GrantAcqisition grant) {
		Objects.requireNonNull(certificate, "BaseGrantLock::certificate"); //$NON-NLS-1$
		Objects.requireNonNull(grant, "BaseGrantLock::grant"); //$NON-NLS-1$
		this.certificate = certificate;
		this.grant = grant;
	}

	@Override
	public ExaminationCertificate certificate() {
		return certificate;
	}

	@Override
	public GrantAcqisition grant() {
		return grant;
	}

}
