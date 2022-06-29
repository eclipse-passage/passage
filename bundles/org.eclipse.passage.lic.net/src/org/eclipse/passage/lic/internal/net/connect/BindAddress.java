/*******************************************************************************
 * Copyright (c) 2022, 2022 IILS mbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IILS mbH - implementation to specify server listen address
 *******************************************************************************/
package org.eclipse.passage.lic.internal.net.connect;

import java.util.Optional;

/**
 * @since 2.5
 */
public final class BindAddress extends CliParameter<String> {

	/**
	 * @since 2.5
	 */
	public BindAddress() {
		super("0.0.0.0"); //$NON-NLS-1$
	}

	public BindAddress(String lazy) {
		super(lazy);
	}

	public BindAddress(String[] sources, String lazy) {
		super(sources, lazy);
	}

	@Override
	public String key() {
		return "server.bindaddress"; //$NON-NLS-1$
	}

	@Override
	protected Optional<String> parse(String value) {
		return Optional.of(value);
	}

}
