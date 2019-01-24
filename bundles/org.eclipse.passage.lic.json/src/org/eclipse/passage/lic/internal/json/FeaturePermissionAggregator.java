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
package org.eclipse.passage.lic.internal.json;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.passage.lic.base.BaseFeaturePermission;

public class FeaturePermissionAggregator {

	private final List<BaseFeaturePermission> featurePermissions = new ArrayList<>();

	public void addFeaturePermission(BaseFeaturePermission p) {
		featurePermissions.add(p);
	}

	public List<BaseFeaturePermission> getFeaturePermissions() {
		return featurePermissions;
	}

}
