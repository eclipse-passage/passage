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
package org.eclipse.passage.lbc.base.tests;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.api.Requester;
import org.eclipse.passage.lbc.internal.base.BaseRequester;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;

public class FakeLicensingRequest implements BackendLicensingRequest {

	private final Map<String, String> params;
	private final String body;

	public FakeLicensingRequest(Map<String, String> params, String body) {
		this.params = params;
		this.body = body;
	}

	@Override
	public Supplier<Optional<ConditionAction>> action() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String parameter(String key) {
		return params.get(key);
	}

	@Override
	public Requester requester() {
		return new BaseRequester("process", "hardware", "feature"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
	}

	@Override
	public String body() throws IOException {
		return body;
	}

}
