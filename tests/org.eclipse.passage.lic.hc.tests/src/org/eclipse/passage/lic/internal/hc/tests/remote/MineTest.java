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
package org.eclipse.passage.lic.internal.hc.tests.remote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.base.tests.TestData;
import org.eclipse.passage.lbc.internal.base.EagerFloatingState;
import org.eclipse.passage.lbc.internal.base.api.FloatingState;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lbc.internal.base.mine.Conditions;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.hc.internal.remote.Client;
import org.eclipse.passage.lic.hc.remote.impl.mine.RemoteConditions;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class MineTest {

	private final TestData data = new TestData();
	private final Supplier<Path> source = new TestLicFolder();

	@Test
	public void mine() {
		ServiceInvocationResult<Collection<ConditionPack>> all = new RemoteConditions<>(//
				new TestEquipment(data.product(), source).get(), this::client, source)//
						.all(data.product());
		assertTrue(new DiagnosticExplained(all.diagnostic()).get(), all.data().isPresent());
		assertEquals(new DiagnosticExplained(all.diagnostic()).get(), 1, all.data().get().size());
	}

	private Client<ShortcutConnection, Collection<ConditionPack>> client() {
		return new ShortcutClient<Collection<ConditionPack>>(new AskMiner(), data);
	}

	private final class AskMiner implements ShortcutClient.Remote {

		@Override
		public NetResponse invoke(RawRequest raw) throws LicensingException {
			return new Conditions(new ProductUserRequest<RawRequest>(raw)).get();
		}

		@Override
		public FloatingState state() {
			return new EagerFloatingState();
		}

	}

}
