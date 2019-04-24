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

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.io.StreamCodec;
import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.base.io.BaseStreamCodecRegistry;
import org.eclipse.passage.lic.base.io.NullStreamCodec;
import org.junit.BeforeClass;
import org.junit.Test;

public class BaseStreamCodecRegistryTest {

	private static final String P1 = "p1"; //$NON-NLS-1$
	private static final String V1 = "v1"; //$NON-NLS-1$
	private static final String V2 = "v2"; //$NON-NLS-1$

	private static final Map<String, Object> MAP1 = new HashMap<>();
	private static final Map<String, Object> MAP2 = new HashMap<>();

	private static final StreamCodec CODEC1 = new NullStreamCodec();
	private static final StreamCodec CODEC2 = new NullStreamCodec();

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
		BaseStreamCodecRegistry registry = new BaseStreamCodecRegistry();
		StreamCodec keyKeeper = registry.getStreamCodec(null);
		assertEquals(NullStreamCodec.INSTANCE, keyKeeper);
		keyKeeper = registry.getStreamCodec(LicensingConfigurations.INVALID);
		assertEquals(NullStreamCodec.INSTANCE, keyKeeper);
	}

	@Test
	public void testBindUnbind() {
		BaseStreamCodecRegistry registry = new BaseStreamCodecRegistry();
		assertEquals(NullStreamCodec.INSTANCE, registry.getStreamCodec(lc1));
		assertEquals(NullStreamCodec.INSTANCE, registry.getStreamCodec(lc2));

		registry.registerStreamCodec(CODEC1, MAP1);
		registry.registerStreamCodec(CODEC2, MAP2);
		assertEquals(CODEC1, registry.getStreamCodec(lc1));
		assertEquals(CODEC2, registry.getStreamCodec(lc2));

		registry.unregisterStreamCodec(CODEC1, MAP2);
		registry.unregisterStreamCodec(CODEC2, MAP1);
		assertEquals(CODEC1, registry.getStreamCodec(lc1));
		assertEquals(CODEC2, registry.getStreamCodec(lc2));

		registry.unregisterStreamCodec(CODEC1, MAP1);
		registry.unregisterStreamCodec(CODEC2, MAP2);
		assertEquals(NullStreamCodec.INSTANCE, registry.getStreamCodec(lc1));
		assertEquals(NullStreamCodec.INSTANCE, registry.getStreamCodec(lc2));
	}
}
