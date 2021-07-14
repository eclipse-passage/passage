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
import org.eclipse.core.runtime.Platform;
import org.eclipse.passage.lic.base.NamedData;

/**
 * @since 1.1
 */
public abstract class CliParameter<T> implements NamedData<T> {

	protected final Logger log = LogManager.getLogger(getClass());
	protected final Optional<String> raw;
	private final T lazy;

	public CliParameter(T lazy) {
		this(Platform.getCommandLineArgs(), lazy);
	}

	public CliParameter(String[] sources, T lazy) {
		this.lazy = lazy;
		String prefix = String.format("-%s=", key()); //$NON-NLS-1$
		this.raw = Arrays.stream(sources)//
				.map(String::toLowerCase)//
				.filter(source -> source.startsWith(prefix))//
				.map(source -> source.substring(prefix.length()))//
				.findAny();
	}

	@Override
	public final Optional<T> get() {
		return raw.map(this::parse).orElseGet(this::defaultValue);
	}

	protected abstract Optional<T> parse(String value);

	protected final Optional<T> defaultValue() {
		return Optional.of(lazy);
	}

}
