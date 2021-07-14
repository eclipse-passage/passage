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
package org.eclipse.passage.lic.api.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * <p>
 * Here we check an arbitrary service method, that returns multiple results as a
 * collection for being read only.
 * </p>
 */
public abstract class ReadOnlyCollectionTest<T> {

	@Test
	public final void prohibitsInjections() {
		testInterferenceResistence(shared -> shared.add(single()));
		testInterferenceResistence(shared -> shared.addAll(Collections.singleton(single())));
	}

	@Test
	public final void prohibitsRemovals() {
		assumeTrue(collection().get().size() > 0);
		testInterferenceResistence(Collection::clear);
		testInterferenceResistence(shared -> shared.retainAll(Collections.emptyList()));
		testInterferenceResistence(shared -> shared.removeAll(shared));
		testInterferenceResistence(shared -> shared.remove(shared.iterator().next()));
	}

	/**
	 * 
	 * @param interference alternation action over the collection supplying method
	 *                     result. Each {@code interference} operation must either
	 *                     be unsupported or cause no alternation to subsequent
	 *                     service method result.
	 */
	private void testInterferenceResistence(Consumer<Collection<T>> interference) {
		// given
		Collection<T> shared = collection().get();
		int before = shared.size();
		// when
		try {
			interference.accept(shared);
		} catch (UnsupportedOperationException unsupported) {
			// then (hooray! unsupported! no alternation either way!)
			return;
		}
		// then
		assertTrue(before == collection().get().size());
	}

	/**
	 * Define supplying service method under test
	 */
	protected abstract Supplier<Collection<T>> collection();

	/**
	 * Supply single instance of T here
	 */
	protected abstract T single();

}
