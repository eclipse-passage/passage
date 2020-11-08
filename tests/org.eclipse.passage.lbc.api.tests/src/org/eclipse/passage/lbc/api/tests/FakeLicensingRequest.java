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
package org.eclipse.passage.lbc.api.tests;

import java.util.Map;

import org.eclipse.passage.lbc.internal.api.BackendLicensingRequest;
import org.eclipse.passage.lbc.internal.api.Requester;

public final class FakeLicensingRequest implements BackendLicensingRequest {

	private final Map<String, String> params;
	private final String body;

	public FakeLicensingRequest(Map<String, String> params, String body) {
		this.params = params;
		this.body = body;
	}

	@Override
	public String parameter(String key) {
		return params.get(key);
	}

	@Override
	public Requester requester() {
		return new Requester() {

			@Override
			public String feature() {
				return "feature"; //$NON-NLS-1$
			}

			@Override
			public String hardware() {
				return "hardware"; //$NON-NLS-1$
			}

			@Override
			public String process() {
				return "process"; //$NON-NLS-1$
			}

		};
	}

	@Override
	public String body() {
		return body;
	}

}
