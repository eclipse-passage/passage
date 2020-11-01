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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 
 * Experimental API to select existing or create new instance of given type.
 *
 */
public final class ZeroOrMany<C> {

	private final Supplier<Collection<C>> source;

	public ZeroOrMany(Supplier<Collection<C>> input) {
		this.source = input;
	}

	/**
	 * 
	 * @param create the supplier of new instances. If no {@code input} elements are
	 *               supplies then a singleton collection of a newly created one is
	 *               returned. The supplier can potentially return nothing - then
	 *               the output collection is to be empty.
	 * @param select the filtering selector callback which takes existing instances
	 *               ({@code input}) and does a filtering of any complexity,
	 *               possibly invoking UI.
	 * @return collection of {@code select}-ed instances, a singleton
	 *         {@code new instance} collection or an empty collection.
	 */
	public Collection<C> choose(Supplier<Optional<C>> create, Function<Collection<C>, Collection<C>> select) {
		Collection<C> input = source.get();
		if (input.isEmpty()) {
			return create.get()//
					.map(Collections::singleton)//
					.orElse(Collections.emptySet());
		}
		return select.apply(input);
	}

}
