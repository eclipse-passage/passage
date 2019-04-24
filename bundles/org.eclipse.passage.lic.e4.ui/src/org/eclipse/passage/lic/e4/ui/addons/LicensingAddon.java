/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.e4.ui.addons;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.access.AccessManager;
import org.eclipse.passage.lic.equinox.ApplicationConfigurations;
import org.osgi.service.event.Event;

public class LicensingAddon {

	private final IApplicationContext applicationContext;
	private final AccessManager accessManager;

	@Inject
	public LicensingAddon(IApplicationContext applicationContext, AccessManager accessManager) {
		this.applicationContext = applicationContext;
		this.accessManager = accessManager;
	}

	@Inject
	@Optional
	public void applicationStarted(
			@SuppressWarnings("unused") @UIEventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) Event event) {
		LicensingConfiguration configuration = ApplicationConfigurations.getLicensingConfiguration(applicationContext);
		accessManager.executeAccessRestrictions(configuration);
	}

}
