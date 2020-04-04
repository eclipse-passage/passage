/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.equinox.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.osgi.framework.wiring.BundleCapability;

@SuppressWarnings("restriction")
public final class LicensingFeatureCapabilitiesFromBundleTest {

	@Test
	public void read() {
		Optional<List<BundleCapability>> capabilities = new LicensingFeatureCapabilitiesFromBundle(//
				new DataBundle().get()).get();
		assertTrue(capabilities.isPresent());
		String key = new CapabilityLicFeatureId("").key(); //$NON-NLS-1$
		assertEquals(//
				new HashSet<String>(Arrays.asList(//
						"PI", //$NON-NLS-1$
						"E")), //$NON-NLS-1$
				capabilities.get().stream() //
						.map(c -> c.getAttributes().get(key))//
						.map(Object::toString) //
						.collect(Collectors.toSet()));
	}

}
