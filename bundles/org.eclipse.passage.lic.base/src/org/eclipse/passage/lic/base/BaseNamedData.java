/*******************************************************************************
 * Copyright (c) 2019, 2021 ArSysOp
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
package org.eclipse.passage.lic.base;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Lazy implementation does not store the value itself on construction time, but
 * keep the way to get the data should the actual need arise.
 * 
 * @param <T> type of the value to supply
 * @see NamedData
 * 
 * @since 2.1
 */
public abstract class BaseNamedData<T> implements NamedData<T> {

	private final Function<String, T> retrieve;

	protected BaseNamedData(Function<String, T> retrieve) {
		Objects.requireNonNull(retrieve, "Retriever cannot be null"); //$NON-NLS-1$
		this.retrieve = retrieve;
	}

	@Override
	public Optional<T> get() {
		return Optional.ofNullable(retrieve.apply(key()));
	}

}
