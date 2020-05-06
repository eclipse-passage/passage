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
package org.eclipse.passage.loc.yars.internal.api.export;

import org.eclipse.passage.loc.yars.internal.api.FetchParams;
import org.eclipse.passage.loc.yars.internal.api.FetchedData;
import org.eclipse.passage.loc.yars.internal.api.Query;
import org.eclipse.passage.loc.yars.internal.api.model.InMemoryStorage;

@SuppressWarnings("restriction")
final class All implements Query<InMemoryStorage, ExportEntry, FetchParams> {

	@Override
	public String id() {
		return "ALL"; //$NON-NLS-1$
	}

	@Override
	public String description() {
		return "Fetch all entries from an im-memory storage in one fell swoop"; //$NON-NLS-1$
	}

	@Override
	public FetchedData<InMemoryStorage, ExportEntry> fetch(InMemoryStorage base, FetchParams params) {
		return new Fetch(base);
	}

}
