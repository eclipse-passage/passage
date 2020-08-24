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
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.api.Requester;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.net.LicensingAction;

/**
 * @since 1.0
 */
public final class BaseLicensingRequest implements BackendLicensingRequest {

	private final HttpServletRequest httpRequest;

	public BaseLicensingRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	@Override
	public Supplier<Optional<ConditionAction>> action() {
		return new LicensingAction(key -> new ConditionAction.Of(parameter(key)));
	}

	@Override
	public String body() throws IOException {
		StringBuilder builder = new StringBuilder();
		httpRequest.getReader().lines().map(builder::append);
		return builder.toString();
	}

	@Override
	public String parameter(String key) {
		return httpRequest.getParameter(key);
	}

	@Override
	public Requester requester() {
		return new BaseRequester(parameter("process"), parameter("hardware"), parameter("feature")); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
	}

}
