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

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.base.tests.TestData;
import org.eclipse.passage.lbc.internal.base.EagerFloatingState;
import org.eclipse.passage.lbc.internal.base.acquire.Acquisition;
import org.eclipse.passage.lbc.internal.base.api.FloatingState;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.acquire.GrantAcquisition;
import org.eclipse.passage.lic.base.diagnostic.DiagnosticExplained;
import org.eclipse.passage.lic.hc.remote.Client;
import org.eclipse.passage.lic.hc.remote.impl.acquire.RemoteAcquisitionService;
import org.eclipse.passage.lic.internal.net.api.handle.NetResponse;
import org.eclipse.passage.lic.internal.net.handle.ProductUserRequest;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class AcquireTest {

	private final TestData data = new TestData();
	private final Supplier<Path> source = new TestLicFolder();
	private final FloatingState server = new EagerFloatingState(source);

	@Test
	public void acquireAndRelease() {
		RemoteAcquisitionService<ShortcutConnection> service = //
				new RemoteAcquisitionService<ShortcutConnection>(//
						new TestEquipment(data.product(), source).get(), this::acq, this::rel, source);
		ServiceInvocationResult<GrantAcquisition> acquisition = service.acquire(data.product(), data.feature());
		assertTrue(new DiagnosticExplained(acquisition.diagnostic()).get(), acquisition.data().isPresent());
		ServiceInvocationResult<Boolean> release = service.release(data.product(), acquisition.data().get());
		assertTrue(release.data().isPresent());
	}

	private Client<ShortcutConnection, GrantAcquisition> acq() {
		return new ShortcutClient<GrantAcquisition>(new AskAcquirer(), data);
	}

	private Client<ShortcutConnection, Boolean> rel() {
		return new ShortcutClient<Boolean>(new AskReleaser(), data);
	}

	private final class AskAcquirer implements ShortcutClient.Remote {

		@Override
		public NetResponse invoke(RawRequest raw) throws LicensingException {
			return new Acquisition(new ProductUserRequest<RawRequest>(raw)).get();
		}

		@Override
		public FloatingState state() {
			return server;
		}

	}

	private final class AskReleaser implements ShortcutClient.Remote {

		@Override
		public NetResponse invoke(RawRequest raw) throws LicensingException {
			return new Acquisition(new ProductUserRequest<RawRequest>(raw)).returnBack();
		}

		@Override
		public FloatingState state() {
			return server;
		}
	}

}
