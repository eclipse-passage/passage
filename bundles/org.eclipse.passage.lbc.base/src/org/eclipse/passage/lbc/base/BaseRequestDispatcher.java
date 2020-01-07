/*******************************************************************************
 * Copyright (c) 2019-2020 ArSysOp
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
package org.eclipse.passage.lbc.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.passage.lbc.api.BackendActionExecutor;
import org.eclipse.passage.lbc.api.BackendRequestDispatcher;
import org.eclipse.passage.lbc.internal.base.i18n.BaseMessages;
import org.eclipse.passage.lic.api.LicensingReporter;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.SystemReporter;
import org.eclipse.passage.lic.net.LicensingNet;

/**
 * Base implementation for {@link BackendRequestDispatcher}
 * 
 * @since 0.5.0
 *
 */
public class BaseRequestDispatcher implements BackendRequestDispatcher {

	private LicensingReporter licensingReporter = SystemReporter.INSTANCE;

	private String roleId;
	private static Map<String, BackendActionExecutor> actionExecutors = new HashMap<>();

	protected void activate(Map<String, Object> properties) {
		roleId = String.valueOf(properties.get(LicensingNet.ROLE));
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
		String actionId = String.valueOf(properties.get(LicensingNet.ACTION));
		actionExecutors.put(actionId, executor);
	}

	protected void unbindBackendActionExecutor(BackendActionExecutor executor, Map<String, Object> properties) {
		String actionId = String.valueOf(properties.get(LicensingNet.ACTION));
		actionExecutors.remove(actionId, executor);
	}

	@Override
	public boolean canDispatchRequest(HttpServletRequest baseRequest) {
		String requestRole = baseRequest.getParameter(LicensingNet.ROLE);
		return Objects.equals(roleId, requestRole);
	}

	@Override
	public void dispatchRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// FIXME: add authentication here
		String actionId = request.getParameter(LicensingNet.ACTION);
		BackendActionExecutor requestAction = actionExecutors.get(actionId);
		if (requestAction != null) {
			LicensingResult execution = requestAction.executeAction(request, response);
			licensingReporter.logResult(execution);
		} else {
			String message = String.format(BaseMessages.BaseRequestDispatcher_e_executor_not_available, requestAction);
			licensingReporter.logResult(LicensingResults.createError(message, getClass().getName()));
			response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, message);
		}
	}

}
