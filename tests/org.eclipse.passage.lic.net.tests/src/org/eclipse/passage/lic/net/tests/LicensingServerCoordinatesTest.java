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
package org.eclipse.passage.lic.net.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.NamedData;
import org.eclipse.passage.lic.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.net.HostPort;
import org.eclipse.passage.lic.internal.net.LicensingServerCoordinatesFromSettings;
import org.eclipse.passage.lic.internal.net.LicensingServerHost;
import org.eclipse.passage.lic.internal.net.LicensingServerPort;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public final class LicensingServerCoordinatesTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void existingSettingsAreFound() throws IOException, LicensingException {
		writeSettingsFile("some.server.ho", "1234"); //$NON-NLS-1$//$NON-NLS-2$
		assertEquals("some.server.ho:1234", coordinates()); //$NON-NLS-1$
	}

	@Test
	public void canDefineHostAndPortInDifferentFiles() throws IOException, LicensingException {
		writeSettingsFile("some.server", null); //$NON-NLS-1$
		writeSettingsFile(null, "56789"); //$NON-NLS-1$
		assertEquals("some.server:56789", coordinates()); //$NON-NLS-1$
	}

	@Test(expected = LicensingException.class)
	public void absentHostCausesFailure() throws IOException, LicensingException {
		writeSettingsFile(null, "8080"); //$NON-NLS-1$
		coordinates();
	}

	@Test(expected = LicensingException.class)
	public void absentPortCausesFailure() throws IOException, LicensingException {
		writeSettingsFile("192.168.0.1", null); //$NON-NLS-1$
		coordinates();
	}

	@Test(expected = LicensingException.class)
	public void emptyHostCausesFailure() throws IOException, LicensingException {
		writeSettingsFile("", "2212"); //$NON-NLS-1$ //$NON-NLS-2$
		coordinates();
	}

	@Test(expected = LicensingException.class)
	public void emptyPortCausesFailure() throws IOException, LicensingException {
		writeSettingsFile("server.address", ""); //$NON-NLS-1$ //$NON-NLS-2$
		coordinates();
	}

	@Test
	public void valuesAreNotVerified() throws IOException {
		writeSettingsFile("(absolutely)$invald+server&*%.address", "not-a-port-value"); //$NON-NLS-1$ //$NON-NLS-2$
		try {
			coordinates();
		} catch (LicensingException e) {
			fail("Values verification is not the part of settings retrieval"); //$NON-NLS-1$
		}
	}

	private String coordinates() throws LicensingException {
		HostPort coords = new LicensingServerCoordinatesFromSettings(folder.getRoot()::toPath).get();
		return String.format("%s:%s", coords.host(), coords.port()); //$NON-NLS-1$
	}

	private void writeSettingsFile(String host, String port) throws IOException {
		File file = folder.newFile(Long.toHexString(System.nanoTime()) + new PassageFileExtension.Settings().get());
		StringBuilder out = new StringBuilder();
		if (host != null) {
			new NamedData.Writable<String>(new LicensingServerHost(host)).write(out);
		}
		if (port != null) {
			new NamedData.Writable<String>(new LicensingServerPort(port)).write(out);
		}
		try (PrintWriter writer = new PrintWriter(file)) {
			writer.print(out.toString());
		}
	}

}
