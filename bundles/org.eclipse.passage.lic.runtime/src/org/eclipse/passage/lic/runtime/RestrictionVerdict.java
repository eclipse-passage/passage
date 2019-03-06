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
package org.eclipse.passage.lic.runtime;

/**
 * 
 * The data required to execute the permission, produced by
 * {@link PermissionExaminer} and consumed by {@link RestrictionExecutor}
 *
 */
public interface RestrictionVerdict {

	LicensingRequirement getConfigurationRequirement();

	String getRestrictionLevel();

	int getRestrictionCode();

}
