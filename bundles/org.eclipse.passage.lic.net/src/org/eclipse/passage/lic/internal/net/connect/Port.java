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
package org.eclipse.passage.lic.internal.net.connect;

import java.util.Arrays;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.passage.lic.internal.base.NamedData;

/**
 * @since 1.1
 */
public final class Port implements NamedData<Integer> {

	private final Logger log = LogManager.getLogger(getClass());
	private final Optional<String> raw;
	private final int lazy;

	public Port(String[] sources, int lazy) {
		this.lazy = lazy;
		String prefix = String.format("-%s=", key()); //$NON-NLS-1$
		this.raw = Arrays.stream(sources)//
				.map(String::toLowerCase)//
				.filter(source -> source.startsWith(prefix))//
				.map(source -> source.substring(prefix.length()))//
				.findAny();
	}

	@Override
	public String key() {
		return "server.port"; //$NON-NLS-1$
	}

	@Override
	public Optional<Integer> get() {
		return raw.map(this::parse).orElseGet(this::defaultPort);
	}

	private Optional<Integer> defaultPort() {
		return Optional.of(lazy);
	}

	private Optional<Integer> parse(String port) {
		try {
			return Optional.of(Integer.parseInt(port));
		} catch (NumberFormatException e) {
			log.error("failed: ", e); //$NON-NLS-1$ ;
			return Optional.empty();
		}
	}

}
