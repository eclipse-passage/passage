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
package org.eclipse.passage.lbc.server.launcher.internal;

import java.util.Collections;
import java.util.Map;

import org.eclipse.passage.lbc.api.BackendLauncher;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true)
public class ApplicationLauncher {

	private final Map<String, Object> launchArguments = Collections.emptyMap();

	private BackendLauncher server;

	@Activate
	public void activate() {
		server.launch(launchArguments);
	}

	@Deactivate
	public void deactivate() {
		server.terminate();
	}

	@Reference
	public void bind(BackendLauncher launcher) {
		this.server = launcher;
	}

}
