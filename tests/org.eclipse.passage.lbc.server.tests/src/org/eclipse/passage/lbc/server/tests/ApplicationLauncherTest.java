package org.eclipse.passage.lbc.server.tests;

import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lbc.server.launcher.ApplicationLauncher;
import org.junit.Before;
import org.junit.Test;

public class ApplicationLauncherTest {

	private ApplicationLauncher launcher;
	private FakeBackendLauncher fakeBackendLauncher = new FakeBackendLauncher();

	@Before
	public void init() {
		launcher = new ApplicationLauncher();
		launcher.bind(fakeBackendLauncher);
	}

	@Test
	public void launch() {
		assertTrue(fakeBackendLauncher.wasLaunched());
	}

	@Test
	public void terminate() {
		assertTrue(fakeBackendLauncher.wasTerminated());
	}

}