/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.lbc.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.passage.lbc.runtime.BackendActionExecutor;
import org.eclipse.passage.lbc.runtime.BackendRequestDispatcher;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.SystemReporter;
import org.eclipse.passage.lic.net.LicensingRequests;
import org.eclipse.passage.lic.runtime.LicensingReporter;
import org.eclipse.passage.lic.runtime.LicensingResult;

public class BaseRequestDispatcher implements BackendRequestDispatcher {

	private LicensingReporter licensingReporter = SystemReporter.INSTANCE;

	private String modeId;
	private static Map<String, BackendActionExecutor> actionExecutors = new HashMap<>();

	protected void activate(Map<String, Object> properties) {
		modeId = String.valueOf(properties.get(LicensingRequests.MODE));
	}

	protected void bindLicensingReporter(LicensingReporter reporter) {
		this.licensingReporter = reporter;
	}

	protected void unbindLicensingReporter(LicensingReporter reporter) {
		if (this.licensingReporter == reporter) {
			this.licensingReporter = SystemReporter.INSTANCE;
		}
	}

	protected void bindBackendActionExecutor(BackendActionExecutor executor, Map<String, Object> properties) {
		String actionId = String.valueOf(properties.get(LicensingRequests.ACTION));
		actionExecutors.put(actionId, executor);
	}

	protected void unbindBackendActionExecutor(BackendActionExecutor executor, Map<String, Object> properties) {
		String actionId = String.valueOf(properties.get(LicensingRequests.ACTION));
		actionExecutors.remove(actionId, executor);
	}

	@Override
	public boolean canDispatchRequest(HttpServletRequest baseRequest) {
		String requestMode = baseRequest.getParameter(LicensingRequests.MODE);
		return Objects.equals(modeId, requestMode);
	}

	@Override
	public void dispatchRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// FIXME: add authentication here
		String actionId = request.getParameter(LicensingRequests.ACTION);
		BackendActionExecutor requestAction = actionExecutors.get(actionId);
		if (requestAction != null) {
			LicensingResult execution = requestAction.executeAction(request, response);
			licensingReporter.logResult(execution);
		} else {
			String message = String.format("Action executor not available for id %s", requestAction);
			licensingReporter.logResult(LicensingResults.createError(message, getClass().getName()));
			response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, message);
		}
	}

}
