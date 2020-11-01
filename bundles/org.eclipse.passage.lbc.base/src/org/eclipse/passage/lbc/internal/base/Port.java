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
package org.eclipse.passage.lbc.internal.base;

import java.util.function.Supplier;

public abstract class Port implements Supplier<Integer> {

	private final int port;

	public Port(int port) {
		this.port = port;
	}

	@Override
	public final Integer get() {
		return port;
	}

	public static final class Custom extends Port {

		public Custom(int argument) {
			super(argument);
		}

	}

	public static final class Default extends Port {

		public Default() {
			super(8090);
		}

	}

	public static final class OfArgument implements Supplier<Port> {

		private final Port port;

		public OfArgument(String argument) {
			this.port = port(argument);
		}

		@Override
		public Port get() {
			return port;
		}

		private Port port(String argument) {
			try {
				return new Custom(Integer.parseInt(argument));
			} catch (NumberFormatException e) {
				return new Default();
			}
		}

	}

}
