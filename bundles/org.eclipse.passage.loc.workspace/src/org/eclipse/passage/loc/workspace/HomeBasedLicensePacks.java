/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.loc.workspace;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.base.io.LicensingFolder;
import org.eclipse.passage.lic.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.base.io.UserHomePath;
import org.eclipse.passage.loc.internal.api.workspace.FolderHandle;
import org.eclipse.passage.loc.internal.api.workspace.LicensePacks;

public final class HomeBasedLicensePacks implements LicensePacks {

	private final Supplier<Path> residence;

	HomeBasedLicensePacks() {
		this.residence = new LicensingFolder(new UserHomePath());
	}

	@Override
	public FolderHandle packResidence(String pack, String product, String version) {
		return new LocalFolderHandle(new PathFromLicensedProduct(residence, product, version).get().resolve(pack));
	}

}
