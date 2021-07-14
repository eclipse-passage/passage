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
package org.eclipse.passage.lic.internal.json;

import java.util.Collection;

import org.eclipse.passage.lic.api.conditions.Condition;

/**
 * Cover connection of typed elements as our persistence library requires.
 * Intentionally ugly.
 */
public final class ConditionPack {

	Collection<Condition> conditions;

	public ConditionPack(Collection<Condition> conditions) {
		this.conditions = conditions;
	}

	ConditionPack() {
	}

}
