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

import java.util.function.Function;

import org.eclipse.passage.lic.internal.base.BaseNamedData;

/**
 * @since 1.0
 */
public final class LicenseTaken extends BaseNamedData<Integer> {

	public LicenseTaken(Function<String, Integer> retrieve) {
		super(retrieve);
	}

	@Override
	public String key() {
		return "taken"; //$NON-NLS-1$
	}

}
