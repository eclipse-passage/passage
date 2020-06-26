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
package org.eclipse.passage.lic.internal.equinox.io;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.core.runtime.Platform;
import org.eclipse.passage.lic.internal.base.io.LicensingFolder;
import org.eclipse.passage.lic.internal.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.PathFromLocalUrl;

/**
 * <p>
 * Accompanies {@code lic.base.internal.io} with eclipse-specific runtime
 * location: a product installation path.
 * </p>
 * <p>
 * Decorate with {@link PathFromLicensedProduct} to get a product-named folder
 * or with {@link LicensingFolder} to get Passage settings host directory under
 * the general installation folder.
 * </p>
 */
@SuppressWarnings("restriction")
public final class InstalationPath implements Supplier<Path> {

	@Override
	public Path get() {
		return new PathFromLocalUrl(Platform.getInstallLocation().getURL()).get();
	}

}
