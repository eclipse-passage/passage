/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.base;

import java.util.Objects;

import org.eclipse.passage.lic.runtime.LicensingConfiguration;

class BaseLicensingConfiguration implements LicensingConfiguration {

	private final String productIdentifier;
	private final String productVersion;

	BaseLicensingConfiguration(String product, String version) {
		super();
		this.productIdentifier = product;
		this.productVersion = version;
	}

	@Override
	public String getProductIdentifier() {
		return productIdentifier;
	}

	@Override
	public String getProductVersion() {
		return productVersion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productIdentifier == null) ? 0 : productIdentifier.hashCode());
		result = prime * result + ((productVersion == null) ? 0 : productVersion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BaseLicensingConfiguration other = (BaseLicensingConfiguration) obj;
		if (!Objects.equals(productIdentifier, other.productIdentifier)) {
			return false;
		}
		if (!Objects.equals(productVersion, other.productVersion)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(LicensingConfigurations.LICENSING_PRODUCT_IDENTIFIER).append('=').append(productIdentifier)
				.append(';');
		sb.append(LicensingConfigurations.LICENSING_PRODUCT_VERSION).append('=').append(productVersion);
		return sb.toString();
	}

}
