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
package org.eclipse.passage.loc.internal.api;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.PassageFileExtension;
import org.eclipse.passage.lic.internal.base.io.PathFromLocalUrl;

/**
 * @deprecated in favor of new `lic.internal.base.io` facilities
 */
@Deprecated
public final class LicensingPaths {

	/**
	 * @deprecated use {@link LicensingFolder}
	 */
	@Deprecated
	public static final String FOLDER_LICENSING_BASE = ".passage"; //$NON-NLS-1$

	/**
	 * @deprecated use {@link PassageFileExtension.LicenseDecrypted}
	 */
	@Deprecated
	public static final String EXTENSION_LICENSE_DECRYPTED = ".lic"; //$NON-NLS-1$
	/**
	 * @deprecated use {@link PassageFileExtension.LicenseEncrypted}
	 */
	@Deprecated
	public static final String EXTENSION_LICENSE_ENCRYPTED = ".licen"; //$NON-NLS-1$
	/**
	 * @deprecated use {@link PassageFileExtension.PublicKey}
	 */
	@Deprecated
	public static final String EXTENSION_PRODUCT_PUBLIC = ".pub"; //$NON-NLS-1$

	private LicensingPaths() {
		// block
	}

	/**
	 * @deprecated use {@link LicensingFolder} and {@link PathFromLocalUrl}
	 */
	@Deprecated
	public static Path resolveBasePath(URL url) {
		File file = new File(url.getPath());
		Path path = Paths.get(file.getPath());
		return path.resolve(FOLDER_LICENSING_BASE);
	}

}
