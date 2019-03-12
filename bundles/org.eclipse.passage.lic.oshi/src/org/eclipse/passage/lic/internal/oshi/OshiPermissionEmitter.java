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
package org.eclipse.passage.lic.internal.oshi;

import org.eclipse.passage.lic.base.LicensingProperties;
import org.eclipse.passage.lic.base.access.BasePermissionEmitter;
import org.eclipse.passage.lic.oshi.OshiHal;
import org.eclipse.passage.lic.runtime.access.PermissionEmitter;
import org.osgi.service.component.annotations.Component;

@Component(property = { LicensingProperties.LICENSING_CONDITION_TYPE + '=' + OshiHal.CONDITION_TYPE_HARDWARE })
public class OshiPermissionEmitter extends BasePermissionEmitter implements PermissionEmitter {

	public OshiPermissionEmitter() {
		setConditionName("Hardware");
		setConditionDescription("Evaluates node-locked conditions using runtime hardware information");
	}

	@Override
	protected boolean evaluateSegment(String key, String value) {
		return OshiHal.evaluateProperty(key, value);
	}

}
