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

import org.eclipse.passage.lic.api.LicensedProduct;
import org.eclipse.passage.lic.api.ServiceInvocationResult;
import org.eclipse.passage.lic.api.conditions.ConditionPack;
import org.eclipse.passage.lic.api.registry.Service;
import org.eclipse.passage.lic.api.registry.StringServiceId;

/**
 * @since 2.1
 */
public interface PermissionEmittingService extends Service<StringServiceId> {

	/**
	 * <p>
	 * Each of the given {@code condition pack} is treated as an altogether
	 * undeniable set of demands. For each of the {@code conditions} in a pack one's
	 * {@code expression} is <i>evaluated</i> according to the condition's
	 * evaluation type and other data.
	 * </p>
	 * <p>
	 * <i>Evaluation</i> means assessing the {@code expression}'s statements against
	 * the surrounding runtime infrastructure. For instance, if
	 * {@code hardware expression} claims that hard disk serial number must be of
	 * certain value, the evaluation gains the actual hard disk parameters and tells
	 * precisely if this claim is satisfied by the environment or not. Thus,
	 * <i>evaluation</i> sums up to a boolean function on the given condition's
	 * {@code expression}.
	 * </p>
	 * <p>
	 * If {@code expression}s of all the given {@code condition}s are evaluated
	 * positively, {@linkplain Permission}s are emitted for 'em and stored in the
	 * returned {@linkplain Emission} object.
	 * </p>
	 * <p>
	 * If <i>evaluation</i> fails for any of the {@code condition}s from he given
	 * set (ended up in negative diagnose or a crush), then the failure details are
	 * reported in the returned {@linkplain Emission} instance.
	 * </p>
	 * <p>
	 * Single {@linkplain Emission} is leased for each incoming
	 * {@linkplain ConditionPack}.
	 * </p>
	 */
	ServiceInvocationResult<Collection<Emission>> emit(Collection<ConditionPack> conditions, LicensedProduct product);

}
