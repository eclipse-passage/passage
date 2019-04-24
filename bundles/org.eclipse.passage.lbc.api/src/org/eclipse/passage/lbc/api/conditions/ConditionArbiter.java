/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lbc.api.conditions;

import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.conditions.ConditionActions;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;

/**
 * Performs {@link ConditionActions} on {@link LicensingCondition}(s)
 * 
 * @since 0.5.0
 *
 */
public interface ConditionArbiter {

	/**
	 * 
	 * @see ConditionActions#ACQUIRE
	 * @param conditions The {@link LicensingCondition}(s) to be acquired
	 * @return the result of operation
	 */
	LicensingResult acquireConditions(Iterable<LicensingCondition> conditions);

	/**
	 * 
	 * @see ConditionActions#KEEP
	 * @param conditions The {@link LicensingCondition}(s) to be kept
	 * @return the result of operation
	 */
	LicensingResult keepConditions(Iterable<LicensingCondition> conditions);

	/**
	 * 
	 * @see ConditionActions#RELEASE
	 * @param conditions The {@link LicensingCondition}(s) to be released
	 * @return the result of operation
	 */
	LicensingResult releaseConditions(Iterable<LicensingCondition> conditions);

}
