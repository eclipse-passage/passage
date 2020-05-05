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

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.passage.loc.yars.internal.api.DefaultDosHandler;
import org.eclipse.passage.loc.yars.internal.api.DosHandleMedia;
import org.eclipse.passage.loc.yars.internal.api.FetchParams;
import org.eclipse.passage.loc.yars.internal.api.ListMedia;
import org.eclipse.passage.loc.yars.internal.api.Progress;
import org.eclipse.passage.loc.yars.internal.api.SingleSwoopExport;
import org.eclipse.passage.loc.yars.internal.api.model.InMemoryStorage;
import org.eclipse.passage.loc.yars.internal.api.model.StoredEntry;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class ProgressTest {

	@Test
	public void ticks() {
		exportWithProgress(InMemoryStorage::size);
	}

	@Test
	public void cancells() {
		exportWithProgress(storage -> storage.size() / 3);
	}

	private void exportWithProgress(Function<InMemoryStorage, Integer> limit) {
		InMemoryStorage storage = storage();
		CountingProgress progress = new CountingProgress(storage, limit.apply(storage));
		queryResult(new Enlistment<ExportEntry>(new ArrayList<>()), progress);
		progress.assertContractIsOk();
		progress.assertStateIsOk();
	}

	private InMemoryStorage storage() {
		return new InMemoryStorage( //
				IntStream.range(1, 16) //
						.mapToObj(i -> new StoredEntry(Integer.toString(i), Integer.toBinaryString(i))) //
						.collect(Collectors.toList()) //
		);
	}

	private void queryResult(ListMedia<ExportEntry> media, Progress<ExportEntry> progress) {
		InMemoryStorage storage = new InMemoryStorage( //
				IntStream.range(1, 16) //
						.mapToObj(i -> new StoredEntry(Integer.toString(i), Integer.toBinaryString(i))) //
						.collect(Collectors.toList()) //
		);
		new SingleSwoopExport<InMemoryStorage, ExportEntry>(new All().fetch(//
				storage, //
				new FetchParams.Empty()))//
						.write(//
								new DosHandleMedia<ExportEntry>(//
										media, //
										new DefaultDosHandler()), //
								progress);
	}

}
