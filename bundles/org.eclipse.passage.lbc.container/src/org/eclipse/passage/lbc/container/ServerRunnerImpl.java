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
package org.eclipse.passage.lbc.container;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.passage.lbc.runtime.BackendLauncher;
import org.eclipse.passage.lbc.runtime.BackendActionExecutor;
import org.eclipse.passage.lbc.runtime.BackendRequestDispatcher;
import org.eclipse.passage.lbc.runtime.ServerRequestHandler;
import org.eclipse.passage.lic.net.LicensingRequests;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

@Component
public class ServerRunnerImpl {

	private static final String REGISTERED = "[Registered]  %s";
	private static final String REQUEST_HANDLER_NOT_FOUND = "Request handler not registrated for component ";

	private BackendLauncher serverHandler;
	private Map<String, ServerRequestHandler> requestHandlers = new HashMap<>();
	private Map<String, BackendRequestDispatcher> requestExecutors = new HashMap<>();
	private Map<String, BackendActionExecutor> requestActions = new HashMap<>();

	private Logger logger;

	public void activate() {
		if (serverHandler != null) {
			serverHandler.launch(new HashMap<String, Object>());
		} else {
			logger.error("Server not registrated");
		}
	}

	@Reference
	public void bindLogger(LoggerFactory loggerFactory) {
		this.logger = loggerFactory.getLogger(ServerRunnerImpl.class);
	}

	public void unbindLogger(LoggerFactory logService) {
		this.logger = null;
	}

	@Reference(cardinality = ReferenceCardinality.AT_LEAST_ONE)
	public void bindServerHandler(BackendLauncher serverHandler) {
		logger.info(String.format(REGISTERED, serverHandler.toString()));
		this.serverHandler = serverHandler;

	}

	public void unbindServerHandler(BackendLauncher serverHandler) {
		this.serverHandler = null;
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindServerRequestHandler(ServerRequestHandler serverRequestHandler, Map<String, String> context) {
		logger.debug(String.format(REGISTERED, serverRequestHandler.getClass().getName()));
		String requestHandlerId = context.get(LicensingRequests.HANDLER);
		if (requestHandlerId != null) {
			requestHandlers.put(requestHandlerId, serverRequestHandler);
			serverHandler.addServerRequestHandler(serverRequestHandler);
		} else {
			logger.error(REQUEST_HANDLER_NOT_FOUND + serverRequestHandler.toString());
		}
	}

	public void unbindServerRequestHandler(ServerRequestHandler serverRequestHandler, Map<String, String> context) {
		String requestHandlerId = context.get(LicensingRequests.HANDLER);
		if (requestHandlerId != null) {
			requestHandlers.remove(requestHandlerId, serverRequestHandler);
			serverHandler.remServerRequestHandler(serverRequestHandler);
		} else {
			logger.error(REQUEST_HANDLER_NOT_FOUND + serverRequestHandler.toString());
		}
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindServerRequestExecutor(BackendRequestDispatcher serverRequestExecutor, Map<String, String> context) {
		logger.debug(String.format(REGISTERED, serverRequestExecutor.getClass().getName()));
		String requestExecutorModeId = context.get(LicensingRequests.MODE);
		if (requestExecutorModeId != null) {
			serverRequestExecutor.setAccessModeId(requestExecutorModeId);
			if (!requestExecutors.containsKey(requestExecutorModeId)) {
				requestExecutors.put(requestExecutorModeId, serverRequestExecutor);
				for (Entry<String, ServerRequestHandler> entry : requestHandlers.entrySet()) {
					entry.getValue().registerRequestExecutor(serverRequestExecutor);
				}
			}
		} else {
			logger.error(REQUEST_HANDLER_NOT_FOUND + serverRequestExecutor.toString() + LicensingRequests.MODE
					+ requestExecutorModeId);
			;
		}
	}

	public void unbindServerRequestExecutor(BackendRequestDispatcher serverRequestExecutor, Map<String, String> context) {
		String requestExecutorModeId = context.get(LicensingRequests.MODE);
		if (requestExecutorModeId != null) {
			requestExecutors.remove(requestExecutorModeId, serverRequestExecutor);
			for (Entry<String, ServerRequestHandler> entry : requestHandlers.entrySet()) {
				entry.getValue().registerRequestExecutor(serverRequestExecutor);
			}
		}
	}

	public void bindExecutorByRequest(Map<String, ServerRequestHandler> mapHandlers,
			Map<String, Map<String, BackendRequestDispatcher>> mapRequestExecutors) {
		for (String keyExecutor : mapRequestExecutors.keySet()) {
			Map<String, BackendRequestDispatcher> mapExecutors = mapRequestExecutors.get(keyExecutor);
			ServerRequestHandler requestHandler = mapHandlers.get(keyExecutor);

			for (Entry<String, BackendRequestDispatcher> iter : mapExecutors.entrySet()) {
				requestHandler.registerRequestExecutor(iter.getValue());
			}
		}
	}

	public void bindHandlerByServer(BackendLauncher serverHandler,
			Map<String, ServerRequestHandler> serverRequestHandlers) {
		for (ServerRequestHandler requestHandler : serverRequestHandlers.values()) {
			serverHandler.addServerRequestHandler(requestHandler);
		}
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE)
	public void bindServerRequestActions(BackendActionExecutor action, Map<String, String> context) {
		logger.debug(String.format(REGISTERED, action.getClass().getName()));

		String actionId = context.get(LicensingRequests.ACTION);
		if (actionId != null) {
			if (!this.requestActions.containsKey(actionId)) {
				this.requestActions.put(actionId, action);
				requestExecutors.values().stream().forEach(a -> a.setRequestAction(requestActions));
			}
		}
	}

	public void unbindServerRequestActions(BackendActionExecutor action, Map<String, String> context) {
		logger.debug(action.getClass().getName());

		String actionId = context.get(LicensingRequests.ACTION);
		if (actionId != null) {
			if (this.requestActions.containsKey(actionId)) {
				this.requestActions.remove(actionId, action);
				requestExecutors.values().stream().forEach(a -> a.setRequestAction(requestActions));
			}
		}
	}
}
