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

import org.eclipse.passage.lic.api.io.EncryptionKeySize;
import org.eclipse.passage.lic.base.BaseNamedData;

/**
 * @since 2.1
 */
public final class SecurityKeySize extends BaseNamedData<EncryptionKeySize> {

	public SecurityKeySize(Function<String, EncryptionKeySize> retrieve) {
		super(retrieve);
	}

	public SecurityKeySize(Map<String, Object> container) {
		super(key -> Optional.ofNullable(container.get(key))//
				.filter(Integer.class::isInstance) //
				.map(Integer.class::cast) //
				.map(EncryptionKeySize.Of::new)//
				.map(EncryptionKeySize.class::cast) // for compiler
				.orElseGet(EncryptionKeySize.Default::new));
	}

	public SecurityKeySize(EncryptionKeySize value) {
		super(key -> value);
	}

	public SecurityKeySize(int size) {
		super(key -> new EncryptionKeySize.Of(size));
	}

	@Override
	public String key() {
		return "licensing.security.key.size"; //$NON-NLS-1$
	}

}
