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

import org.eclipse.passage.lic.runtime.LicensingConfiguration;
import org.eclipse.passage.lic.runtime.LicensingException;
import org.eclipse.passage.lic.runtime.conditions.LicensingCondition;

/**
 * Evaluates the collection of {@link LicensingCondition} to emit a collection
 * of {@link FeaturePermission}
 *
 */
public interface PermissionEmitter {

	Iterable<FeaturePermission> emitPermissions(Iterable<LicensingCondition> conditions,
			LicensingConfiguration configuration) throws LicensingException;

}
