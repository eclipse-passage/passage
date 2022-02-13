/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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

import org.eclipse.passage.lic.api.acquire.ForsakenGrantsService;
import org.eclipse.passage.lic.api.acquire.GrantAcquisition;

public final class UnreleasedGrantsService implements ForsakenGrantsService {

	@Override
	public void takeCare(GrantAcquisition grant) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void settle() {
		throw new UnsupportedOperationException();
	}

}
