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
package org.eclipse.passage.lbc.base.tests;

import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.eclipse.passage.lbc.internal.base.LicensesResource;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.eclipse.passage.lic.licenses.model.meta.LicensesFactory;
import org.junit.Test;

public final class LicensesResourceTest {

	@Test
	public void positive() {
		LicensePack licensePack = LicensesFactory.eINSTANCE.createLicensePack();
		Stream.of(licensePack) //
				.map(new LicensesResource()).forEach(resource -> {
					assertTrue(resource.getContents().contains(licensePack));
				});
	}

}
