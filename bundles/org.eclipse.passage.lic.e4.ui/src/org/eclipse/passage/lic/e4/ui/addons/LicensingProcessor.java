/*****************************************************************************************
 * Copyright (c) ArSysOp 2018, 2020
 *****************************************************************************************/
package org.eclipse.passage.lic.e4.ui.addons;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MAddon;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.MApplicationFactory;

@Deprecated
public class LicensingProcessor {

	@Inject
	@Execute
	public void execute(MApplication application) {
		ensureAddon(application);
	}

	private void ensureAddon(MApplication application) {
		String addonId = LicensingAddon.class.getName();
		List<MAddon> addons = application.getAddons();
		for (MAddon addon : addons) {
			if (addonId.equals(addon.getElementId())) {
				// our addon was found
				return;
			}
		}

		MAddon addon = MApplicationFactory.INSTANCE.createAddon();
		addon.setContributionURI(
				"bundleclass://org.eclipse.passage.lic.e4.ui/org.eclipse.passage.lic.e4.ui.addons.LicensingAddon"); //$NON-NLS-1$
		addon.setElementId(addonId);
		addons.add(addon);
	}

}
