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
package org.eclipse.passage.lic.internal.api.conditions.mining;

import java.util.Collection;

import org.eclipse.passage.lic.internal.api.conditions.Condition;
import org.eclipse.passage.lic.internal.api.registry.Service;
import org.eclipse.passage.lic.internal.api.registry.StringServiceId;

/**
 * <p>
 * The miner to extract {@link Condition}s from different sources like
 * </p>
 * <ul>
 * <li>local file system</li>
 * <li>network server</li>
 * <li>etc</li>
 * </ul>
 * <p>
 * The service is intended to implement the third phase of <i>access cycle</i>.
 * </p>
 */
public interface MinedConditions extends Service<StringServiceId> {

	Collection<Condition> all();

}
