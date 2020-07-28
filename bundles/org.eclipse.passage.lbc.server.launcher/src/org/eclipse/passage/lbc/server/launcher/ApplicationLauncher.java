package org.eclipse.passage.lbc.server.launcher;

import java.util.Collections;
import java.util.Map;

import org.eclipse.passage.lbc.api.BackendLauncher;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true)
public class ApplicationLauncher {

	private final Map<String, Object> launchArguments = Collections.emptyMap();

	private BackendLauncher server;

	@Activate
	public void activate() {
		server.launch(launchArguments);
	}

	@Deactivate
	public void deactivate() {
		server.terminate();
	}

	@Reference
	public void bind(BackendLauncher launcher) {
		this.server = launcher;
	}

}
