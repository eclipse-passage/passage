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
package org.eclipse.passage.lbc.test;

import java.util.Map;

import org.eclipse.passage.lbc.api.BackendLauncher;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.base.LicensingResults;
import org.osgi.service.component.annotations.Component;

@Component
public class ServerLauncherComponentOne implements BackendLauncher {

	@Override
	public LicensingResult launch(Map<String, Object> arguments) {
		return LicensingResults.createOK("Test component launch"); //$NON-NLS-1$
	}

	@Override
	public LicensingResult terminate() {
		return LicensingResults.createOK("Test component terminate"); //$NON-NLS-1$
	}

}
