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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.passage.lbc.internal.base.LicenseEObject;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.eclipse.passage.lic.licenses.model.api.LicensePack;
import org.junit.Test;

public final class LicenseEObjectTest extends LbcTestsBase {

	@Test
	public void positive() {
		List<LicensePack> licensePacks = Stream.of(conditionPack()) //
				.map(new LicenseEObject()) //
				.collect(Collectors.toList());
		assertFalse(licensePacks.isEmpty());
		LicensePack licensePack = licensePacks.get(0);
		List<LicenseGrant> grants = licensePack.getLicenseGrants();
		assertFalse(grants.isEmpty());
		LicenseGrant grant = grants.get(0);
		assertEquals(condition().feature(), grant.getFeatureIdentifier());
		assertEquals(condition().evaluationInstructions().expression(), grant.getConditionExpression());
		assertEquals(condition().evaluationInstructions().type().identifier(), grant.getConditionType());
	}

}
