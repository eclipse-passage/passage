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
package org.eclipse.passage.lic.base;

import java.util.Map;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.eclipse.passage.lic.internal.base.NamedData;

/**
 * 
 * @deprecated use {@linkplain NamedData}, {@linkplain ProductInfo} or another
 *             implementations, {@linkplain BaseLicensedProduct}
 */
@Deprecated
public final class LicensingConfigurations {

	public static final String LICENSING_PRODUCT_IDENTIFIER = "licensing.product.identifier"; //$NON-NLS-1$
	public static final String LICENSING_PRODUCT_VERSION = "licensing.product.version"; //$NON-NLS-1$
	public static final String LICENSING_PRODUCT_CONTACTS = "licensing.product.contacts"; //$NON-NLS-1$

	public static final String IDENTIFIER_INVALID = "org.eclipse.passage.lic.api.configuration.invalid"; //$NON-NLS-1$

	public static final LicensingConfiguration INVALID = new LicConfig(IDENTIFIER_INVALID,
			LicensingVersions.VERSION_DEFAULT);

	private LicensingConfigurations() {
		// block
	}

	public static LicensingConfiguration create(String product, String version) {
		return new LicConfig(String.valueOf(product), String.valueOf(version));
	}

	public static LicensingConfiguration create(Map<String, Object> properties) {
		String product = String.valueOf(properties.get(LICENSING_PRODUCT_IDENTIFIER));
		String version = String.valueOf(properties.get(LICENSING_PRODUCT_VERSION));
		return new LicConfig(product, version);
	}

	private static final class LicConfig implements LicensingConfiguration {

		private final String id;
		private final String version;

		private LicConfig(String id, String version) {
			this.id = id;
			this.version = version;
		}

		@Override
		public String getProductIdentifier() {
			return id;
		}

		@Override
		public String getProductVersion() {
			return version;
		}

	}

}
