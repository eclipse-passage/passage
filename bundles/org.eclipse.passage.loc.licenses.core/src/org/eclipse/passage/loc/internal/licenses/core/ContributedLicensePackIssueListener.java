package org.eclipse.passage.loc.internal.licenses.core;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;

final class ContributedLicensePackIssueListener implements LicensePackIssueListener {

	private final Logger log = LogManager.getLogger(getClass());
	private final List<LicensePackIssueListener> contributed;

	ContributedLicensePackIssueListener() {
		this.contributed = read();
	}

	@Override
	public void floating(FloatingLicensePack pack, Collection<FloatingLicenseAccess> configs, Path residence) {
		contributed.forEach(listener -> listener.floating(pack, configs, residence));

	}

	@Override
	public void personal(PersonalLicensePack license, Path residence) {
		contributed.forEach(listener -> listener.personal(license, residence));
	}

	private List<LicensePackIssueListener> read() {
		IExtension[] extensions = Platform.getExtensionRegistry()
				.getExtensionPoint("org.eclipse.passage.loc.licenses.core", "issue").getExtensions(); //$NON-NLS-1$//$NON-NLS-2$
		List<LicensePackIssueListener> found = new ArrayList<>();
		for (IExtension extension : extensions) {
			for (IConfigurationElement config : extension.getConfigurationElements()) {
				try {
					found.add((LicensePackIssueListener) config.createExecutableExtension("class")); //$NON-NLS-1$
				} catch (CoreException e) {
					log.error("failed to instanciate licence pack issue listener", e); //$NON-NLS-1$
					e.printStackTrace();
				}
			}
		}
		return found;
	}

}
