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
 *     Nikifor Fedorov <zelenyhleb@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.loc.internal.billing.core;

import java.util.Objects;

import org.eclipse.passage.lic.users.UserLicenseDescriptor;

/**
 * Local data class designed to sort licenses by product and version.
 * 
 * @since 0.1
 */
public final class ProductVersionLicense {

	private final String product;
	private final String version;

	/**
	 * @param license license descriptor (must not be null)
	 */
	public ProductVersionLicense(UserLicenseDescriptor license) {
		Objects.requireNonNull(license);
		this.product = Objects.requireNonNull(license.getProductIdentifier());
		this.version = Objects.requireNonNull(license.getProductVersion());
	}

	public String getProduct() {
		return product;
	}

	public String getVersion() {
		return version;
	}

	@Override
	public boolean equals(Object obj) {
		if (!this.getClass().isInstance(obj)) {
			return false;
		}
		ProductVersionLicense license = (ProductVersionLicense) obj;
		return Objects.equals(getProduct(), license.getProduct()) && Objects.equals(getVersion(), license.getVersion());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getProduct(), getVersion());
	}

	@Override
	public String toString() {
		return getProduct() + ":" + getVersion(); //$NON-NLS-1$
	}

}
