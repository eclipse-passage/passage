/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.yars.internal.api.export;

import org.eclipse.passage.loc.yars.internal.api.ExportData;
import org.eclipse.passage.loc.yars.internal.api.FetchedData;
import org.eclipse.passage.loc.yars.internal.api.ListMedia;
import org.eclipse.passage.loc.yars.internal.api.model.InMemoryStorage;

@SuppressWarnings("restriction")
class Export implements ExportData<ExportedEntry> {

	private final FetchedData<InMemoryStorage, ExportedEntry> query;

	Export(FetchedData<InMemoryStorage, ExportedEntry> query) {
		this.query = query;
	}

	@Override
	public <C> void write(ListMedia<ExportedEntry, C> media) {
		media.start();
		query.get().forEach(d -> d.write(media));
		media.finish();
	}

}
