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

import org.eclipse.passage.lbc.internal.api.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.RawRequest;
import org.eclipse.passage.lbc.internal.base.acquire.Acquisition;
import org.eclipse.passage.lic.internal.api.LicensingException;

final class Release extends ChoreDraft {

	Release(RawRequest data) {
		super(data);
	}

	@Override
	protected FloatingResponse withProductUser(ProductUserRequest request) throws LicensingException {
		return new Acquisition(request).returnBack();
	}
}
