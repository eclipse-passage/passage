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
package org.eclipse.passage.lic.equinox.io;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.io.LicensingPaths;
import org.eclipse.passage.lic.equinox.EquinoxMessages;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.eclipse.passage.lic.runtime.LicensingResult;

public class EquinoxPaths {

	public static Path resolveInstallConfigurationPath(LicensingConfiguration configuration) {
		URL url = Platform.getInstallLocation().getURL();
		return LicensingPaths.resolveConfigurationPath(url, configuration);
	}

	public static Path resolveInstallBasePath() {
		URL url = Platform.getInstallLocation().getURL();
		return LicensingPaths.resolveBasePath(url);
	}

	public static Path extractPath(String property, String value) throws LicensingException {
		String source = EquinoxPaths.class.getName();
		try {
			return Paths.get(URIUtil.fromString(value));
		} catch (URISyntaxException e) {
			String pattern = EquinoxMessages.EquinoxPaths_uri_retrieval_error;
			String message = NLS.bind(pattern, value, property);
			LicensingResult result = LicensingResults.createError(message, source, e);
			throw new LicensingException(result);
		}
	}

}
