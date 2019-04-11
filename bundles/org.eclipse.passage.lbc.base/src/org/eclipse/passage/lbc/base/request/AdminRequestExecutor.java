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
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LoggerFactory;

@Component
public class AdminRequestExecutor extends BaseComponent implements BackendRequestDispatcher {

	private static final String MSG_REQUEST_ACTION_NOT_FOUND_ERROR = "Action id: %s not found";

	private static Map<String, BackendActionExecutor> mapActionRequest = new HashMap<>();

	@Override
	@Reference
	protected void bindLogger(LoggerFactory loggerFactory) {
		super.bindLogger(loggerFactory);
	}

	@Override
	public void dispatchRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String actionId = request.getParameter(LicensingRequests.ACTION);
		BackendActionExecutor requestAction = mapActionRequest.get(actionId);
		if (requestAction != null) {
			requestAction.executeAction(request, response);
		} else {
			logger.info(String.format(MSG_REQUEST_ACTION_NOT_FOUND_ERROR, requestAction));
		}
	}

	@Override
	public boolean canDispatchRequest(HttpServletRequest baseRequest) {
		String requestAccessMode = baseRequest.getParameter(LicensingRequests.MODE);
		if (requestAccessMode != null && requestAccessMode.equals("admin")) {
			return true;
		}
		return false;
	}

}
