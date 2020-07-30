/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lbc.server.launcher.internal.tests;

import java.util.Map;

import org.eclipse.passage.lbc.api.BackendLauncher;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.base.LicensingResults;

public class FakeBackendLauncher implements BackendLauncher {

	private boolean launched = false;
	private boolean terminated = false;

	public boolean wasLaunched() {
		return launched;
	}

	public boolean wasTerminated() {
		return terminated;
	}

	@Override
	public LicensingResult launch(Map<String, Object> arguments) {
		this.launched = true;
		return LicensingResults.createOK();
	}

	@Override
	public LicensingResult terminate() {
		this.terminated = true;
		return LicensingResults.createOK();
	}

}
