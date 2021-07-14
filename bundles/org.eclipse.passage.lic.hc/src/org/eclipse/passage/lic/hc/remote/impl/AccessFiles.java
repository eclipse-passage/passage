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
package org.eclipse.passage.lic.hc.remote.impl;

import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.base.io.FileCollection;
import org.eclipse.passage.lic.base.io.FloatingFileExtension;
import org.eclipse.passage.lic.base.io.LicensingFolder;
import org.eclipse.passage.lic.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.base.io.UserHomePath;

final class AccessFiles {

	private final LicensedProduct product;
	private final Supplier<Path> source;

	AccessFiles(LicensedProduct product, Supplier<Path> source) {
		this.product = product;
		this.source = source;
	}

	public AccessFiles(LicensedProduct product) {
		this(product, new LicensingFolder(new UserHomePath()));
	}

	public Collection<Path> get() throws LicensingException {
		return new FileCollection(//
				new PathFromLicensedProduct(source, product),
				new FloatingFileExtension.FloatingLicenseAccessEncrypted()//
		).get();
	}

}
