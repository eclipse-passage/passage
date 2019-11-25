/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
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
package org.eclipse.passage.lic.internal.equinox.access;

import static org.eclipse.passage.lic.base.LicensingResults.createEvent;

import java.util.List;
import java.util.Set;

import org.eclipse.passage.lic.api.LicensingReporter;
import org.eclipse.passage.lic.api.access.AccessEvents;
import org.eclipse.passage.lic.api.access.FeaturePermission;
import org.eclipse.passage.lic.base.SystemReporter;
import org.eclipse.passage.lic.internal.base.permission.LimitedPermission;
import org.eclipse.passage.lic.internal.base.permission.observatory.GuardedObservatory;
import org.eclipse.passage.lic.internal.equinox.EquinoxEvents;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

@SuppressWarnings("restriction")
//FIXME: also listen event "feature-is-not-used-anymore" fired by the product under licensing-> call 'observatory.forget(feature)` 
//FIXME #553424 - Program under licensing should be able to cause leased FeaturePermission destruction
@Component(property = EventConstants.EVENT_TOPIC + "=" + AccessEvents.CONDITIONS_EVALUATED)
public class PermissionObservatory implements EventHandler {

	private final GuardedObservatory<LimitedPermission> observatory;
	private LicensingReporter eventBus = SystemReporter.INSTANCE;

	public PermissionObservatory() {
		this.observatory = new GuardedObservatory<LimitedPermission>(10 * 60, this::fireExpiration);
	}

	@Activate
	void activate() {
		observatory.open();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleEvent(Event event) {
		List<FeaturePermission> payload = (List<FeaturePermission>) event.getProperty(EquinoxEvents.PROPERTY_DATA);
		payload.stream() //
				.map(LimitedPermission::new) //
				.forEach(limited -> observatory.watch(limited));
	}

	@Reference
	public void bindLicensingReporter(LicensingReporter reporter) {
		this.eventBus = reporter;
	}

	public void unbindLicensingReporter(LicensingReporter reporter) {
		if (this.eventBus == reporter) {
			this.eventBus = SystemReporter.INSTANCE;
		}
	}

	private void fireExpiration(Set<LimitedPermission> expired) {
		eventBus.postResult(createEvent(AccessEvents.PERMISSIONS_EXPIRED, expired));
	}

}
