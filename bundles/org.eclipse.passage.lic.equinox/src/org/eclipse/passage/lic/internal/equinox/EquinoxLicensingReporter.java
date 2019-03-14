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
package org.eclipse.passage.lic.internal.equinox;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.passage.lic.equinox.LicensingEquinox;
import org.eclipse.passage.lic.runtime.LicensingReporter;
import org.eclipse.passage.lic.runtime.LicensingResult;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

@Component
public class EquinoxLicensingReporter implements LicensingReporter {

	private EventAdmin eventAdmin;

	@Reference
	public void bindEventAdmin(EventAdmin admin) {
		this.eventAdmin = admin;
	}

	public void unbindEventAdmin(EventAdmin admin) {
		this.eventAdmin = admin;
	}

	@Override
	public void logResult(LicensingResult result) {
		Bundle bundle = FrameworkUtil.getBundle(getClass());
		ILog log = Platform.getLog(bundle);
		IStatus status = LicensingEquinox.toStatus(result);
		log.log(status);
	}

	@Override
	public void postResult(LicensingResult result) {
		Event event = EquinoxEvents.extractEvent(result);
		if (event != null) {
			eventAdmin.postEvent(event);
		} else {
			logResult(result);
		}
	}

	@Override
	public void sendResult(LicensingResult result) {
		Event event = EquinoxEvents.extractEvent(result);
		if (event != null) {
			eventAdmin.sendEvent(event);
		} else {
			logResult(result);
		}
	}

}
