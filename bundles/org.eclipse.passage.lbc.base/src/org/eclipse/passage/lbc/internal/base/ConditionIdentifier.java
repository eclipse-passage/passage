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

import org.eclipse.passage.lic.internal.base.StringNamedData;

/**
 * @since 1.0
 */
public final class ConditionIdentifier extends StringNamedData {

	public ConditionIdentifier(String identifier) {
		super(identifier);
	}

	public ConditionIdentifier(Function<String, String> retrieve) {
		super(retrieve);
	}

	@Override
	public final String key() {
		return "identifier"; //$NON-NLS-1$
	}

}
