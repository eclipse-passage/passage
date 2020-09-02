/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.equinox.io;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.URIUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.io.LicensingPaths;
import org.eclipse.passage.lic.internal.equinox.i18n.EquinoxMessages;
import org.eclipse.passage.lic.internal.equinox.io.ConfigurationPath;
import org.eclipse.passage.lic.internal.equinox.io.InstallationPath;

/**
 * @deprecated in the favor of {@code lic.interal.base.io} Path suppliers
 *             accompanied with {@linkplain InstallationPath} and
 *             {@linkplain ConfigurationPath}
 */
@Deprecated
public class EquinoxPaths {

	/**
	 * @deprecated use {@link InstallationPath} in decoration with
	 */
	@Deprecated
	public static Path resolveInstallBasePath() {
		URL url = Platform.getInstallLocation().getURL();
		return LicensingPaths.resolveBasePath(url);
	}

	/**
	 * @deprecated it is not used now and is prohibited to use in the future. To be
	 *             removed.
	 */
	@Deprecated
	public static Path extractPath(String property, String value) throws LicensingException {
		try {
			return Paths.get(URIUtil.fromString(value));
		} catch (URISyntaxException e) {
			String pattern = EquinoxMessages.EquinoxPaths_uri_retrieval_error;
			String message = NLS.bind(pattern, value, property);
			LicensingResult result = LicensingResults.createError(message, EquinoxPaths.class.getName(), e);
			throw new LicensingException(result);
		}
	}

}
