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
package org.eclipse.passage.lbc.server;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServerRequestExecutor {

	public void executeRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException;

	public void setRequestAction(Map<String, ServerRequestAction> mapActions);

	void setAccessModeId(String setAccessModeId);

	boolean checkAccesstMode(HttpServletRequest baseRequest);

}
