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
 *     Nikifor Fedorov
 *******************************************************************************/
package org.eclipse.passage.lbc.internal.equinox;

import java.util.Map;

import org.eclipse.passage.lbc.api.BackendLaunchArguments;

public class LaunchArguments implements BackendLaunchArguments {

	private final Map<String, Object> arguments;

	public LaunchArguments(Map<String, Object> arguments) {
		this.arguments = arguments;
	}

	@Override
	public Map<String, Object> get() {
		return arguments;
	}

}
