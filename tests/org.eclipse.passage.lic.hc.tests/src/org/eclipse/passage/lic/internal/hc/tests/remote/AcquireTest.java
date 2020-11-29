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
package org.eclipse.passage.lic.internal.hc.tests.remote;

import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.base.tests.LicFolder;
import org.eclipse.passage.lbc.base.tests.TestData;
import org.eclipse.passage.lbc.internal.api.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.FloatingState;
import org.eclipse.passage.lbc.internal.api.RawRequest;
import org.eclipse.passage.lbc.internal.base.EagerFloatingState;
import org.eclipse.passage.lbc.internal.base.ProductUserRequest;
import org.eclipse.passage.lbc.internal.base.acquire.Acquisition;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.acquire.GrantAcqisition;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.io.PathKeyKeeper;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.internal.bc.BcStreamCodec;
import org.eclipse.passage.lic.internal.hc.remote.Client;
import org.eclipse.passage.lic.internal.hc.remote.impl.acquire.RemoteAcquisitionService;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class AcquireTest {

	private final TestData data = new TestData();
	private final Supplier<Path> source = new LicFolder();
	private final FloatingState server = new EagerFloatingState(source);

	@Test
	public void acquireAndRelease() {
		RemoteAcquisitionService<ShortcutConnection> service = //
				new RemoteAcquisitionService<ShortcutConnection>(keys(), codecs(), this::acq, this::rel, source);
		ServiceInvocationResult<GrantAcqisition> acquisition = service.acquire(data.product(), data.feature());
		assertTrue(acquisition.data().isPresent());
		ServiceInvocationResult<Boolean> release = service.release(data.product(), acquisition.data().get());
		assertTrue(release.data().isPresent());

	}

	private StreamCodecRegistry codecs() {
		return () -> new ReadOnlyRegistry<>(new BcStreamCodec(data::product));
	}

	private KeyKeeperRegistry keys() {
		return () -> new ReadOnlyRegistry<>(new PathKeyKeeper(data.product(), source));
	}

	private Client<ShortcutConnection, GrantAcqisition> acq() {
		return new ShortcutClient<GrantAcqisition>(new AskAcquirer());
	}

	private Client<ShortcutConnection, Boolean> rel() {
		return new ShortcutClient<Boolean>(new AskReleaser());
	}

	private final class AskAcquirer implements ShortcutClient.Remote {

		@Override
		public FloatingResponse invoke(RawRequest raw) throws LicensingException {
			return new Acquisition(new ProductUserRequest(raw)).get();
		}

		@Override
		public FloatingState state() {
			return server;
		}

	}

	private final class AskReleaser implements ShortcutClient.Remote {

		@Override
		public FloatingResponse invoke(RawRequest raw) throws LicensingException {
			return new Acquisition(new ProductUserRequest(raw)).returnBack();
		}

		@Override
		public FloatingState state() {
			return server;
		}
	}

}
