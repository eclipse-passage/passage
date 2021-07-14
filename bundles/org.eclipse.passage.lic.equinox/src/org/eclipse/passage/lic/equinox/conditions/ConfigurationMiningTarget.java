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
package org.eclipse.passage.lic.equinox.conditions;

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;

/**
 * @since 2.1
 */
public final class ConfigurationMiningTarget implements Supplier<ConditionMiningTarget> {

	@Override
	public ConditionMiningTarget get() {
		return new ConditionMiningTarget.Local().child("configuration-conditions"); //$NON-NLS-1$
	}

}
