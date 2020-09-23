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
package org.eclipse.passage.lic.internal.equinox.requirements;

import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.equinox.i18n.AccessMessages;

final class ProvidedCapabilitiesFromManifest {

	private final String manifest;

	ProvidedCapabilitiesFromManifest(String manifest) {
		Objects.requireNonNull(manifest, "ProvidedCapabilitiesFromManifest::manifest"); //$NON-NLS-1$
		this.manifest = manifest;
	}

	public Optional<String> get() throws LicensingException {
		Properties properties = new Properties();
		try {
			properties.load(new StringReader(lined()));
		} catch (IOException e) {
			throw fail(e);
		}
		return Optional.ofNullable(properties.getProperty(new RequirementsToBundle().key()));
	}

	private String lined() {
		return manifest//
				.replaceAll("\r\n ", "\\\\\r\n") // -- windows -- //$NON-NLS-1$//$NON-NLS-2$
				.replaceAll("\n ", "\\\\\n"); // -- nix -- //$NON-NLS-1$//$NON-NLS-2$
	}

	private LicensingException fail(IOException e) {
		return new LicensingException(AccessMessages.RequirementCapabilitiesFromManifest_ioe, e);
	}

}
