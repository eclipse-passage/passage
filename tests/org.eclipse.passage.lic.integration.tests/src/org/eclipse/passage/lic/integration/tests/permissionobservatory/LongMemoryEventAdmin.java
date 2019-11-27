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
package org.eclipse.passage.lic.integration.tests.permissionobservatory;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.api.LicensingReporter;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.access.AccessEvents;
import org.eclipse.passage.lic.internal.base.permission.LimitedPermission;
import org.eclipse.passage.lic.internal.equinox.EquinoxEvents;
import org.osgi.service.event.Event;

/**
 * <p>
 * As <i>OSGi EventAdmin</i> is not functional (do not process event queue
 * before the main thread ends) here we emulate it's behavior through the memory
 * structures.
 * </p>
 * 
 * <p>
 * Mostly this bus keeps all incoming {@code post} requests in memory.
 * </p>
 * <p>
 * And for the purpose of the test we only must implement
 * {@link AccessEvents.CONDITIONS_EVALUATED} {@code post}s delivery to a hander.
 * We do this by means of preconfigured {@code onLease} handler.
 * </p>
 * 
 * @since 0.6
 */
@SuppressWarnings("restriction")
class LongMemoryEventAdmin implements LicensingReporter {

	private final Set<LimitedPermission> leased = new HashSet<>();
	private final Set<LimitedPermission> expired = new HashSet<>();
	private final Consumer<Event> onLease;

	LongMemoryEventAdmin(Consumer<Event> onLease) {
		this.onLease = onLease;
	}

	@Override
	public void postResult(LicensingResult result) {
		Event event = EquinoxEvents.extractEvent(result);
		String topic = event.getTopic();
		@SuppressWarnings("unchecked")
		Iterable<LimitedPermission> data = (Iterable<LimitedPermission>) event.getProperty(EquinoxEvents.PROPERTY_DATA);
		collectLeased(topic, data);
		collectExpired(topic, data);
		if (lease(event.getTopic())) {
			onLease.accept(event);
		}
	}

	@Override
	public void sendResult(LicensingResult result) {
		// do nothing: not needed for the test
	}

	@Override
	public void logResult(LicensingResult result) {
		// do nothing: not needed for the test
	}

	private void collectLeased(String topic, Iterable<LimitedPermission> data) {
		if (!lease(topic)) {
			return;
		}
		leased.addAll(set(data));
	}

	private void collectExpired(String topic, Iterable<LimitedPermission> data) {
		if (!AccessEvents.PERMISSIONS_EXPIRED.equals(topic)) {
			return;
		}
		expired.addAll(set(data));
	}

	private Set<LimitedPermission> set(Iterable<LimitedPermission> what) {
		return StreamSupport.stream(what.spliterator(), false).collect(Collectors.toSet());
	}

	private boolean lease(String topic) {
		return AccessEvents.CONDITIONS_EVALUATED.equals(topic);
	}

	boolean even() {
		return leased.equals(expired);
	}

}
