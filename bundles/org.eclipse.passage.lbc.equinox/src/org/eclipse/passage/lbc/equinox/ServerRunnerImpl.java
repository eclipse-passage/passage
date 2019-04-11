/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lbc.equinox;

import java.util.HashMap;

import org.eclipse.passage.lbc.runtime.BackendLauncher;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

@Component
public class ServerRunnerImpl {

	private BackendLauncher backendLauncher;

	private LoggerFactory loggerFactory;
	private Logger logger;

	@Activate
	public void activate() {
		if (backendLauncher != null) {
			backendLauncher.launch(new HashMap<String, Object>());
		} else {
			logger.error("Backend launcher is not available"); //$NON-NLS-1$
		}
	}

	@Deactivate
	public void deactivate() {
		if (backendLauncher != null) {
			backendLauncher.terminate();
		} else {
			logger.error("Backend launcher is not available"); //$NON-NLS-1$
		}
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

	@Reference
	public void bindServerHandler(BackendLauncher serverHandler) {
		logger.info(String.format("Bind BackendLauncher %s", serverHandler)); //$NON-NLS-1$
		this.backendLauncher = serverHandler;

	}

	public void unbindServerHandler(BackendLauncher serverHandler) {
		logger.info(String.format("Unbind BackendLauncher %s", serverHandler)); //$NON-NLS-1$
		this.backendLauncher = null;
	}

}
