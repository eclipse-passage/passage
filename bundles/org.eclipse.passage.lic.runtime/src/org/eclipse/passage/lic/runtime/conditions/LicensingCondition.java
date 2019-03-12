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
package org.eclipse.passage.lic.runtime.conditions;

import java.util.Date;

import org.eclipse.passage.lic.runtime.access.PermissionEmitter;

/**
 * 
 * Defines the condition to be evaluated by {@link PermissionEmitter} <br/>
 * Obtained from {@link ConditionMiner}
 *
 */
public interface LicensingCondition {

	String getFeatureIdentifier();

	String getMatchVersion();

	String getMatchRule();

	Date getValidFrom();

	Date getValidUntil();

	/**
	 * the type of condition like "time" or "hardware"
	 * 
	 * @return
	 */
	String getConditionType();

	String getConditionExpression();

}
