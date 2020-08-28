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
package org.eclipse.passage.lbc.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lbc.internal.api.BackendLauncher;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component(immediate = true)
public class OsgiServerLauncher {

	private final Set<BackendLauncher> servers = new HashSet<>();

	@Activate
	public void activate() {
		System.out.println("activated"); //$NON-NLS-1$
		servers.forEach(server -> server.launch(params()));
	}

	@Deactivate
	public void deactivate() {
		servers.forEach(BackendLauncher::terminate);
	}

	@Reference(cardinality = ReferenceCardinality.AT_LEAST_ONE)
	public void bind(BackendLauncher launcher) {
		servers.add(launcher);
	}

	public void unbind(BackendLauncher launcher) {
		servers.remove(launcher);
	}

	private Map<String, Object> params() {
		return new HashMap<>();
	}

}
