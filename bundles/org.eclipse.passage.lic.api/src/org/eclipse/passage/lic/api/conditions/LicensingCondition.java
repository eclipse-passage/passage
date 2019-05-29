/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
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
package org.eclipse.passage.lic.api.conditions;

import java.util.Date;

import org.eclipse.passage.lic.api.access.PermissionEmitter;

/**
 * 
 * Defines the condition to be evaluated by {@link PermissionEmitter} <br/>
 * Obtained from {@link ConditionMiner}
 * 
 * @since 0.5.0
 *
 */
public interface LicensingCondition {

	String getFeatureIdentifier();

	String getMatchVersion();

	String getMatchRule();

	/**
	 * Returns the validity period start date of this licensing condition. This is
	 * the value of its <code>"validFrom"</code> attribute.
	 *
	 * @return the valid from
	 */
	Date getValidFrom();

	/**
	 * Returns the validity period end date of this licensing condition. This is the
	 * value of its <code>"validUntil"</code> attribute.
	 *
	 * @return the valid until
	 */
	Date getValidUntil();

	/**
	 * the type of condition like "time" or "hardware"
	 * 
	 * @return
	 */
	String getConditionType();

	String getConditionExpression();

}
