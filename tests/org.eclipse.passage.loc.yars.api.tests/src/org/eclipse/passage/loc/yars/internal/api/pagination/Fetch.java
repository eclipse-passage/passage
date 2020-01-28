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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.passage.loc.yars.internal.api.FetchedData;
import org.eclipse.passage.loc.yars.internal.api.model.InMemoryStorage;
import org.eclipse.passage.loc.yars.internal.api.model.StoredEntry;

@SuppressWarnings("restriction")

class Fetch implements FetchedData<InMemoryStorage, StoredEntry> {

	private final PaginationSettings settings;
	private final InMemoryStorage storage;

	Fetch(PaginationSettings settings, InMemoryStorage storage) {
		this.settings = settings;
		this.storage = storage;
	}

	@Override
	public List<StoredEntry> get() {
		List<StoredEntry> all = storage.entries();
		return IntStream.range(settings.from(), settings.to()) //
				.filter(i -> i >= 0) //
				.filter(i -> i < all.size()) //
				.mapToObj(all::get) //
				.collect(Collectors.toList());
	}

}
