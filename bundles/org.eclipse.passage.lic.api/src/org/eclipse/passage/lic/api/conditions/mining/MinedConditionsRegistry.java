/*******************************************************************************
 * Copyright (c) 2020, 2023 ArSysOp
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
package org.eclipse.passage.lic.api.conditions.mining;

import java.util.function.Supplier;

import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.registry.Registry;

/**
 * @since 2.1
 */
public interface MinedConditionsRegistry extends Supplier<Registry<ConditionMiningTarget, MinedConditions>> {

}
