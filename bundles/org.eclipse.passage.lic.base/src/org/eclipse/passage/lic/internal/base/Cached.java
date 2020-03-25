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
package org.eclipse.passage.lic.internal.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * To be removed after arsysop.lang is approved
 * 
 */
public final class Cached<S, T> implements Supplier<T> {

	private final S source;
	private final Function<S, T> retrieve;
	private final List<T> value = new ArrayList<T>(1);

	/**
	 * To create a <i>late init</i> value, you should specify a {@code source} for
	 * initialization and a {@code way} to perform it.
	 * 
	 * @param source   original data sufficient to build the <i>late init-ed
	 *                 value</i> from it
	 * @param retrieve a function that builds <i>the late init value</i> from a
	 *                 given {@code source}. It is guaranteed to be called ones and
	 *                 only when {@linkplain get} method is called.
	 * @since 0.1
	 */
	public Cached(S source, Function<S, T> retrieve) {
		Objects.requireNonNull(source, "Source cannot be null"); //$NON-NLS-1$
		Objects.requireNonNull(retrieve, "Retriever function cannot be null"); //$NON-NLS-1$
		this.source = source;
		this.retrieve = retrieve;
	}

	/**
	 * Returns cashed value. The first call begets {@code the value retrieval} and
	 * cashing.
	 * 
	 * @since 0.1
	 */
	@Override
	public T get() {
		if (value.isEmpty()) {
			value.add(retrieve.apply(source));
		}
		return value.get(0);
	}

}
