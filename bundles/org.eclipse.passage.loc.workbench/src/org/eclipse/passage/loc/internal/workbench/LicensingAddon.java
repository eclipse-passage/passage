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
package org.eclipse.passage.loc.internal.workbench;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.access.AccessManager;
import org.osgi.framework.Version;
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
	public void applicationStarted(@UIEventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) Event event, MApplication application) {
		String brandingName = applicationContext.getBrandingName();
		List<MWindow> children = application.getChildren();
		for (MWindow window : children) {
			window.setLabel(brandingName);
		}
		String productId = applicationContext.getBrandingId();
		Version version = applicationContext.getBrandingBundle().getVersion();
		StringBuilder sb = new StringBuilder();
		sb.append(version.getMajor()).append('.');
		sb.append(version.getMinor()).append('.');
		sb.append(version.getMicro());
		String productVersion = sb.toString();
		LicensingConfiguration configuration = LicensingConfigurations.create(productId, productVersion);
		accessManager.executeAccessRestrictions(configuration);
	}

}
