/*******************************************************************************
 * Copyright (c) 2018-2020 ArSysOp
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
package org.eclipse.passage.lbc.internal.equinox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lbc.api.BackendCluster;
import org.eclipse.passage.lbc.api.BackendLauncher;
import org.eclipse.passage.lbc.internal.equinox.i18n.EquinoxMessages;
import org.eclipse.passage.lic.api.LicensingResult;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

@Component(immediate = true)
public class ServerRunnerImpl implements BackendCluster {

	private List<BackendLauncher> backendLaunchers = new ArrayList<>();
	private LoggerFactory loggerFactory;
	private Logger logger;

	@Activate
	public void activate() {
	}

	@Deactivate
	public void deactivate() {
		for (BackendLauncher launcher : backendLaunchers) {
			launcher.terminate();
		}
		backendLaunchers.clear();
	}

	@Reference
	public void bindLogger(LoggerFactory factory) {
		this.loggerFactory = factory;
		this.logger = factory.getLogger(ServerRunnerImpl.class);
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
		LicensingResult result = serverHandler.launch(context);
		switch (result.getSeverity()) {
		case LicensingResult.ERROR:
			logger.error(result.getMessage());
			logger.error(
					NLS.bind(EquinoxMessages.ServerRunnerImpl_error_launching, serverHandler.getClass().getName()));
			break;
		case LicensingResult.OK:
			logger.info(result.getMessage());
			backendLaunchers.add(serverHandler);
			break;
		default:
			logger.warn(result.getMessage());
			break;
		}
	}

	public void unbindServerHandler(BackendLauncher serverHandler) {
		if (serverHandler != null) {
			logger.info(NLS.bind(EquinoxMessages.ServerRunnerImpl_i_launcher_unbind, serverHandler));
			serverHandler.terminate();
			backendLaunchers.remove(serverHandler);
		}
	}

	@Override
	public List<BackendLauncher> getBackendLaunchers() {
		return backendLaunchers;
	}
}
