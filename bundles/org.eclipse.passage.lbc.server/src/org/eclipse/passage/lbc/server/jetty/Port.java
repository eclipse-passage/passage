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
package org.eclipse.passage.lbc.server.jetty;

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

	public static class OfArgument extends Port {

		public OfArgument(String argument) throws NumberFormatException {
			super(Integer.parseInt(argument));
		}

	}

	public static class Default extends Port {

		public Default() {
			super(8090);
		}

	}

}
