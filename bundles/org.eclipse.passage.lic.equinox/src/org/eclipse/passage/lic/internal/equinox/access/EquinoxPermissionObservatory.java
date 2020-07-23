/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.equinox.access;

import static org.eclipse.passage.lic.base.LicensingResults.createEvent;

import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lic.api.LicensingReporter;
import org.eclipse.passage.lic.api.access.AccessEvents;
import org.eclipse.passage.lic.api.access.FeaturePermission;
import org.eclipse.passage.lic.base.SystemReporter;
import org.eclipse.passage.lic.internal.base.observatory.CheckSchedule;
import org.eclipse.passage.lic.internal.base.permission.BasePermissionObservatory;
import org.eclipse.passage.lic.internal.base.permission.LimitedPermission;
import org.eclipse.passage.lic.internal.base.permission.PermissionObservatory;
import org.eclipse.passage.lic.internal.equinox.EquinoxEvents;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

/**
 * OSGi-driven implementation of {@linkplain PermissionObservatory}.
 * 
 * @since 0.6
 */
@SuppressWarnings("restriction")
//FIXME: also listen event "feature-is-not-used-anymore" fired by the product under licensing-> call 'observatory.forget(feature)` 
//FIXME #553424 - Program under licensing should be able to cause leased FeaturePermission destruction
@Component(property = { EventConstants.EVENT_TOPIC + "=" + AccessEvents.CONDITIONS_EVALUATED,
		"observatory.schedule:Integer=600" })
public final class EquinoxPermissionObservatory implements EventHandler, PermissionObservatory {

	private BasePermissionObservatory observatory;
	private LicensingReporter eventBus = SystemReporter.INSTANCE;

	@Activate
	public void activate(Map<String, Object> config) {
		int schedule = (Integer) config.get("observatory.schedule"); //$NON-NLS-1$
		observatory = new BasePermissionObservatory(new CheckSchedule(schedule, ChronoUnit.SECONDS),
				this::fireExpiration);
		observatory.open();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleEvent(Event event) {
		Iterable<FeaturePermission> payload = (Iterable<FeaturePermission>) event
				.getProperty(EquinoxEvents.PROPERTY_DATA);
		observatory.watch(payload);
	}

	@Reference
	public void bindLicensingReporter(LicensingReporter reporter) {
		eventBus = reporter;
	}

	public void unbindLicensingReporter(LicensingReporter reporter) {
		if (eventBus == reporter) {
			eventBus = SystemReporter.INSTANCE;
		}
	}

	private void fireExpiration(Set<LimitedPermission> expired) {
		eventBus.postResult(createEvent(AccessEvents.PERMISSIONS_EXPIRED, expired));
	}

}
