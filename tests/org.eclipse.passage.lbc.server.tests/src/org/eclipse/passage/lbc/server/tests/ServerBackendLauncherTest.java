/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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

package org.eclipse.passage.lbc.server.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.eclipse.passage.lbc.api.BackendLauncher;
import org.eclipse.passage.lic.api.LicensingResult;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class ServerBackendLauncherTest {

	private BundleContext context;

	@Before
	public void getContext() {
		Bundle bundle = FrameworkUtil.getBundle(ServerBackendLauncherTest.class);
		context = bundle.getBundleContext();
	}

	@Test
	public void serverBackendLauncherTest() {
		assertNotNull(context);
		ServiceReference<BackendLauncher> serviceReference = context.getServiceReference(BackendLauncher.class);
		assertNotNull(serviceReference);
		BackendLauncher launcher = context.getService(serviceReference);
		assertNotNull(launcher);

		LicensingResult launchResult = launcher.launch(Collections.emptyMap());
		assertTrue(launchResult.getSeverity() == LicensingResult.OK);

		LicensingResult terminateResult = launcher.terminate();
		assertTrue(terminateResult.getSeverity() == LicensingResult.OK);

	}
}
