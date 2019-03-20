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
package org.eclipse.passage.lbc.base.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.passage.lbc.base.BaseComponent;
import org.eclipse.passage.lbc.runtime.ServerRequestAction;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LoggerFactory;

@Component
public class ValidateActionRequest extends BaseComponent implements ServerRequestAction {

	private static final String MSG_LOG = "Execute action class:";

	@Override
	@Reference
	protected void bindLogger(LoggerFactory loggerFactory) {
		// TODO Auto-generated method stub
		super.bindLogger(loggerFactory);
	}

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		if (logger != null) {
			logger.info(MSG_LOG + this.getClass().getName());
		}
		return false;
	}
}
