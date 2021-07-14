/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.equinox.requirements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.osgi.framework.wiring.BundleCapability;

public final class LicensingFeatureCapabilitiesFromBundleTest {

	@Test
	public void read() {
		Optional<List<BundleCapability>> capabilities = new LicensingFeatureCapabilitiesFromBundle(//
				new DataBundle().bundle()).get();
		assertTrue(capabilities.isPresent());
		assertEquals(3, capabilities.get().size());
		assertExpectedContent(capabilities);
	}

	private void assertExpectedContent(Optional<List<BundleCapability>> capabilities) {
		String key = new CapabilityLicFeatureId(new DataBundle().e()).key();
		assertEquals(//
				new HashSet<String>(Arrays.asList(//
						"Incomplete", //$NON-NLS-1$
						"PI", //$NON-NLS-1$
						"E")), //$NON-NLS-1$
				capabilities.get().stream() //
						.filter(c -> Optional.ofNullable(c.getAttributes()).isPresent()) //
						.map(c -> c.getAttributes())//
						.filter(attributes -> attributes.containsKey(key))//
						.map(attributes -> attributes.get(key))//
						.filter(Objects::nonNull) //
						.map(Object::toString) //
						.collect(Collectors.toSet()));
	}

}
