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
package org.eclipse.passage.lbc.base.request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.passage.lbc.base.BaseComponent;
import org.eclipse.passage.lbc.runtime.BackendActionExecutor;
import org.eclipse.passage.lbc.runtime.BackendRequestDispatcher;
import org.eclipse.passage.lic.net.LicensingRequests;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LoggerFactory;

@Component
public class ClientRequestExecutor extends BaseComponent implements BackendRequestDispatcher {

	private static Map<String, BackendActionExecutor> mapActionRequest = new HashMap<>();

	private static final String EXECUTION_ACTION_ERROR = "Execution action: [%s] result [FALSE]";
	private static final String RECIEVED_ACTION_TXT = "Recieved action id [%s]";
	private static final Object CLIENT_TRUSTED_VALUE = "12345678";

	private static final String RESPONSE_ERROR_UNTRUSTED = "Recieved unttrusted client";
	private static final String MSG_REQUEST_ACTION_NOT_FOUND_ERROR = "Action executor with id: [%s] not registered";

	private String accessModeId = "";

	@Override
	@Reference
	protected void bindLogger(LoggerFactory loggerFactory) {
		super.bindLogger(loggerFactory);
	}

	@Override
	public void dispatchRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String actionId = request.getParameter(LicensingRequests.ACTION);
		logger.info(String.format(RECIEVED_ACTION_TXT, actionId));
		if (clientRecognition(request)) {
			BackendActionExecutor requestAction = mapActionRequest.get(actionId);
			if (requestAction == null) {
				logger.info(String.format(MSG_REQUEST_ACTION_NOT_FOUND_ERROR, actionId));
				return;
			}
			if (requestAction.executeAction(request, response).getSeverity() != LicensingResult.OK) {
				logger.info(EXECUTION_ACTION_ERROR, requestAction.getClass().getName());
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		} else {
			logger.info(RESPONSE_ERROR_UNTRUSTED);
		}

	}

	public boolean clientRecognition(HttpServletRequest request) {
		String httpClientTrustId = request.getParameter(LicensingRequests.USER);
		if (CLIENT_TRUSTED_VALUE.equals(httpClientTrustId)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canDispatchRequest(HttpServletRequest baseRequest) {
		String requestAccessMode = baseRequest.getParameter(LicensingRequests.MODE);
		if (requestAccessMode != null && requestAccessMode.equals(accessModeId)) {
			return true;
		}
		return false;
	}

	@Override
	public void setRequestAction(Map<String, BackendActionExecutor> mapActions) {
		mapActionRequest.putAll(mapActions);
	}

	@Override
	public void setAccessModeId(String accessModeId) {
		this.accessModeId = accessModeId;
	}
}
