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
package org.eclipse.passage.lic.internal.json;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.passage.lic.api.access.FeaturePermission;

public class FeaturePermissionAggregator {

	private final List<FeaturePermissionMixIn> featurePermissions = new ArrayList<>();

	public void addFeaturePermission(FeaturePermission d) {
		FeaturePermissionMixIn mixIn = FeaturePermissionMixIn.create(d);
		featurePermissions.add(mixIn);
	}

	public Iterable<FeaturePermissionMixIn> getFeaturePermissions() {
		return featurePermissions;
	}

}
