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
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.api.BackendLicensingResponse;
import org.eclipse.passage.lbc.internal.api.BackendRequestDispatcher;
import org.eclipse.passage.lbc.internal.api.chains.Chain;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.net.LicensingAction;

/**
 * @since 1.0
 */

public final class BaseRequestDispatcher implements BackendRequestDispatcher {

	private final Map<LicensingAction, Chain> chains;
	private final Function<BackendLicensingRequest, String> action;

	public BaseRequestDispatcher(Map<LicensingAction, Chain> chains) {
		this.chains = chains;
		this.action = r -> r.parameter("action"); //$NON-NLS-1$
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
		return Optional.ofNullable(action.apply(request))//
				.map(ConditionAction.Of::new)//
				.map(LicensingAction::new)//
				.map(chains::get)//
				.map(c -> c.apply(request))//
				.orElseGet(() -> String.format("{\"error\":\"unsupported action %s\"}", //$NON-NLS-1$
						action.apply(request)));
	}

}
