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
package org.eclipse.passage.lic.internal.hc.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.api.conditions.UserRole;
import org.eclipse.passage.lic.internal.api.conditions.mining.ContentType;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.NamedData;
import org.eclipse.passage.lic.internal.base.ProductIdentifier;
import org.eclipse.passage.lic.internal.base.ProductVersion;
import org.eclipse.passage.lic.internal.base.conditions.mining.LicensingContentType;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.hc.remote.impl.RemoteConditionsRequest;
import org.eclipse.passage.lic.internal.net.LicensingAction;
import org.eclipse.passage.lic.internal.net.LicensingRole;
import org.eclipse.passage.lic.internal.net.LicensingServerHost;
import org.eclipse.passage.lic.internal.net.LicensingServerPort;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

@SuppressWarnings("restriction")
public final class RemoteConditionsRequestTest {

	private final String host = "fake.licensing.server"; //$NON-NLS-1$
	private final int port = 8080;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void urlContainsAllParameters() throws IOException {
		writeSettings();
		URL url = url();
		assertEquals(host, url.getHost());
		assertEquals(port, url.getPort());
		assertNotNull(url.getQuery());
		assertTrue(url.getQuery().contains(new ProductIdentifier("any").key())); //$NON-NLS-1$
		assertTrue(url.getQuery().contains(new ProductVersion("any").key())); //$NON-NLS-1$
		assertTrue(url.getQuery().contains(new LicensingAction(new ConditionAction.Of("any")).key())); //$NON-NLS-1$
		assertTrue(url.getQuery().contains(new LicensingRole(new UserRole.Of("any")).key())); //$NON-NLS-1$
		assertTrue(url.getQuery().contains("user")); //$NON-NLS-1$ // FIXME: #564815
		assertTrue(url.getQuery().contains(new LicensingContentType(new ContentType.Of("any")).key())); //$NON-NLS-1$
	}

	private URL url() {
		try {
			return new RemoteConditionsRequest(product(), folder.getRoot()::toPath).url();
		} catch (LicensingException e) {
			fail("Url composition on valid parameters must succssed"); //$NON-NLS-1$
			throw new RuntimeException(e); // unreachable
		}
	}

	private void writeSettings() throws IOException {
		StringBuilder output = new StringBuilder();
		new NamedData.Writable<String>(new LicensingServerHost(host)).write(output, "=", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		new NamedData.Writable<String>(new LicensingServerPort(port)).write(output, "=", "\n");//$NON-NLS-1$ //$NON-NLS-2$
		try (FileWriter writer = new FileWriter(folder.newFile(settingsFileName()))) {
			writer.append(output.toString());
		}
	}

	private LicensedProduct product() {
		return new BaseLicensedProduct("fake-product", "0.1.27"); //$NON-NLS-1$//$NON-NLS-2$
	}

	private String settingsFileName() {
		return Long.toHexString(System.nanoTime()) + new PassageFileExtension.Settings().get();
	}
}
