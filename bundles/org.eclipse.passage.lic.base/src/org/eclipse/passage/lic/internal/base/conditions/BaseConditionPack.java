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
package org.eclipse.passage.lic.internal.base.conditions;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;

public final class BaseConditionPack implements ConditionPack {

	private final String origin;
	private final Collection<Condition> conditions;

	public BaseConditionPack(String origin, Collection<Condition> conditions) {
		Objects.requireNonNull(origin, "BaseConditionPack::origin"); //$NON-NLS-1$
		Objects.requireNonNull(conditions, "BaseConditionPack::conditions"); //$NON-NLS-1$
		this.origin = origin;
		this.conditions = new ArrayList<>(conditions);
	}

	/**
	 * Convenience constructor for a pack begotten by a (license) file.
	 */
	public BaseConditionPack(Path origin, Collection<Condition> conditions) {
		this(origin.normalize().toAbsolutePath().toString(), conditions);
	}

	@Override
	public String origin() {
		return origin;
	}

	@Override
	public Collection<Condition> conditions() {
		return conditions;
	}

}
