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

import org.eclipse.passage.lic.net.LicensingRequests;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

import org.eclipse.passage.lbc.server.ServerHandler;
import org.eclipse.passage.lbc.server.ServerRequestAction;
import org.eclipse.passage.lbc.server.ServerRequestExecutor;
import org.eclipse.passage.lbc.server.ServerRequestHandler;
import org.eclipse.passage.lbc.server.ServerRunner;

public class ServerRunnerImpl implements ServerRunner {

	private static final String REGISTERED = "[Registered]  %s";
	private static final String REQUEST_HANDLER_NOT_FOUND = "Request handler not registrated for component ";

	private ServerHandler serverHandler;
	private Map<String, ServerRequestHandler> requestHandlers = new HashMap<>();
	private Map<String, ServerRequestExecutor> requestExecutors = new HashMap<>();
	private Map<String, ServerRequestAction> requestActions = new HashMap<>();

	private Logger logger;

	public void activate() {
		if (serverHandler != null) {
			serverHandler.launch();
		} else {
			logger.error("Server not registrated");
		}
	}

	public void bindLogger(LoggerFactory loggerFactory) {
		this.logger = loggerFactory.getLogger(ServerRunnerImpl.class);
	}

	public void unbindLogger(LoggerFactory logService) {
		this.logger = null;
	}

	public void bindServerHandler(ServerHandler serverHandler) {
		logger.info(String.format(REGISTERED, serverHandler.toString()));
		this.serverHandler = serverHandler;

	}

	public void unbindServerHandler(ServerHandler serverHandler) {
		this.serverHandler = null;
	}

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

	public void bindServerRequestExecutor(ServerRequestExecutor serverRequestExecutor, Map<String, String> context) {
		logger.debug(String.format(REGISTERED, serverRequestExecutor.getClass().getName()));
		String requestExecutorModeId = context.get(LicensingRequests.MODE);
		if (requestExecutorModeId != null) {
			serverRequestExecutor.setAccessModeId(requestExecutorModeId);
			if (!requestExecutors.containsKey(requestExecutorModeId)) {
				requestExecutors.put(requestExecutorModeId, serverRequestExecutor);
				for (Entry<String, ServerRequestHandler> entry : requestHandlers.entrySet()) {
					entry.getValue().addRequestExecutor(serverRequestExecutor);
				}
			}
		} else {
			logger.error(REQUEST_HANDLER_NOT_FOUND + serverRequestExecutor.toString()
					+ LicensingRequests.MODE + requestExecutorModeId);
			;
		}
	}

	public void unbindServerRequestExecutor(ServerRequestExecutor serverRequestExecutor, Map<String, String> context) {
		String requestExecutorModeId = context.get(LicensingRequests.MODE);
		if (requestExecutorModeId != null) {
			requestExecutors.remove(requestExecutorModeId, serverRequestExecutor);
			for (Entry<String, ServerRequestHandler> entry : requestHandlers.entrySet()) {
				entry.getValue().addRequestExecutor(serverRequestExecutor);
			}
		}
	}

	public void bindExecutorByRequest(Map<String, ServerRequestHandler> mapHandlers,
			Map<String, Map<String, ServerRequestExecutor>> mapRequestExecutors) {
		for (String keyExecutor : mapRequestExecutors.keySet()) {
			Map<String, ServerRequestExecutor> mapExecutors = mapRequestExecutors.get(keyExecutor);
			ServerRequestHandler requestHandler = mapHandlers.get(keyExecutor);

			for (Entry<String, ServerRequestExecutor> iter : mapExecutors.entrySet()) {
				requestHandler.addRequestExecutor(iter.getValue());
			}
		}
	}

	public void bindHandlerByServer(ServerHandler serverHandler,
			Map<String, ServerRequestHandler> serverRequestHandlers) {
		for (ServerRequestHandler requestHandler : serverRequestHandlers.values()) {
			serverHandler.addServerRequestHandler(requestHandler);
		}
	}

	public void bindServerRequestActions(ServerRequestAction action, Map<String, String> context) {
		logger.debug(String.format(REGISTERED, action.getClass().getName()));

		String actionId = context.get(LicensingRequests.ACTION);
		if (actionId != null) {
			if (!this.requestActions.containsKey(actionId)) {
				this.requestActions.put(actionId, action);
				requestExecutors.values().stream().forEach(a -> a.setRequestAction(requestActions));
			}
		}
	}

	public void unbindServerRequestActions(ServerRequestAction action, Map<String, String> context) {
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
