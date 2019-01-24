/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.lic.base;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.passage.lic.runtime.LicensingConfiguration;

public class LicensingPaths {

	public static String FOLDER_LICENSING_BASE = ".passage"; //$NON-NLS-1$

	public static String EXTENSION_LICENSE_DECRYPTED = ".lic"; //$NON-NLS-1$
	public static String EXTENSION_LICENSE_ENCRYPTED = ".licen"; //$NON-NLS-1$
	public static String EXTENSION_PRODUCT_PUBLIC = ".pub"; //$NON-NLS-1$
	
	public static final String PROPERTY_OSGI_INSTALL_AREA = "osgi.install.area"; //$NON-NLS-1$
	
	public static Path getBasePath(String from) {
		Path path = Paths.get(URI.create(from));
		return path.resolve(FOLDER_LICENSING_BASE);
	}

	public static Path resolveConfigurationPath(String from, LicensingConfiguration configuration) {
		Path basePath = getBasePath(from);
		if (configuration == null) {
			return basePath;
		}
		String product = configuration.getProductIdentifier();
		if (product == null) {
			return basePath;
		}
		Path productPath = basePath.resolve(product);
		String version = configuration.getProductVersion();
		if (version == null) {
			return productPath;
		}
		return productPath.resolve(version);
	}

	public static String composeFileName(LicensingConfiguration configuration, String extension) {
		String product = null;
		String version = null;
		if (configuration != null) {
			product = configuration.getProductIdentifier();
			version = configuration.getProductVersion();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(product).append('_').append(version).append(extension);
		return sb.toString();
	}

}
