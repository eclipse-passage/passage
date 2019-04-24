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
package org.eclipse.passage.lic.base.tests.io;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.passage.lic.api.io.KeyKeeper;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.io.BaseKeyKeeperRegistry;
import org.eclipse.passage.lic.base.io.NullKeyKeeper;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.junit.BeforeClass;
import org.junit.Test;

public class BaseKeyKeeperRegistryTest {

	private static final String P1 = "p1"; //$NON-NLS-1$
	private static final String V1 = "v1"; //$NON-NLS-1$
	private static final String V2 = "v2"; //$NON-NLS-1$

	private static final Map<String, Object> MAP1 = new HashMap<>();
	private static final Map<String, Object> MAP2 = new HashMap<>();

	private static final KeyKeeper KEEPER1 = new NullKeyKeeper();

	private static final KeyKeeper KEEPER2 = new NullKeyKeeper();

	private static LicensingConfiguration lc1;
	private static LicensingConfiguration lc2;

	@BeforeClass
	public static void beforeClass() {
		MAP1.put(LicensingConfigurations.LICENSING_PRODUCT_IDENTIFIER, P1);
		MAP1.put(LicensingConfigurations.LICENSING_PRODUCT_VERSION, V1);

		MAP2.put(LicensingConfigurations.LICENSING_PRODUCT_IDENTIFIER, P1);
		MAP2.put(LicensingConfigurations.LICENSING_PRODUCT_VERSION, V2);

		lc1 = LicensingConfigurations.create(MAP1);
		lc2 = LicensingConfigurations.create(MAP2);
	}

	@Test
	public void testGet() {
		BaseKeyKeeperRegistry registry = new BaseKeyKeeperRegistry();
		KeyKeeper keyKeeper = registry.getKeyKeeper(null);
		assertEquals(NullKeyKeeper.INSTANCE, keyKeeper);
		keyKeeper = registry.getKeyKeeper(LicensingConfigurations.INVALID);
		assertEquals(NullKeyKeeper.INSTANCE, keyKeeper);
	}

	@Test
	public void testBindUnbind() {
		BaseKeyKeeperRegistry registry = new BaseKeyKeeperRegistry();
		assertEquals(NullKeyKeeper.INSTANCE, registry.getKeyKeeper(lc1));
		assertEquals(NullKeyKeeper.INSTANCE, registry.getKeyKeeper(lc2));

		registry.registerKeyKeeper(KEEPER1, MAP1);
		registry.registerKeyKeeper(KEEPER2, MAP2);
		assertEquals(KEEPER1, registry.getKeyKeeper(lc1));
		assertEquals(KEEPER2, registry.getKeyKeeper(lc2));

		registry.unregisterKeyKeeper(KEEPER1, MAP2);
		registry.unregisterKeyKeeper(KEEPER2, MAP1);
		assertEquals(KEEPER1, registry.getKeyKeeper(lc1));
		assertEquals(KEEPER2, registry.getKeyKeeper(lc2));

		registry.unregisterKeyKeeper(KEEPER1, MAP1);
		registry.unregisterKeyKeeper(KEEPER2, MAP2);
		assertEquals(NullKeyKeeper.INSTANCE, registry.getKeyKeeper(lc1));
		assertEquals(NullKeyKeeper.INSTANCE, registry.getKeyKeeper(lc2));
	}
}
