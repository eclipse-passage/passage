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
package org.eclipse.passage.lic.api.conditions.evaluation;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.eclipse.passage.lic.api.conditions.ConditionPack;

/**
 * Aggregates results of a {@linkplain ConditionPack}'s successful evaluation.
 * 
 * @since 2.1
 */
public final class Emission {

	private final ConditionPack pack;
	private final Collection<Permission> permissions;

	public Emission(ConditionPack pack) {
		this(pack, Collections.emptyList());
	}

	public Emission(ConditionPack pack, Permission permission) {
		this(pack, Collections.singleton(permission));
	}

	public Emission(ConditionPack pack, Collection<Permission> permissions) {
		Objects.requireNonNull(pack, "Emission::pack"); //$NON-NLS-1$
		Objects.requireNonNull(permissions, "Emission::permissions"); //$NON-NLS-1$
		this.pack = pack;
		this.permissions = permissions;
		if (permissions.stream().anyMatch(Objects::isNull)) {
			throw new IllegalArgumentException("Null permissions have no sence"); //$NON-NLS-1$
		}
	}

	public final ConditionPack conditionPack() {
		return pack;
	}

	public Collection<Permission> permissions() {
		return permissions;
	}
}