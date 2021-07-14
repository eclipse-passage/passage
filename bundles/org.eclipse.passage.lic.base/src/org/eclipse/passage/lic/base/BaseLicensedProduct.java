/*******************************************************************************
 * Copyright (c) 2018, 2021 ArSysOp
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

import java.util.Objects;

import org.eclipse.passage.lic.api.LicensedProduct;

/**
 * Default data-driven implementation of {@code LicensedProduct} represents
 * configuration of the running product. True {@code data-class}.
 * 
 * @see LicensedProduct
 * @see ProductIdentifier
 * @see ProductVersion
 * 
 * @since 2.1
 */
public final class BaseLicensedProduct implements LicensedProduct {

	private final String identifier;
	private final String version;

	public BaseLicensedProduct(String product, String version) {
		Objects.requireNonNull(product, "BaseLicensedProduct::product"); //$NON-NLS-1$
		Objects.requireNonNull(version, "BaseLicensedProduct::version"); //$NON-NLS-1$
		this.identifier = product;
		this.version = version;
	}

	@Override
	public String identifier() {
		return identifier;
	}

	@Override
	public String version() {
		return version;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identifier, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (!LicensedProduct.class.isInstance(obj)) {
			return false;
		}
		LicensedProduct other = (LicensedProduct) obj;
		if (!Objects.equals(identifier, other.identifier())) {
			return false;
		}
		if (!Objects.equals(version, other.version())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		new NamedData.Writable<String>(new ProductIdentifier(identifier)).write(output);
		output.append(';');
		new NamedData.Writable<String>(new ProductVersion(version)).write(output);
		return output.toString();
	}

}
