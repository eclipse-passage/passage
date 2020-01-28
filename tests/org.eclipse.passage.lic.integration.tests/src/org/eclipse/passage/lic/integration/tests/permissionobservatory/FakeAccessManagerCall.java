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
package org.eclipse.passage.lic.integration.tests.permissionobservatory;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.passage.lic.api.access.AccessManager;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.internal.base.permission.PermissionObservatory;
import org.eclipse.passage.lic.internal.equinox.access.EquinoxAccessManager;
import org.eclipse.passage.lic.internal.equinox.access.EquinoxPermissionObservatory;
import org.osgi.service.event.Event;

/**
 * <p>
 * Main testing unit.
 * </p>
 * 
 * <ul>
 * Here we
 * <li>fake the service responsible for a condition evaluation with our own
 * {@linkplain FakePermissionEmitter} - the instance is implanted into actual
 * {@linkplain AccessManager} service available in OSGi container. This emitter
 * leases 2-second-TTL permission for each condition.</li>
 * <li>use our own {@code event bus} instead of {@code Event Admin} of OSGi to
 * post and handle events</li>
 * <li>emulate {@linkplain LicensingCondition} with our own implementation to be
 * source for
 * {@linkplain AccessManager#evaluateConditions(org.eclipse.passage.lic.api.LicensingConfiguration, Iterable)}
 * </li>
 * <li>reconfigure {@linkplain PermissionObservatory} to a schedule proper for
 * test environments (seconds instead of minutes for production)</li>
 * <li>finally, call actual {@linkplain AccessManager#evaluateConditions}</li>
 * <ul>
 * <p/>
 * <ul>
 * We expect, that
 * <li>during the call {@linkplain AccessManager} posts
 * {@code condition_evaluated} event to the bus</li>
 * <li>our event bus resends it to {@linkplain EquinoxPermissionObservatory}
 * component's event handling function</li>
 * <li>the leased permission is kept under the observatory's eyes and is
 * regularly checked on expiration</li>
 * <li>after the permission TTL is over, the observatory fires
 * {@code permission_expired} event, which is tracked by our event bus</li>
 * </ul>
 * <p>
 * As event bus is encapsulated here, we also can say, if set of {@code leased
 * permissions} is precisely equal to the set of {@code expired permissions}.
 * </p>
 * 
 * @since 0.6
 */
@SuppressWarnings("restriction")
class FakeAccessManagerCall {
	private final EquinoxAccessManager accessManager;
	private final EquinoxPermissionObservatory observatory;
	private final LongMemoryEventAdmin eventBus;

	FakeAccessManagerCall(AccessManager accessManager, PermissionObservatory observatory) {
		this.accessManager = (EquinoxAccessManager) accessManager;
		this.observatory = (EquinoxPermissionObservatory) observatory;
		eventBus = new LongMemoryEventAdmin(this::handleLeasing);
	}

	void reinstall() {
		accessManager.bindLicensingReporter(eventBus);
		observatory.bindLicensingReporter(eventBus);
		accessManager.bindPermissionEmitter( //
				new FakePermissionEmitter(), //
				new ConditionType("time").map()); //$NON-NLS-1$
		observatory.activate(new GuardSchedule(1).map());
	}

	void leasePermissions(int amount) {
		accessManager.evaluateConditions(new FakeConfiguration(), conditions(amount));
	}

	private Iterable<LicensingCondition> conditions(int amount) {
		return IntStream.range(0, amount) //
				.mapToObj(i -> new FakeCondition()) //
				.collect(Collectors.toList());
	}

	private void handleLeasing(Event leaseEvent) {
		observatory.handleEvent(leaseEvent);
	}

	boolean complete() {
		return eventBus.even();
	}
}
