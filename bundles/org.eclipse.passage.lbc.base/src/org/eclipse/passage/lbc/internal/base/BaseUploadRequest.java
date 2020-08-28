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

import java.util.List;
import java.util.Objects;

import org.eclipse.passage.lbc.internal.api.Requester;
import org.eclipse.passage.lbc.internal.api.UploadRequest;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;

/**
 * @since 1.0
 */
public final class BaseUploadRequest implements UploadRequest {

	private final Requester requester;
	private final List<LicenseGrant> grants;

	public BaseUploadRequest(List<LicenseGrant> grants, Requester requester) {
		Objects.requireNonNull(requester, "BaseUploadRequest::requester"); //$NON-NLS-1$
		Objects.requireNonNull(grants, "BaseUploadRequest::grants"); //$NON-NLS-1$
		this.requester = requester;
		this.grants = grants;
	}

	@Override
	public List<LicenseGrant> grants() {
		return grants;
	}

	@Override
	public Requester requester() {
		return requester;
	}

}
