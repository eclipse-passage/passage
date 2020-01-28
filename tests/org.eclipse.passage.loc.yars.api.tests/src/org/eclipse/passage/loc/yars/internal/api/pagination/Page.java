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

import org.eclipse.passage.loc.yars.internal.api.FetchedData;
import org.eclipse.passage.loc.yars.internal.api.Query;
import org.eclipse.passage.loc.yars.internal.api.model.InMemoryStorage;
import org.eclipse.passage.loc.yars.internal.api.model.StoredEntry;

@SuppressWarnings("restriction")
public class Page implements Query<InMemoryStorage, StoredEntry, PaginationSettings> {

	@Override
	public String id() {
		return "PAGE"; //$NON-NLS-1$
	}

	@Override
	public String description() {
		return "Fetch paginated original entries from in-memory storage"; //$NON-NLS-1$
	}

	@Override
	public FetchedData<InMemoryStorage, StoredEntry> fetch(InMemoryStorage storage, PaginationSettings params) {
		return new Fetch(params, storage);
	}

}
