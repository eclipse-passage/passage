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
package org.eclipse.passage.lbc.runtime;

import java.util.Map;

import org.eclipse.passage.lic.runtime.LicensingResult;

public interface BackendLauncher {

	LicensingResult launch(Map<String, Object> arguments);

	LicensingResult terminate();

	void addServerRequestHandler(ServerRequestHandler handler);

	void remServerRequestHandler(ServerRequestHandler handler);

}
