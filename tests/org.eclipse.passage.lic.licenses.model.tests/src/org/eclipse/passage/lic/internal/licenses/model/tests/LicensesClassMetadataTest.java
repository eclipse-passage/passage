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
 *     Nikifor Fedorov <zelenyhleb@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.licenses.model.tests;

import static org.junit.Assert.assertTrue;

import org.eclipse.passage.lic.internal.licenses.model.LicensesClassMetadata;
import org.eclipse.passage.lic.licenses.LicensePlanDescriptor;
import org.eclipse.passage.lic.licenses.model.api.LicensePlan;
import org.junit.Test;

public class LicensesClassMetadataTest {

	private final LicensesClassMetadata metadata;

	public LicensesClassMetadataTest() {
		metadata = new LicensesClassMetadata();
	}

	@Test
	public void licensePlanMetadata() {
		assertTrue(metadata.find(LicensePlanDescriptor.class).isPresent());
		assertTrue(metadata.find(LicensePlan.class).isPresent());
	}

}
