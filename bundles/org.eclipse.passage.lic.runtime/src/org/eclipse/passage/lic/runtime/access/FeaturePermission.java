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
package org.eclipse.passage.lic.runtime.access;

import java.util.Date;

import org.eclipse.passage.lic.api.conditions.LicensingCondition;
import org.eclipse.passage.lic.runtime.LicensingConfiguration;

/**
 * Permission to use the component of given name and version range obtained from
 * {@link PermissionEmitter} as a result of evaluation for
 * {@link LicensingCondition}
 */
public interface FeaturePermission {

	LicensingConfiguration getLicensingConfiguration();

	LicensingCondition getLicensingCondition();

	Date getLeaseDate();

	Date getExpireDate();
}
