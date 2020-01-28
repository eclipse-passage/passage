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
package org.eclipse.passage.loc.yars.internal.api.pagination;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.passage.loc.yars.internal.api.model.InMemoryStorage;
import org.eclipse.passage.loc.yars.internal.api.model.StoredEntry;
import org.junit.Test;

/**
 * <p>
 * The test is focused on the case when fetching requires context-dependent
 * parameters.
 * </p>
 * <p>
 * Here we define a base of elements enough to form three pages - two complete
 * and the last partial. Then we appeal to the single query tree times with
 * different pagination fetching parameters.
 * </p>
 */
public class PaginationTest {

	@Test
	public void testFirstPage() {
		List<StoredEntry> result = pageContent(0);
		assertEquals(3, result.size());
		assertTrue(result.stream().allMatch(e -> e.origin().equals("page 1"))); //$NON-NLS-1$
	}

	@Test
	public void testMidPage() {
		List<StoredEntry> result = pageContent(1);
		assertEquals(3, result.size());
		assertTrue(result.stream().allMatch(e -> e.origin().equals("page 2"))); //$NON-NLS-1$
	}

	@Test
	public void testNotFullPage() {
		List<StoredEntry> result = pageContent(2);
		assertEquals(1, result.size());
		assertEquals("page 3", result.get(0).origin()); //$NON-NLS-1$
	}

	@SuppressWarnings("restriction")
	private List<StoredEntry> pageContent(int no) {
		return new Page().fetch(base(), new PaginationSettings(no, 3)).get();
	}

	private InMemoryStorage base() {
		return new InMemoryStorage( //
				new StoredEntry("1.0", "page 1"), //$NON-NLS-1$ //$NON-NLS-2$
				new StoredEntry("1.1", "page 1"), //$NON-NLS-1$ //$NON-NLS-2$
				new StoredEntry("1.2", "page 1"), //$NON-NLS-1$ //$NON-NLS-2$
				new StoredEntry("2.0", "page 2"), //$NON-NLS-1$ //$NON-NLS-2$
				new StoredEntry("2.1", "page 2"), //$NON-NLS-1$ //$NON-NLS-2$
				new StoredEntry("2.2", "page 2"), //$NON-NLS-1$ //$NON-NLS-2$
				new StoredEntry("3.1", "page 3")//$NON-NLS-1$ //$NON-NLS-2$
		);
	}

}
