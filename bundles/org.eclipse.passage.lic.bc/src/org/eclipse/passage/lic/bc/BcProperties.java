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
package org.eclipse.passage.lic.bc;

import java.util.Map;

import org.eclipse.passage.lic.base.LicensingProperties;

public class BcProperties {
	
	public static final String KEY_ALGO_DEFAULT = LicensingProperties.LICENSING_SECURITY_KEY_ALGO_RSA;
	public static final int KEY_SIZE_DEFAULT = LicensingProperties.LICENSING_SECURITY_KEY_SIZE_1024;

	private BcProperties() {
		//block
	}
	
	public static String extractKeyAlgo(Map<String, Object> properties) {
		if (properties == null) {
			return KEY_ALGO_DEFAULT;
		}
		Object object = properties.get(LicensingProperties.LICENSING_SECURITY_KEY_ALGO);
		if (object instanceof String) {
			String algo = (String) object;
			return algo;
		}
		return KEY_ALGO_DEFAULT;
	}

	public static int extractKeySize(Map<String, Object> properties) {
		if (properties == null) {
			return KEY_SIZE_DEFAULT;
		}
		Object object = properties.get(LicensingProperties.LICENSING_SECURITY_KEY_SIZE);
		if (object instanceof Integer) {
			Integer size = (Integer) object;
			return size.intValue();
		}
		return KEY_SIZE_DEFAULT;
	}

}
