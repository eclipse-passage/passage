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

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.equinox.BundleResource;
import org.eclipse.passage.lic.internal.equinox.i18n.AccessMessages;
import org.osgi.framework.Bundle;

final class BundleManifest {

	private final Bundle bundle;
	private final Path midpath = Paths.get("META-INF").resolve("MANIFEST.MF"); //$NON-NLS-1$ //$NON-NLS-2$

	public BundleManifest(Bundle bundle) {
		Objects.requireNonNull(bundle, "BundleManifest::bundle"); //$NON-NLS-1$
		this.bundle = bundle;
	}

	public String get() throws LicensingException {
		Optional<URL> url = new BundleResource(bundle, midpath).get();
		if (!url.isPresent()) {
			throw noManifest();
		}
		try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(url.get().openStream()))) {
			return reader.lines().collect(Collectors.joining("\n")); //$NON-NLS-1$
		} catch (IOException e) {
			throw errorOnReading(e);
		}
	}

	private LicensingException errorOnReading(IOException e) {
		return new LicensingException(String.format(//
				AccessMessages.RequirementsFromManifest_reading_error, //
				e));
	}

	private LicensingException noManifest() {
		return new LicensingException(String.format(//
				AccessMessages.RequirementsFromManifest_no_manifest, //
				midpath, //
				bundle.getSymbolicName()));
	}
}
