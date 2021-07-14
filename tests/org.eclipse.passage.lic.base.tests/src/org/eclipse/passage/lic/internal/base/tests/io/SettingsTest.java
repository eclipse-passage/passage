/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.internal.base.tests.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.eclipse.passage.lic.base.io.Settings;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public final class SettingsTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void enoughStopsSearching() throws IOException, LicensingException {
		// given
		String key = "common_key"; //$NON-NLS-1$
		writeOverlappingSettings(key + " = some_value", new PassageFileExtension.Settings()); //$NON-NLS-1$
		// when
		Map<String, Object> properties = new Settings(//
				folder.getRoot()::toPath, //
				m -> m.containsKey(key)).get();
		// then
		assertTrue(properties.containsKey(key));
		assertEquals(3, properties.size()); // only one file is loaded
	}

	@Test
	public void neverEnoughDoesNotStopSearching() throws IOException, LicensingException {
		// given
		writeOverlappingSettings("x=X", new PassageFileExtension.Settings()); //$NON-NLS-1$
		// when
		Map<String, Object> properties = new Settings(folder.getRoot()::toPath).get();
		// then
		assertEquals(7, properties.size()); // all files are loaded
	}

	@Test
	public void onlySettingsFilesAreAnalyzed() throws IOException, LicensingException {
		// given
		writeOverlappingSettings("s=S", new PassageFileExtension.LicenseDecrypted()); //$NON-NLS-1$
		// when
		Map<String, Object> properties = new Settings(folder.getRoot()::toPath).get();
		// then
		assertEquals(0, properties.size()); // all files are loaded
	}

	private void writeOverlappingSettings(String common, PassageFileExtension extension) throws IOException {
		writeOneMoreFile(extension, common, "a=A", "b=B"); //$NON-NLS-1$//$NON-NLS-2$
		writeOneMoreFile(extension, common, "c=C", "d=D"); //$NON-NLS-1$//$NON-NLS-2$
		writeOneMoreFile(extension, common, "e=E", "f=F"); //$NON-NLS-1$//$NON-NLS-2$
	}

	private void writeOneMoreFile(PassageFileExtension extension, String... extras) throws IOException {
		File file = folder.newFile(Long.toHexString(System.nanoTime()) + extension.get());
		try (PrintWriter writer = new PrintWriter(file)) {
			Arrays.stream(extras).forEach(writer::println);
		}
	}

}
