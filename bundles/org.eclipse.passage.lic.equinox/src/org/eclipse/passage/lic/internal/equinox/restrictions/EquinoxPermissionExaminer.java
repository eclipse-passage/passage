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
package org.eclipse.passage.lic.internal.equinox.restrictions;

import org.eclipse.passage.lic.api.access.PermissionExaminer;
import org.eclipse.passage.lic.base.access.BasePermissionExaminer;
import org.eclipse.passage.lic.internal.equinox.EquinoxEvents;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

@Component
public class EquinoxPermissionExaminer extends BasePermissionExaminer implements PermissionExaminer {

	private EventAdmin eventAdmin;
	
	@Reference
	public void bindEventAdmin(EventAdmin admin) {
		this.eventAdmin = admin;
	}
	
	public void unbindEventAdmin(EventAdmin admin) {
		this.eventAdmin = admin;
	}

	@Override
	protected void postEvent(String topic, Object data) {
		Event event = EquinoxEvents.createEvent(topic, data);
		eventAdmin.postEvent(event);
	}

}
