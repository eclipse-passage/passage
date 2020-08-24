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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.passage.lbc.internal.api.BackendLicenseVault;
import org.eclipse.passage.lbc.internal.base.BaseLicenseVault;
import org.eclipse.passage.lbc.internal.base.BaseServerConfiguration;
import org.eclipse.passage.lbc.internal.base.ServerKeyKeeper;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.bc.BcStreamCodec;
import org.eclipse.passage.lic.internal.licenses.migration.tobemoved.XmiConditionTransport;
import org.junit.Test;

public class MiningTest extends LbcTestsBase {

	@Test
	public void positive() {
		BackendLicenseVault vault = new BaseLicenseVault(
				new BaseServerConfiguration(Collections.singleton(new BcStreamCodec(() -> product())),
						Collections.singleton(new ServerKeyKeeper(() -> product())),
						Collections.singleton(new XmiConditionTransport())));
		ServiceInvocationResult<Collection<ConditionPack>> result = vault
				.availableLicenses(new FakeMiningRequest(product()));
		assertTrue(result.diagnostic().bearable().isEmpty());
		assertTrue(result.diagnostic().severe().isEmpty());
	}

}
