package org.eclipse.passage.loc.workspace;

import java.util.List;

import org.eclipse.passage.lic.equinox.io.InstallationPath;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;
import org.eclipse.passage.loc.internal.api.workspace.Users;

final class UncertainUsers implements Users {

	@Override
	public List<ResourceHandle> all() {
		return new LoadResourceSet(//
				xmi, //
				new InstallationPath(), //
				"users" //$NON-NLS-1$
		).load();
	}

	@Override
	public void memento(List<String> locations) {
		new StoreResourceSet(//
				new InstallationPath(), //
				"users" //$NON-NLS-1$
		).store(locations);
	}

}
