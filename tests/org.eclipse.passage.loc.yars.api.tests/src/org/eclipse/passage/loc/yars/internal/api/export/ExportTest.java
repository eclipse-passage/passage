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

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.eclipse.passage.loc.yars.internal.api.FetchParams;
import org.eclipse.passage.loc.yars.internal.api.ListMedia;
import org.eclipse.passage.loc.yars.internal.api.Storage;
import org.eclipse.passage.loc.yars.internal.api.model.InMemoryStorage;
import org.eclipse.passage.loc.yars.internal.api.model.StoredEntry;
import org.junit.Test;

/**
 * <p>
 * Let's say, you have a domain class {@linkplain StoredEntry} and a
 * {@linkplain Storage} - some coverer, that can somehow provide access to all
 * existing instances.
 * </p>
 * <p>
 * You desire to
 * </p>
 * <ul>
 * <li>have a look at all entries</li>
 * <li>get some information from each</li>
 * <li>and {@code write} this information to some output format.</li>
 * </ul>
 * 
 * <p>
 * Here what you should do to make this happen
 * </p>
 * <ul>
 * <li>implement a {@linkplain Query}</li>
 * <li>Form a new class to keep information of interest(here it is called
 * {@linkplain ExportedEntry}</li>
 * <li></li>
 * <li></li>
 * </ul>
 * 
 */
@SuppressWarnings("restriction")
public class ExportTest {

	@Test
	public void testCsv() {
		assertEquals("name;\nGammy;\nQuami;\nTsunami;", //$NON-NLS-1$
				queryResult(new Csv("name"))); //$NON-NLS-1$
	}

	@Test
	public void testEnlistment() {
		assertEquals(Arrays.asList( //
				new ExportedEntry("Gammy"), //$NON-NLS-1$
				new ExportedEntry("Quami"), //$NON-NLS-1$
				new ExportedEntry("Tsunami")), //$NON-NLS-1$
				queryResult(new Enlistment<>()));
	}

	@Test
	public void testJson() {
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
				queryResult(new Json()));
	}

	private <T> T queryResult(ListMedia<ExportedEntry, T> media) {
		new Export(new All().data(//
				new InMemoryStorage( //
						new StoredEntry("Gammy", "US"), //$NON-NLS-1$ //$NON-NLS-2$
						new StoredEntry("Quami", "France"), //$NON-NLS-1$ //$NON-NLS-2$
						new StoredEntry("Tsunami", "Japan")//$NON-NLS-1$ //$NON-NLS-2$
				), //
				new FetchParams.Empty()))//
						.write(media);
		return media.content();
	}

}
