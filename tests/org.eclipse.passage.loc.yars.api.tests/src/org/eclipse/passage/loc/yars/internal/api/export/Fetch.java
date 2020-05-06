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

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.passage.loc.yars.internal.api.FetchedData;
import org.eclipse.passage.loc.yars.internal.api.model.InMemoryStorage;

@SuppressWarnings("restriction")
final class Fetch implements FetchedData<InMemoryStorage, ExportEntry> {

	private final InMemoryStorage source;

	Fetch(InMemoryStorage source) {
		this.source = source;
	}

	@Override
	public List<ExportEntry> get() {
		return source.entries().stream()//
				.map(dummy -> new ExportEntry(dummy.name()))//
				.collect(Collectors.toList());
	}

}
