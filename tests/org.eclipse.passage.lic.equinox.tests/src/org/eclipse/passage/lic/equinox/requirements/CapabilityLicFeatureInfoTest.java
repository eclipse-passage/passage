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
import static org.junit.Assert.assertFalse;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.base.StringNamedData;
import org.junit.Test;
import org.osgi.framework.wiring.BundleCapability;

abstract class CapabilityLicFeatureInfoTest {

	@Test
	public void read() {
		DataBundle data = new DataBundle();
		assertEquals(//
				expectations(), // $NON-NLS-1$
				data.capabilities().stream()//
						.map(BundleCapability::getAttributes) //
						.map(this::infoSupplier) //
						.map(StringNamedData::get) //
						.filter(Optional::isPresent)//
						.map(Optional::get) //
						.collect(Collectors.toSet())//
		);
	}

	@Test(expected = NullPointerException.class)
	public void nullInputMap() {
		infoSupplier(null).get();
	}

	@Test
	public void nullInputValue() {
		assertFalse(infoSupplier(Collections.emptyMap()).get().isPresent());
	}

	protected abstract StringNamedData infoSupplier(Map<String, Object> attributes);

	protected abstract Set<String> expectations();

}
