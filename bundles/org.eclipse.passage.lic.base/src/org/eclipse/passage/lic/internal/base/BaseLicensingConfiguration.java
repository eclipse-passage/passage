/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.base;

import java.util.Objects;

import org.eclipse.passage.lic.api.LicensingConfiguration;

public final class BaseLicensingConfiguration implements LicensingConfiguration {

	private final String identifier;
	private final String version;

	public BaseLicensingConfiguration(String product, String version) {
		this.identifier = product;
		this.version = version;
	}

	@Override
	public String getProductIdentifier() {
		return identifier;
	}

	@Override
	public String getProductVersion() {
		return version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		if (!Objects.equals(identifier, other.identifier)) {
			return false;
		}
		if (!Objects.equals(version, other.version)) {
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
