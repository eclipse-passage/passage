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
package org.eclipse.passage.lic.integration.tests.permissionobservatory;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.passage.lic.api.LicensingConfiguration;
import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.access.FeaturePermission;
import org.eclipse.passage.lic.api.access.PermissionEmitter;
import org.eclipse.passage.lic.api.conditions.LicensingCondition;

/**
 * Without any questions and regrets we lease a 2-seconds-TTL
 * {@linkplain FakePermission} for each incoming {@linkplain LicensingCondition}
 * 
 * @since 0.6
 */
class FakePermissionEmitter implements PermissionEmitter {

	@Override
	public Iterable<FeaturePermission> emitPermissions(LicensingConfiguration configuration,
			Iterable<LicensingCondition> conditions) throws LicensingException {
		return StreamSupport.stream(conditions.spliterator(), false) //
				.map(c -> new FakePermission(configuration, c, 2)) // 2 seconds TTL
				.collect(Collectors.toList());
	}

}
