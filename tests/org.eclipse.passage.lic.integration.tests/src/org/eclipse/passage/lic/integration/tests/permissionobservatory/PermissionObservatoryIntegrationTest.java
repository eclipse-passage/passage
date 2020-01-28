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

import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.api.access.AccessManager;
import org.eclipse.passage.lic.api.access.FeaturePermission;
import org.eclipse.passage.lic.internal.base.permission.PermissionObservatory;
import org.eclipse.passage.lic.internal.equinox.access.EquinoxPermissionObservatory;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * 
 * <p>
 * Test {@linkplain EquinoxPermissionObservatory} functioning.
 * </p>
 * 
 * <p>
 * It's a long-running test. With starting up and warming it takes ~25 seconds
 * on a developer's machine.
 * </p>
 * 
 * @since 0.6
 */
@SuppressWarnings("restriction")
public class PermissionObservatoryIntegrationTest {

	private FakeAccessManagerCall fake;

	@Before
	public void startup() {
		Bundle bundle = FrameworkUtil.getBundle(PermissionObservatoryIntegrationTest.class);
		BundleContext context = bundle.getBundleContext();
		ServiceReference<AccessManager> accessManager = context.getServiceReference(AccessManager.class);
		ServiceReference<PermissionObservatory> observatory = context.getServiceReference(PermissionObservatory.class);
		fake = new FakeAccessManagerCall(context.getService(accessManager), context.getService(observatory));
		fake.reinstall();
	}

	/**
	 * <p>
	 * The test checks if a {@code condition_evaluated} event (meaning that a
	 * {@linkplain FeaturePermission} is leased) is properly handled by
	 * {@linkplain EquinoxPermissionObservatory}.
	 * </p>
	 * 
	 * <p>
	 * By means of custom event bus ({@linkplain LongMemoryEventAdmin}) at the end
	 * of the day we can say exactly whether each and every leased permission is
	 * properly expired or not.
	 * </p>
	 * 
	 * @since 0.6
	 */
	@Test
	public void testObservatoryTracksExpiration() {
		fake.leasePermissions(4);
		assertAllAreExpired(2000); // permissions are leased with 2 seconds TTL
		assertTrue("Some expired permissions are still active", fake.complete()); //$NON-NLS-1$
	}

	private void assertAllAreExpired(int delay) {
		waitABit(delay);
		for (int i = 0; i < 5; i++) {
			if (fake.complete()) {
				return;
			}
			waitABit(500);
		}

	}

	private void waitABit(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
