package org.eclipse.passage.loc.internal.licenses.core;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

import org.eclipse.passage.lic.internal.equinox.ServiceExtensions;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicenseAccess;
import org.eclipse.passage.lic.licenses.model.api.FloatingLicensePack;
import org.eclipse.passage.lic.licenses.model.api.PersonalLicensePack;

final class ContributedLicensePackIssueListener implements LicensePackIssueListener {

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
		return new ServiceExtensions<LicensePackIssueListener>(//
				"org.eclipse.passage.loc.licenses.core", //$NON-NLS-1$
				"issue", //$NON-NLS-1$
				LicensePackIssueListener.class)//
						.get();
	}

}
