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

import java.util.Optional;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.passage.lbc.internal.api.LicensingRequest;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.net.LicensingAction;

/**
 * @since 1.0
 */
public class BaseLicensingRequest implements LicensingRequest {

	private final HttpServletRequest httpRequest;

	public BaseLicensingRequest(HttpServletRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	@Override
	public Supplier<Optional<ConditionAction>> action() {
		return new LicensingAction(key -> new ConditionAction.Of(parameter(key)));
	}

	@Override
	public String parameter(String key) {
		return httpRequest.getParameter(key);
	}

}
