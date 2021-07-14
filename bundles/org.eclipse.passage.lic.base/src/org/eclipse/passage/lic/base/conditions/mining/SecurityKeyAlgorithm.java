/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.base.conditions.mining;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.passage.lic.api.io.EncryptionAlgorithm;
import org.eclipse.passage.lic.base.BaseNamedData;

/**
 * @since 2.1
 */
public final class SecurityKeyAlgorithm extends BaseNamedData<EncryptionAlgorithm> {

	public SecurityKeyAlgorithm(Function<String, EncryptionAlgorithm> retrieve) {
		super(retrieve);
	}

	public SecurityKeyAlgorithm(Map<String, Object> container) {
		super(key -> Optional.ofNullable(container.get(key))//
				.map(String::valueOf) //
				.map(String::trim) //
				.filter(value -> !value.isEmpty())//
				.map(EncryptionAlgorithm.Of::new)//
				.map(EncryptionAlgorithm.class::cast) // just for compiler
				.orElseGet(EncryptionAlgorithm.Default::new));
	}

	public SecurityKeyAlgorithm(EncryptionAlgorithm value) {
		super(key -> value);
	}

	public SecurityKeyAlgorithm(String algorithm) {
		super(key -> new EncryptionAlgorithm.Of(algorithm));
	}

	@Override
	public String key() {
		return "licensing.security.key.algo"; //$NON-NLS-1$
	}

}
