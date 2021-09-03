/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.e4.ui.addons;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MAddon;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.MApplicationFactory;

/**
 * @since 2.1
 */
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
