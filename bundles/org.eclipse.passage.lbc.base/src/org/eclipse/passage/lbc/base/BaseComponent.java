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
package org.eclipse.passage.lbc.base;

import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

public abstract class BaseComponent {

	protected Logger logger;

	protected void bindLogger(LoggerFactory loggerFactory) {
		logger = loggerFactory.getLogger(this.getClass().getName());
	}

	protected void unbindLogger(LoggerFactory loggerFactory) {
		logger = null;
	}
}
