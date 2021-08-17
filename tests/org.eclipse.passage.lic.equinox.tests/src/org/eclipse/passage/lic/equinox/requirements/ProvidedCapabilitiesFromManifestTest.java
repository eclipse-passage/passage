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
package org.eclipse.passage.lic.equinox.requirements;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.eclipse.passage.lic.api.LicensingException;
import org.junit.Test;

public final class ProvidedCapabilitiesFromManifestTest {

	@Test(expected = NullPointerException.class)
	public void sourceIsMandatory() {
		new ProvidedCapabilitiesFromManifest(null);
	}

	@Test
	public void empty() {
		try {
			assertFalse(new ProvidedCapabilitiesFromManifest("").get().isPresent()); //$NON-NLS-1$
		} catch (LicensingException e) {
			fail("Manifest scanning is not supposed to fail on valid data"); //$NON-NLS-1$
		}
	}

	@Test
	public void noCapabilitiesDeclared() {
		String manifest = "Manifest-Version: 1.0\r\n" //$NON-NLS-1$
				+ "Automatic-Module-Name: org.eclipse.passage.lic.equinox.tests\r\n" //$NON-NLS-1$
				+ "Bundle-ManifestVersion: 2\r\n" //$NON-NLS-1$
				+ "Bundle-SymbolicName: org.eclipse.passage.lic.equinox.tests\r\n" //$NON-NLS-1$
				+ "Bundle-Version: 0.6.0.qualifier\r\n" //$NON-NLS-1$
				+ "Bundle-RequiredExecutionEnvironment: JavaSE-1.8\r\n" //$NON-NLS-1$
				+ "Bundle-Name: %Bundle-Name\r\n" //$NON-NLS-1$
				+ "Bundle-Vendor: %Bundle-Vendor\r\n" //$NON-NLS-1$
				+ "Bundle-Copyright: %Bundle-Copyright\r\n" //$NON-NLS-1$
				+ "Fragment-Host: org.eclipse.passage.lic.equinox\r\n" //$NON-NLS-1$
				+ "Require-Bundle: org.junit;bundle-version=\"0.0.0\",\r\n" //$NON-NLS-1$
				+ " org.eclipse.passage.lic.api.tests;bundle-version=\"1.0.0\""; //$NON-NLS-1$
		try {
			assertFalse(new ProvidedCapabilitiesFromManifest(manifest).get().isPresent());
		} catch (LicensingException e) {
			fail("Manifest scanning is not supposed to fail on valid data"); //$NON-NLS-1$
		}
	}

	@Test
	public void existingWindowsLineEndings() {
		testExistingDeclarations("\r\n"); //$NON-NLS-1$
	}

	@Test
	public void existingNixLineEndings() {
		testExistingDeclarations("\n"); //$NON-NLS-1$
	}

	@Test
	public void fromManifestFile() {
		try {
			Optional<String> declarations = new ProvidedCapabilitiesFromManifest(//
					new BundleManifest(//
							new DataBundle().bundle()//
					).get()//
			).get();
			assertCapabilitiesAreOk(declarations);
		} catch (LicensingException e) {
			fail("Is not supposed to fail on valid data"); //$NON-NLS-1$
		}

	}

	private void testExistingDeclarations(String ending) {
		try {
			assertCapabilitiesAreOk(new ProvidedCapabilitiesFromManifest(manifest(ending)).get());
		} catch (LicensingException e) {
			fail("Manifest scanning is not supposed to fail on valid data"); //$NON-NLS-1$
		}
	}

	private void assertCapabilitiesAreOk(Optional<String> declarations) {
		assertTrue(declarations.isPresent());
		assertTrue(declarations.get().startsWith("licensing.feature;")); //$NON-NLS-1$
		assertTrue(declarations.get().contains("\"Euler number\"")); //$NON-NLS-1$
		assertTrue(declarations.get().contains(";level=\"error\"")); //$NON-NLS-1$
		assertTrue(declarations.get().contains(";agreements=\"Honor Euler.txt::comp_lics/EULERS IDENTITY\"")); //$NON-NLS-1$
	}

	private String manifest(String ending) {
		return "Manifest-Version: 1.0" + ending //$NON-NLS-1$
				+ "Automatic-Module-Name: org.eclipse.passage.lic.equinox.tests.data.requirements" + ending //$NON-NLS-1$
				+ "Bundle-ManifestVersion: 2" + ending //$NON-NLS-1$
				+ "Bundle-SymbolicName: org.eclipse.passage.lic.equinox.tests.data.requirements;singleton:=true" //$NON-NLS-1$
				+ ending + "Bundle-Version: 0.1.0.qualifier" + ending //$NON-NLS-1$
				+ "Bundle-RequiredExecutionEnvironment: JavaSE-1.8" + ending //$NON-NLS-1$
				+ "Bundle-Name: Data for Passage LIC Equinox requirements tests" + ending //$NON-NLS-1$
				+ "Bundle-Vendor: Eclipse Passage " + ending //$NON-NLS-1$
				+ "Bundle-Copyright: %Bundle-Copyright" + ending //$NON-NLS-1$
				+ "Provide-Capability: licensing.feature;licensing.feature=\"PI\";version=\"3.14.15\";name=\"PI of version PI\";level=\"error\"," //$NON-NLS-1$
				+ ending
				+ " licensing.feature;licensing.feature=\"E\";version=\"2.71.82\";name=\"Euler number\";level=\"info\";provider=\"Euler\";agreements=\"Honor Euler.txt::comp_lics/EULERS IDENTITY\"," //$NON-NLS-1$
				+ ending + " licensing.feature;licensing.feature=\"Incomplete\"," + ending //$NON-NLS-1$
				+ " licensing.feature" + ending //$NON-NLS-1$
				+ "Service-Component: OSGI-INF/*.xml" + ending //$NON-NLS-1$
				+ "Bundle-ActivationPolicy: lazy" + ending //$NON-NLS-1$
				+ "Require-Bundle: org.eclipse.osgi.services;bundle-version=\"3.8.0\""; //$NON-NLS-1$
	}
}
