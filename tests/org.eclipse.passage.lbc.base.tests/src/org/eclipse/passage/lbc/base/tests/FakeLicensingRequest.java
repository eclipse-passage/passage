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

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.internal.api.LicensingRequest;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;

public class FakeLicensingRequest implements LicensingRequest {

	private final Map<String, String> params;

	public FakeLicensingRequest(Map<String, String> params) {
		this.params = params;
	}

	@Override
	public Supplier<Optional<ConditionAction>> action() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String parameter(String key) {
		return params.get(key);
	}

}
