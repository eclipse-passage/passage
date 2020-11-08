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
package org.eclipse.passage.lic.internal.hc.remote.impl;

import java.nio.file.Path;
import java.util.Collection;

import org.eclipse.passage.lic.floating.FloatingFileExtensions;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.base.io.FileCollection;
import org.eclipse.passage.lic.internal.base.io.PathFromLicensedProduct;
import org.eclipse.passage.lic.internal.base.io.UserHomePath;

final class AccessFiles {
	private final LicensedProduct product;

	public AccessFiles(LicensedProduct product) {
		this.product = product;
	}

	public Collection<Path> get() throws LicensingException {
		return new FileCollection(//
				new PathFromLicensedProduct(new UserHomePath(), product),
				new FloatingFileExtensions.LicenseAccessEncrypted()//
		).get();
	}

}
