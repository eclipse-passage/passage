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

import java.util.Map;

import org.eclipse.passage.lic.runtime.LicensingConfiguration;

public class LicensingConfigurations {

	public static final String LICENSING_PRODUCT_IDENTIFIER = "licensing.product.identifier"; //$NON-NLS-1$
	public static final String LICENSING_PRODUCT_VERSION = "licensing.product.version"; //$NON-NLS-1$
	public static final String LICENSING_PRODUCT_CONTACTS = "licensing.product.contacts"; //$NON-NLS-1$

	public static final String IDENTIFIER_INVALID = "org.eclipse.passage.lic.runtime.invalid"; //$NON-NLS-1$

	public static final LicensingConfiguration INVALID = new BaseLicensingConfiguration(IDENTIFIER_INVALID,
			LicensingVersions.VERSION_DEFAULT);

	public static LicensingConfiguration create(String product, String version) {
		return new BaseLicensingConfiguration(String.valueOf(product), String.valueOf(version));
	}

	public static LicensingConfiguration create(Map<String, Object> properties) {
		String product = String.valueOf(properties.get(LICENSING_PRODUCT_IDENTIFIER));
		String version = String.valueOf(properties.get(LICENSING_PRODUCT_VERSION));
		return new BaseLicensingConfiguration(product, version);
	}

}
