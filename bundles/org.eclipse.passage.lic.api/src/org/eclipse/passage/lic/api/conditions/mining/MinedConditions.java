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
package org.eclipse.passage.lic.api.conditions.mining;

import java.util.Collection;

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.Condition;
import org.eclipse.passage.lic.api.conditions.ConditionMiningTarget;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.registry.Service;

/**
 * <p>
 * Condition mining is driven by a {@linkplain LicensedProduct} configuration:
 * having coordinates for a product under licensing, miner tries to get all
 * {@linkplain Condition}s, under which the product can be used.
 * </p>
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
 * 
 * @since 2.1
 */
public interface MinedConditions extends Service<ConditionMiningTarget> {

	/**
	 * <p>
	 * Triggers a mining process (caching possible, but not mandatory) for declared
	 * licensing {@linkplain Condition}s over particular set of sources.
	 * </p>
	 * <p>
	 * The mining fails with a {@code ConditionMiningException} in case of any
	 * undeniable misbehavior of a mining infrastructure: whether it is a floating
	 * license server protocol discrepancy, a file system failure, heavy lack of
	 * configuration or other state that makes the mining itself impossible.
	 * </p>
	 * <p>
	 * If, on the scape of full functioning infrastructure, a particular conditions
	 * source is failed to be processed, then this local failure is {@code handled}
	 * (logged or listened in other way the particular implementation provides). No
	 * exception is thrown in this case to make other sources scanning possible.
	 * </p>
	 * 
	 * @return all the conditions gained during the mining for the given
	 *         {@code product}.
	 */
	ServiceInvocationResult<Collection<ConditionPack>> all(LicensedProduct product);

}
