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
package org.eclipse.passage.lic.internal.e4.ui.addons;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.internal.equinox.ApplicationConfiguration;
import org.eclipse.passage.lic.internal.equinox.EquinoxPassage;
import org.osgi.service.event.Event;

@SuppressWarnings("restriction")
public final class E4LicensingAddon {

	private final IApplicationContext applicationContext;

	@Inject
	public E4LicensingAddon(IApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Inject
	@Optional
	public void applicationStarted(
			@SuppressWarnings("unused") @UIEventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) Event event) {
		LicensingConfiguration configuration = new ApplicationConfiguration(applicationContext).get();
		new EquinoxPassage().checkLicense(configuration.getProductIdentifier());
	}

}
