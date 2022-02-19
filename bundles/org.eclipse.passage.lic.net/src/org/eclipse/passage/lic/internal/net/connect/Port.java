/*******************************************************************************
 * Copyright (c) 2020, 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.net.connect;

import java.util.Optional;

/**
 * @since 1.1
 */
public final class Port extends CliParameter<Integer> {

	/**
	 * @since 2.3
	 */
	public Port() {
		super(8090);
	}

	public Port(int lazy) {
		super(lazy);
	}

	public Port(String[] sources, int lazy) {
		super(sources, lazy);
	}

	@Override
	public String key() {
		return "server.port"; //$NON-NLS-1$
	}

	@Override
	protected Optional<Integer> parse(String value) {
		try {
			return Optional.of(Integer.parseInt(value));
		} catch (NumberFormatException e) {
			log.error("failed: ", e); //$NON-NLS-1$ ;
			return Optional.empty();
		}
	}

}
