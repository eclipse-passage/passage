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
 *     Nikifor Fedorov
 *******************************************************************************/
package org.eclipse.passage.lbc.internal.equinox;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lbc.api.BackendLaunchArguments;
import org.eclipse.passage.lbc.api.BackendLauncher;
import org.eclipse.passage.lbc.api.BackendLauncherProvider;
import org.eclipse.passage.lbc.internal.equinox.i18n.EquinoxMessages;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

@Component(immediate = true)
public class ServerRunner implements BackendLauncherProvider {

	private Map<BackendLauncher, BackendLaunchArguments> configurations = new HashMap<>();
	private LoggerFactory loggerFactory;
	private Logger logger;

	@Activate
	public void activate() {
		configurations.forEach((launcher, arguments) -> launcher.launch(arguments.get()));
	}

	@Deactivate
	public void deactivate() {
		configurations.forEach((launcher, arguments) -> launcher.terminate());
		configurations.clear();
	}

	@Reference
	public void bindLogger(LoggerFactory factory) {
		this.loggerFactory = factory;
		this.logger = factory.getLogger(ServerRunner.class);
	}

	public void unbindLogger(LoggerFactory factory) {
		if (this.loggerFactory == factory) {
			this.loggerFactory = null;
			this.logger = null;
		}
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	public void bindServerHandler(BackendLauncher serverHandler, Map<String, Object> context) {
		logger.info(NLS.bind(EquinoxMessages.ServerRunnerImpl_i_launcher_bind, serverHandler));
		configurations.put(serverHandler, new LaunchArguments(context));
	}

	public void unbindServerHandler(BackendLauncher serverHandler) {
		logger.info(NLS.bind(EquinoxMessages.ServerRunnerImpl_i_launcher_unbind, serverHandler));
		configurations.remove(serverHandler);
	}

	@Override
	public Map<BackendLauncher, BackendLaunchArguments> getBackendLaunchers() {
		return configurations;
	}
}
