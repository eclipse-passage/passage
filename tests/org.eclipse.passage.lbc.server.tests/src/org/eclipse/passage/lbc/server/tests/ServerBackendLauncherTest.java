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

package org.eclipse.passage.lbc.server.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.passage.lbc.api.BackendCluster;
import org.eclipse.passage.lbc.api.BackendLauncher;
import org.eclipse.passage.lic.api.LicensingResult;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class ServerBackendLauncherTest {

	private static final int DEFAULT_CAPACITY = 2;
	private BundleContext context;

	@Before
	public void getContext() {
		Bundle bundle = FrameworkUtil.getBundle(ServerBackendLauncherTest.class);
		context = bundle.getBundleContext();
	}

	@Test
	public void serverBackendLauncherTest() {
		assertNotNull(context);
		ServiceReference<BackendCluster> serviceReference = context.getServiceReference(BackendCluster.class);
		assertNotNull(serviceReference);
		BackendCluster service = context.getService(serviceReference);
		assertNotNull(service);
		Iterable<BackendLauncher> backendLaunchers = service.getBackendLaunchers();
		assertNotNull(backendLaunchers);
		List<BackendLauncher> launchers = new ArrayList<>();
		backendLaunchers.forEach(launchers::add);
		assertNotNull(launchers);
		assertFalse(launchers.isEmpty());
		assertTrue(launchers.size() >= DEFAULT_CAPACITY);

		launchers.stream().forEach(launcher -> {
			LicensingResult result = launcher.launch(Collections.emptyMap());
			assertTrue(result.getSeverity() == LicensingResult.OK);
		});

		launchers.stream().forEach(launcher -> {
			LicensingResult result = launcher.terminate();
			assertTrue(result.getSeverity() == LicensingResult.OK);
		});

	}
}
