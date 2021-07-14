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
package org.eclipse.passage.lic.base;

import org.eclipse.passage.lic.api.LicensedProduct;

/**
 * {@linkplain LicensedProduct} is a key piece of data for an
 * {@code access cycle}. For the cases of any sabotage is detected this <i>
 * knowingly invalid</i> configuration is used to make an {@code access cycle}
 * fail.
 * 
 * @since 2.1
 */
public final class InvalidLicensedProduct implements LicensedProduct {

	private final BaseLicensedProduct delegate;

	public InvalidLicensedProduct() {
		delegate = new BaseLicensedProduct(//
				"org.eclipse.passage.lic.api.configuration.invalid", //$NON-NLS-1$ ,
				"0.0.0"); //$NON-NLS-1$
	}

	@Override
	public String identifier() {
		return delegate.identifier();
	}

	@Override
	public String version() {
		return delegate.version();
	}

	@Override
	public int hashCode() {
		return delegate.identifier().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!LicensedProduct.class.isInstance(obj)) {
			return false;
		}
		return delegate.identifier().equals(((LicensedProduct) obj).identifier());
	}

	@Override
	public String toString() {
		return delegate.toString();
	}

}
