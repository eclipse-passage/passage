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

import java.util.List;
import java.util.stream.Stream;

import org.eclipse.passage.lbc.internal.base.LicenseEObject;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.licenses.model.api.LicenseGrant;
import org.junit.Test;

public final class LicenseEObjectTest extends LbcTestsBase {

	@Test
	public void positive() {
		final ConditionPack conditionPack = conditionPack();
		Stream.of(conditionPack) //
				.map(new LicenseEObject()) //
				.forEach(licensePack -> {
					List<LicenseGrant> grants = licensePack.getLicenseGrants();
					grants.forEach(grant -> {
						assertEquals(condition().feature(), grant.getFeatureIdentifier());
						assertEquals(condition().evaluationInstructions().expression(), grant.getConditionExpression());
						assertEquals(condition().evaluationInstructions().type().identifier(),
								grant.getConditionType());
					});
				});
	}

}
