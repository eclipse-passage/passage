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

	public static final LicensingConfiguration INVALID = new BaseLicensingConfiguration("invalid",
			LicensingVersions.VERSION_DEFAULT);

	public static LicensingConfiguration create(String product, String version) {
		return new BaseLicensingConfiguration(String.valueOf(product), String.valueOf(version));
	}

	public static LicensingConfiguration create(Map<String, Object> properties) {
		String product = String.valueOf(properties.get(LicensingProperties.LICENSING_PRODUCT_IDENTIFIER));
		String version = String.valueOf(properties.get(LicensingProperties.LICENSING_PRODUCT_VERSION));
		return new BaseLicensingConfiguration(product, version);
	}

	public static String findProductIdentifier(String[] args) {
		if (args == null) {
			return null;
		}
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if ("-product".equals(arg)) {
				int index = i + 1;
				if (index < args.length) {
					return args[index];
				}

			}
		}
		return null;
	}

}
