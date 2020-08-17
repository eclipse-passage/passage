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

import org.eclipse.passage.lbc.internal.api.BackendFloatingServer;
import org.eclipse.passage.lbc.internal.api.BackendRequestDispatcher;
import org.eclipse.passage.lbc.internal.api.LicensingRequest;
import org.eclipse.passage.lbc.internal.api.LicensingResponse;

/**
 * @since 1.0
 */

public class BaseRequestDispatcher implements BackendRequestDispatcher {

	private final BackendFloatingServer server = new PassageFloatingServer();

	@Override
	public void dispatch(LicensingRequest request, LicensingResponse result) throws IOException {
		// TODO: execute server's method according to request's action
	}

}
