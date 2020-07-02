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
package org.eclipse.passage.lic.bc;

import java.util.Map;

import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.internal.api.io.EncryptionAlgorithm;
import org.eclipse.passage.lic.internal.api.io.EncryptionKeySize;
import org.eclipse.passage.lic.internal.base.NamedData;
import org.eclipse.passage.lic.internal.base.conditions.mining.SecurityKeyAlgorithm;
import org.eclipse.passage.lic.internal.base.conditions.mining.SecurityKeySize;

/**
 * @deprecated instead of string literals use {@link EncryptionAlgorithm},
 *             {@link EncryptionKeySize} and appropriate {@link NamedData}
 *             implementations: {@link SecurityKeyAlgorithm} and
 *             {@link SecurityKeySize}
 */
@Deprecated
@SuppressWarnings("restriction")
public final class BcProperties {

	/**
	 * @deprecated use {@link EncryptionAlgorithm.Default}
	 */
	@Deprecated
	public static final String KEY_ALGO_DEFAULT = LicensingProperties.LICENSING_SECURITY_KEY_ALGO_RSA;
	/**
	 * @deprecated use {@link EncryptionKeySize.Default}
	 */
	@Deprecated
	public static final int KEY_SIZE_DEFAULT = LicensingProperties.LICENSING_SECURITY_KEY_SIZE_1024;

	private BcProperties() {
		// block
	}

	/**
	 * @deprecated use {@link SecurityKeyAlgorithm} :
	 *             {@code new SecurityKeyAlgorithm(properties)}
	 */
	@Deprecated
	public static String extractKeyAlgo(Map<String, Object> properties) {
		if (properties == null) {
			return KEY_ALGO_DEFAULT;
		}
		Object object = properties.get(LicensingProperties.LICENSING_SECURITY_KEY_ALGO);
		if (object instanceof String) {
			return (String) object;
		}
		return KEY_ALGO_DEFAULT;
	}

	/**
	 * @deprecated use {@link SecurityKeySize} :
	 *             {@code new SecurityKeySize(properties)}
	 */
	@Deprecated
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
