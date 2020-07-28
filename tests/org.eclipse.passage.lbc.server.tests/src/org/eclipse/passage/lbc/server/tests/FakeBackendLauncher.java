package org.eclipse.passage.lbc.server.tests;

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
