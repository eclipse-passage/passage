/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.passage.loc.yars.internal.api.Progress;
import org.eclipse.passage.loc.yars.internal.api.model.InMemoryStorage;

@SuppressWarnings("restriction")
final class CountingProgress implements Progress<ExportEntry> {

	private final InMemoryStorage storage;
	private final int limit;
	private int estimated = 0;
	private int started = 0;
	private int finished = 0;
	private List<String> infos = new ArrayList<>();

	CountingProgress(InMemoryStorage storage, int limit) {
		this.storage = storage;
		this.limit = limit;
	}

	@Override
	public void estimate(int amount) {
		estimated = amount;
	}

	@Override
	public void reportNodeSrart(ExportEntry entry) {
		started++;
	}

	@Override
	public void reportNodeFinish(ExportEntry entry) {
		finished++;
	}

	@Override
	public void report(String info) {
		this.infos.add(info);
	}

	@Override
	public boolean cancelDemanded() {
		return finished >= limit;
	}

	void assertContractIsOk() {
		assertTrue(started == finished);
		assertTrue(started <= estimated);
	}

	void assertStateIsOk() {
		assertEquals(storage.size(), estimated);
		assertTrue(started == limit);
	}

}
