/*******************************************************************************
 * Copyright (c) 2019, 2020 ArSysOp
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
package org.eclipse.passage.loc.internal.api;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 
 * Experimental API to select existing or create new instance of
 *
 */
public final class ZeroOneMany<C> {

	private final Supplier<Iterable<C>> supplier;

	public ZeroOneMany(Supplier<Iterable<C>> input) {
		this.supplier = input;
	}

	public Optional<C> choose(Supplier<C> create, Function<Iterable<C>, Optional<C>> select) {
		Iterable<C> input = supplier.get();
		Iterator<C> iterator = input.iterator();
		if (!iterator.hasNext()) {
			return Optional.ofNullable(create.get());
		}
		C first = iterator.next();
		if (iterator.hasNext()) {
			return select.apply(input);
		}
		return Optional.ofNullable(first);
	}

}
