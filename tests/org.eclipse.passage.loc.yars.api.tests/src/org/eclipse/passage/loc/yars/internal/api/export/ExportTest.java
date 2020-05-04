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

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.passage.loc.yars.internal.api.DefaultDosHandler;
import org.eclipse.passage.loc.yars.internal.api.DosHandleMedia;
import org.eclipse.passage.loc.yars.internal.api.FetchParams;
import org.eclipse.passage.loc.yars.internal.api.FetchedData;
import org.eclipse.passage.loc.yars.internal.api.ListMedia;
import org.eclipse.passage.loc.yars.internal.api.Progress;
import org.eclipse.passage.loc.yars.internal.api.Query;
import org.eclipse.passage.loc.yars.internal.api.SingleSwoopExport;
import org.eclipse.passage.loc.yars.internal.api.Storage;
import org.eclipse.passage.loc.yars.internal.api.model.InMemoryStorage;
import org.eclipse.passage.loc.yars.internal.api.model.StoredEntry;
import org.junit.Test;

/**
 * <p>
 * Here we illustrate how to use {@linkplain org.eclipse.passage.loc.yars.api}
 * in order to
 * </p>
 * <ul>
 * <li>fetch something from a {@linkplain Storage},</li>
 * <li>reorganize it to another type and</li>
 * <li>finally end up in a {@code CSV} or {@code JSON} persistence or in a
 * simple <i>enlistment</i> of the result.</li>
 * </ul>
 * 
 * <p>
 * We implement api's interfaces in the following manner.
 * </p>
 * <ul>
 * <li>Our {@linkplain Storage} consists of a simple entries of
 * {@linkplain StoredEntry} type - we emulate <i>in memory base</i>.</li>
 * <li>We define a {@linkplain Query} - {@linkplain All} - which fetches all
 * entries from our storage. It also emulates a <i>business logic</i> that is
 * implemented as a conversion stored entries to entities of another type
 * ({@linkplain ExportEntry})<i></li>
 * <li>When we as our <i>query</i> for {@linkplain Query#data()}, it actually
 * does not interacts with the {@code storage}, but only instantiate a dedicated
 * {@linkplain FetchedData} instance.</li>
 * <li>We implement {@linkplain FetchedData} as {@linkplain Fetch} class. It is,
 * by contract, aware of our particular storage type and knows how to get data
 * from it.</li>
 * <li>We have tree <i>target format definitions</i>: CSV, JSON (strings) and a
 * runtime list. All of them are unaware of storing and fetching details.</li>
 */
@SuppressWarnings("restriction")
public final class ExportTest {

	@Test
	public void testCsv() {
		StringBuilder output = new StringBuilder();
		queryResult(new Csv(output, "name")); //$NON-NLS-1$
		assertEquals("name;\nGammy;\nQuami;\nTsunami;", //$NON-NLS-1$
				output.toString());
	}

	@Test
	public void testEnlistment() {
		List<ExportEntry> output = new ArrayList<>();
		queryResult(new Enlistment<ExportEntry>(output));
		assertEquals(Arrays.asList( //
				new ExportEntry("Gammy"), //$NON-NLS-1$
				new ExportEntry("Quami"), //$NON-NLS-1$
				new ExportEntry("Tsunami")), //$NON-NLS-1$
				output);
	}

	@Test
	public void testJson() {
		StringBuilder output = new StringBuilder();
		queryResult(new Json(output));
		assertEquals("{\n" + //$NON-NLS-1$
				"\t\"node\" : {\n" + //$NON-NLS-1$
				"\t\t\"name\" : \"Gammy\"\n" + //$NON-NLS-1$
				"\t}\n" + //$NON-NLS-1$
				"\t\"node\" : {\n" + //$NON-NLS-1$
				"\t\t\"name\" : \"Quami\"\n" + //$NON-NLS-1$
				"\t}\n" + //$NON-NLS-1$
				"\t\"node\" : {\n" + //$NON-NLS-1$
				"\t\t\"name\" : \"Tsunami\"\n" + //$NON-NLS-1$
				"\t}\n" + //$NON-NLS-1$
				"}\n", //$NON-NLS-1$
				output.toString());
	}

	private void queryResult(ListMedia<ExportEntry> media) {
		new SingleSwoopExport<InMemoryStorage, ExportEntry>(new All().fetch(//
				new InMemoryStorage( //
						new StoredEntry("Gammy", "US"), //$NON-NLS-1$ //$NON-NLS-2$
						new StoredEntry("Quami", "France"), //$NON-NLS-1$ //$NON-NLS-2$
						new StoredEntry("Tsunami", "Japan")//$NON-NLS-1$ //$NON-NLS-2$
				), //
				new FetchParams.Empty()))//
						.write(//
								new DosHandleMedia<ExportEntry>(//
										media, //
										new DefaultDosHandler()), //
								new Progress.Inane<ExportEntry>());
	}

}
