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
package org.eclipse.passage.lic.internal.equinox;

import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;

public final class LicensedProductFromContext implements Supplier<LicensedProduct> {

	private final IApplicationContext context;

	public LicensedProductFromContext(IApplicationContext context) {
		Objects.requireNonNull(context);
		this.context = context;
	}

	@Override
	public LicensedProduct get() {
		return new BaseLicensedProduct(//
				new ApplicationIdentifier(context).get(), //
				new ApplicationVersion(context).get());
	}

}
