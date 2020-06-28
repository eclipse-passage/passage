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
package org.eclipse.passage.lic.internal.base.io;

import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.LicensedProduct;

public final class FileNameFromLicensedProduct implements Supplier<String> {

	private final LicensedProduct product;
	private final String extension;

	public FileNameFromLicensedProduct(LicensedProduct product, String extension) {
		this.product = product;
		this.extension = extension;
	}

	@Override
	public String get() {
		return String.format("%s_%s%s", //$NON-NLS-1$
				product.identifier(), //
				product.version(), //
				extension);
	}

}
