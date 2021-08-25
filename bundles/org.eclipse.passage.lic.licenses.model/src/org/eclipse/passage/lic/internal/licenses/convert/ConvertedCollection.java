/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.licenses.convert;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

abstract class ConvertedCollection<S, T> implements Supplier<Collection<T>> {

	private final Collection<S> origin;
	private final Function<S, Supplier<T>> converter;

	protected ConvertedCollection(Collection<S> origin, Function<S, Supplier<T>> converter) {
		this.origin = origin;
		this.converter = converter;
	}

	@Override
	public final Collection<T> get() {
		return origin.stream()//
				.map(converter)//
				.map(Supplier::get)//
				.collect(Collectors.toList());
	}

}
