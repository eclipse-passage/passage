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
package org.eclipse.passage.lic.api.access;

/**
 * Takes care of all {@link PermissionEmitter} services registered at runtime.
 *
 * @since 0.4.0
 */
public interface PermissionEmitterRegistry {

	/**
	 * @since 0.4.0
	 */
	String getDefaultConditionType();

	/**
	 * @since 0.4.0
	 */
	Iterable<String> getSupportedConditionTypes();

	/**
	 * @since 0.4.0
	 */
	String getConditionTypeName(String conditionType);

	/**
	 * @since 0.4.0
	 */
	String getConditionTypeDescription(String conditionType);

	/**
	 * @since 0.4.0
	 */
	PermissionEmitter getPermissionEmitter(String conditionType);

	/**
	 * @since 0.4.0
	 */
	Iterable<? extends PermissionEmitter> getPermissionEmitters();

}
