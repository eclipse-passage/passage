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
package org.eclipse.passage.lic.integration.tests;

import static org.junit.Assert.assertFalse;

import org.eclipse.passage.lic.base.LicensingConfigurations;
import org.eclipse.passage.lic.runtime.LicensingCondition;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.junit.Test;

public class LicensingConditionIntegrationTest extends LicIntegrationBase {

	@Test
	public void testExtractConditionsNegative() {
		Iterable<LicensingCondition> conditionsNull = accessManager.extractConditions(null);
		assertFalse(conditionsNull.iterator().hasNext());

		
		Iterable<LicensingCondition> conditionsProduct = accessManager
				.extractConditions(LicensingConfigurations.create(null, null));
		assertFalse(conditionsProduct.iterator().hasNext());

		LicensingConfiguration configuration = LicensingConfigurations.create(SOME_ENCRYPTED_PRODUCT, null);
		Iterable<LicensingCondition> conditions = accessManager.extractConditions(configuration);
		assertFalse(conditions.iterator().hasNext());
	}

	@Test
	public void testExtractConditionsDecryptedNegative() {
		LicensingConfiguration configuration = LicensingConfigurations.create(SOME_DECRYPTED_PRODUCT, null);
		Iterable<LicensingCondition> conditions = accessManager.extractConditions(configuration);
		assertFalse(conditions.iterator().hasNext());
	}

	@Test
	public void testExtractConditionsEncryptedNegative() {
		LicensingConfiguration configuration = LicensingConfigurations.create(SOME_ENCRYPTED_PRODUCT, null);
		Iterable<LicensingCondition> conditions = accessManager.extractConditions(configuration);
		assertFalse(conditions.iterator().hasNext());
	}

}
