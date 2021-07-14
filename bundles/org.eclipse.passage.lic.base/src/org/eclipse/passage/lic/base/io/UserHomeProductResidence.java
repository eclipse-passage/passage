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
package org.eclipse.passage.lic.base.io;

import java.nio.file.Path;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;

/**
 * @since 2.1
 */
public final class UserHomeProductResidence implements Supplier<Path> {

	private final Supplier<Path> residence;

	public UserHomeProductResidence(LicensedProduct product) {
		residence = new PathFromLicensedProduct(new LicensingFolder(new UserHomePath()), product);
	}

	public UserHomeProductResidence(String product, String version) {
		residence = new PathFromLicensedProduct(new LicensingFolder(new UserHomePath()), product, version);
	}

	@Override
	public Path get() {
		Path path = residence.get();
		path.toFile().mkdirs();
		return path;
	}

}
