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

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONDITION_TYPE_DESCRIPTION;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONDITION_TYPE_ID;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONDITION_TYPE_NAME;

import org.eclipse.passage.lic.api.access.PermissionEmitter;
import org.eclipse.passage.lic.base.access.BasePermissionEmitter;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.oshi.OshiHal;
import org.eclipse.passage.lic.runtime.inspector.HardwareInspector;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(property = { LICENSING_CONDITION_TYPE_ID + '=' + OshiHal.CONDITION_TYPE_HARDWARE,
		LICENSING_CONDITION_TYPE_NAME + '=' + "Hardware", LICENSING_CONDITION_TYPE_DESCRIPTION + '='
				+ "Evaluates node-locked conditions using runtime hardware information" })
public class OshiPermissionEmitter extends BasePermissionEmitter implements PermissionEmitter {

	private HardwareInspector hardwareInspector;

	public OshiPermissionEmitter() {
	}

	@Reference
	public void bindHardwareInspector(HardwareInspector inspector) {
		hardwareInspector = inspector;
	}

	public void unbindHardwareInspector(HardwareInspector inspector) {
		if (hardwareInspector == inspector) {
			hardwareInspector = null;
		}
	}

	@Override
	protected boolean evaluateSegment(String key, String expected) {
		String actual = hardwareInspector.inspectProperty(key);
		return LicensingConditions.evaluateSegmentValue(expected, actual);
	}

}
