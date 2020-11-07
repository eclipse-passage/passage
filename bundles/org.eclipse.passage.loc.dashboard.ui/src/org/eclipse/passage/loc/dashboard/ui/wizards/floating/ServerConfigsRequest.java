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
package org.eclipse.passage.loc.dashboard.ui.wizards.floating;

import java.util.Optional;
import java.util.function.Supplier;

abstract class ServerConfigsRequest {

	private final boolean generate;

	protected ServerConfigsRequest(boolean generate) {
		this.generate = generate;
	}

	boolean generate() {
		return generate;
	}

	abstract String ip();

	abstract int port();

	static final class Skip extends ServerConfigsRequest {

		Skip() {
			super(false);
		}

		@Override
		String ip() {
			throw new UnsupportedOperationException();
		}

		@Override
		int port() {
			throw new UnsupportedOperationException();
		}

	}

	static final class Active extends ServerConfigsRequest {

		private final String ip;
		private final int port;

		Active(String ip, int port) {
			super(true);
			this.ip = ip;
			this.port = port;
		}

		@Override
		String ip() {
			return ip;
		}

		@Override
		int port() {
			return port;
		}

	}

	static final class Of implements Supplier<ServerConfigsRequest> {
		private final Supplier<Optional<Boolean>> generate;
		private final Supplier<Optional<String>> ip;
		private final Supplier<Optional<Integer>> port;

		Of(Supplier<Optional<Boolean>> generate, Supplier<Optional<String>> ip, Supplier<Optional<Integer>> port) {
			this.generate = generate;
			this.ip = ip;
			this.port = port;
		}

		@Override
		public ServerConfigsRequest get() {
			return generate.get().get() //
					? new Active(ip.get().get(), port.get().get()) //
					: new Skip();

		}

	}

}
