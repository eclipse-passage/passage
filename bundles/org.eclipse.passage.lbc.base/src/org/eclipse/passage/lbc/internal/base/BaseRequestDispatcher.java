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
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.api.BackendLicensingResponse;
import org.eclipse.passage.lbc.internal.api.BackendRequestDispatcher;
import org.eclipse.passage.lbc.internal.api.Chain;

/**
 * @since 1.0
 */

public final class BaseRequestDispatcher implements BackendRequestDispatcher {

	private final Map<BackendAction, Chain> chains;

	public BaseRequestDispatcher(Map<BackendAction, Chain> chains) {
		this.chains = chains;
	}

	@SuppressWarnings("resource")
	@Override
	public void dispatch(BackendLicensingRequest request, BackendLicensingResponse result) throws IOException {
		result.outputStream().write(execute(request).getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * 
	 * @param request
	 * @return serialized result to be sent
	 */
	private String execute(BackendLicensingRequest request) {
		Chain chain = chains.get(new BackendAction.Of(request::parameter));
		if (chain != null) {
			return chain.apply(request);
		}
		return "{\"error\":\"unsupported action\"}"; //$NON-NLS-1$
	}

}
