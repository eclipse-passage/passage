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

import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lbc.server.launcher.internal.ApplicationLauncher;
import org.junit.Before;
import org.junit.Test;

public class ApplicationLauncherTest {

	private FakeBackendLauncher fakeBackendLauncher = new FakeBackendLauncher();
	private ApplicationLauncher applicationLauncher = new ApplicationLauncher();

	@Before
	public void init() {
		applicationLauncher.bind(fakeBackendLauncher);
	}

	@Test
	public void launch() {
		applicationLauncher.activate();
		assertTrue(fakeBackendLauncher.wasLaunched());
	}

	@Test
	public void terminate() {
		applicationLauncher.deactivate();
		assertTrue(fakeBackendLauncher.wasTerminated());
	}

}