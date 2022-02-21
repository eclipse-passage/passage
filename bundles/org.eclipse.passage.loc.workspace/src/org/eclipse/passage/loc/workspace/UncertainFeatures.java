package org.eclipse.passage.loc.workspace;

import java.util.List;

import org.eclipse.passage.lic.equinox.io.InstallationPath;
import org.eclipse.passage.loc.internal.api.workspace.Features;
import org.eclipse.passage.loc.internal.api.workspace.ResourceHandle;

final class UncertainFeatures implements Features {

	@Override
	public List<ResourceHandle> all() {
		return new LoadResourceSet(//
				xmi, //
				new InstallationPath(), //
				"features" //$NON-NLS-1$
		).load();
	}

	@Override
	public void memento(List<String> locations) {
		new StoreResourceSet(//
				new InstallationPath(), //
				"features" //$NON-NLS-1$
		).store(locations);
	}

}
