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
package org.eclipse.passage.lic.internal.json;

import org.eclipse.passage.lic.runtime.LicensingConfiguration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

final class LicensingConfigurationMixIn implements LicensingConfiguration {

	static LicensingConfigurationMixIn create(LicensingConfiguration d) {
		String productIdentifier = d.getProductIdentifier();
		String productVersion = d.getProductVersion();
		return new LicensingConfigurationMixIn(productIdentifier, productVersion);
	}

	private final String productIdentifier;
	private final String productVersion;

	@JsonCreator
	LicensingConfigurationMixIn(@JsonProperty("productIdentifier") String productIdentifier,
			@JsonProperty("productVersion") String productVersion) {
		this.productIdentifier = productIdentifier;
		this.productVersion = productVersion;
	}

	@Override
	public String getProductIdentifier() {
		return productIdentifier;
	}

	@Override
	public String getProductVersion() {
		return productVersion;
	}

}