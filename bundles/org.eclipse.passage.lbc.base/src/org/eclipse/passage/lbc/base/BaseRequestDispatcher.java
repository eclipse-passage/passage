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
import java.util.Optional;

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
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.api.conditions.UserRole;
import org.eclipse.passage.lic.internal.net.LicensingAction;
import org.eclipse.passage.lic.internal.net.LicensingRole;

/**
 * Base implementation for {@link BackendRequestDispatcher}
 * 
 * @since 0.5.0
 *
 */
public class BaseRequestDispatcher implements BackendRequestDispatcher {

	private LicensingReporter licensingReporter = SystemReporter.INSTANCE;

	private String roleId;
	private Map<String, BackendActionExecutor> actionExecutors = new HashMap<>();

	protected void activate(Map<String, Object> properties) {
		roleId = new LicensingRole(properties).get().get().name();
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
		new LicensingAction(properties).get().ifPresent(a -> actionExecutors.put(a.name(), executor));
	}

	protected void unbindBackendActionExecutor(BackendActionExecutor executor, Map<String, Object> properties) {
		new LicensingAction(properties).get().ifPresent(a -> actionExecutors.remove(a.name(), executor));
	}

	@Override
	public boolean canDispatchRequest(HttpServletRequest baseRequest) {
		return new LicensingRole(s -> new UserRole.Of(baseRequest.getParameter(s))).get()//
				.map(UserRole::name)//
				.filter(roleId::equals)//
				.isPresent();
	}

	@Override
	public void dispatchRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// FIXME: add authentication here
		Optional<BackendActionExecutor> optional = new LicensingAction(
				s -> new ConditionAction.Of(request.getParameter(s))).get()//
						.flatMap(x -> Optional.ofNullable(actionExecutors.get(x.name())));
		if (optional.isPresent()) {
			LicensingResult execution = optional.get().executeAction(request, response);
			licensingReporter.logResult(execution);
		} else {
			String message = String.format(BaseMessages.BaseRequestDispatcher_e_executor_not_available,
					request.getParameterMap());
			licensingReporter.logResult(LicensingResults.createError(message, getClass().getName()));
			response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, message);
		}
	}

}
