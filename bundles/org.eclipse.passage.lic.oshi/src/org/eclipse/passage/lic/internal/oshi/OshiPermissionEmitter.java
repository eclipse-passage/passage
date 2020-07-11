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
package org.eclipse.passage.lic.internal.oshi;

import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONDITION_TYPE_DESCRIPTION;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONDITION_TYPE_ID;
import static org.eclipse.passage.lic.base.LicensingProperties.LICENSING_CONDITION_TYPE_NAME;

import org.eclipse.passage.lic.api.LicensingReporter;
import org.eclipse.passage.lic.api.access.PermissionEmitter;
import org.eclipse.passage.lic.api.inspector.HardwareInspector;
import org.eclipse.passage.lic.base.SystemReporter;
import org.eclipse.passage.lic.base.access.BasePermissionEmitter;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.oshi.OshiHal;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @deprecated use 1.0 HardwareAssessmentService instead
 */
@Deprecated
@Component(property = { LICENSING_CONDITION_TYPE_ID + '=' + OshiHal.CONDITION_TYPE_HARDWARE,
		LICENSING_CONDITION_TYPE_NAME + '=' + "Hardware", LICENSING_CONDITION_TYPE_DESCRIPTION + '='
				+ "Evaluates node-locked conditions using runtime hardware information" })
public class OshiPermissionEmitter extends BasePermissionEmitter implements PermissionEmitter {

	private OshiHardwareInspector hardwareInspector;
	private LicensingReporter licensingReporter = new SystemReporter();

	@Reference
	public void bindLicensingReporter(LicensingReporter reporter) {
		this.licensingReporter = reporter;
	}

	public void unbindLicensingReporter(LicensingReporter reporter) {
		if (this.licensingReporter == reporter) {
			this.licensingReporter = new SystemReporter();
		}
	}

	@Reference
	public void bindHardwareInspector(HardwareInspector inspector) {
		// FIXME: extend interface in 0.9.0
		hardwareInspector = (OshiHardwareInspector) inspector;
	}

	public void unbindHardwareInspector(HardwareInspector inspector) {
		if (hardwareInspector == inspector) {
			hardwareInspector = null;
		}
	}

	@Override
	protected boolean evaluateSegment(String key, String expected) {
		// FIXME: EP: fast hack for 562012,
		// to be solved properly with formats alternations in 0.9
		if (key.equals(HardwareInspector.PROPERTY_HWDISK_SERIAL)) {
			return hardwareInspector.disks().stream() //
					.anyMatch(disk -> LicensingConditions.evaluateSegmentValue(expected, disk.getSerial()));
		}
		String actual = hardwareInspector.inspectProperty(key);
		return LicensingConditions.evaluateSegmentValue(expected, actual);
	}

}
