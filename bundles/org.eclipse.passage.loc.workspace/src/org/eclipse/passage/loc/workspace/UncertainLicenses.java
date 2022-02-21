package org.eclipse.passage.loc.workspace;

import java.util.List;

import org.eclipse.passage.lic.equinox.io.InstallationPath;
import org.eclipse.passage.loc.internal.api.workspace.Licenses;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;

final class UncertainLicenses implements Licenses {

	@Override
	public List<ResourceHandle> all() {
		return new LoadResourceSet(//
				xmi, //
				new InstallationPath(), //
				"licenses" //$NON-NLS-1$
		).load();
	}

	@Override
	public void memento(List<String> locations) {
		new StoreResourceSet(//
				new InstallationPath(), //
				"licenses" //$NON-NLS-1$
		).store(locations);
	}

}
