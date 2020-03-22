/*****************************************************************************************
 * Copyright (c) ArSysOp 2020
 *****************************************************************************************/
package org.eclipse.passage.lic.internal.e4.ui.addons;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MAddon;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.MApplicationFactory;

public final class E4LicensingProcessor {

	@Inject
	@Execute
	public void execute(MApplication application) {
		ensureAddon(application);
	}

	private void ensureAddon(MApplication application) {
		String id = E4LicensingAddon.class.getName();
		if (alreadyHas(application, id)) {
			return;
		}
		register(application, id);
	}

	private boolean alreadyHas(MApplication application, String id) {
		return application.getAddons().stream().anyMatch(a -> id.equals(a.getElementId()));
	}

	private void register(MApplication application, String id) {
		MAddon addon = MApplicationFactory.INSTANCE.createAddon();
		addon.setContributionURI("bundleclass://org.eclipse.passage.lic.e4.ui/" + id); //$NON-NLS-1$
		addon.setElementId(id);
		application.getAddons().add(addon);
	}

}
