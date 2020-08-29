/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lbc.internal.base;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.base.BaseNamedData;

/**
 * @since 1.0
 */
public final class LicenseCapacity extends BaseNamedData<Integer> {

	public LicenseCapacity(Function<String, Integer> retrieve) {
		super(retrieve);
	}

	public LicenseCapacity(int value) {
		super((key) -> value);
	}

	public LicenseCapacity(Supplier<Optional<Integer>> existing) {
		this(existing.get().get());
	}

	@Override
	public String key() {
		return "capacity"; //$NON-NLS-1$
	}

}
