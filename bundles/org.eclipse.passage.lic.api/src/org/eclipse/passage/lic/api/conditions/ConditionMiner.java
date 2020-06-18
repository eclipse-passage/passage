/*******************************************************************************
 * Copyright (c) 2018, 2020 ArSysOp
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
package org.eclipse.passage.lic.api.conditions;

import org.eclipse.passage.lic.api.LicensingConfiguration;

/**
 * <p>
 * The miner to extract {@link LicensingCondition}s from different sources like
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
 * @see LicensingCondition
 * @see org.eclipse.passage.lic.api
 * @deprecated use MinedConditions
 * @since 0.4.0
 */
@Deprecated
public interface ConditionMiner {

	/**
	 * Extracts {@link LicensingCondition}s for given {@link LicensingConfiguration}
	 * 
	 * @param configuration the {@link LicensingConfiguration}
	 * @return the {@link Iterable}<{@link LicensingCondition}>, may be empty
	 * 
	 * @since 0.4.0
	 */
	Iterable<LicensingCondition> extractLicensingConditions(LicensingConfiguration configuration);

}
