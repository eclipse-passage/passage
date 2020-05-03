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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.passage.lic.api.LicensingReporter;
import org.eclipse.passage.lic.api.access.PermissionEmitter;
import org.eclipse.passage.lic.api.inspector.HardwareInspector;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.SystemReporter;
import org.eclipse.passage.lic.base.access.BasePermissionEmitter;
import org.eclipse.passage.lic.base.conditions.LicensingConditions;
import org.eclipse.passage.lic.internal.oshi.i18n.OshiMessages;
import org.eclipse.passage.lic.oshi.OshiHal;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;

@Component(property = { LICENSING_CONDITION_TYPE_ID + '=' + OshiHal.CONDITION_TYPE_HARDWARE,
		LICENSING_CONDITION_TYPE_NAME + '=' + "Hardware", LICENSING_CONDITION_TYPE_DESCRIPTION + '='
				+ "Evaluates node-locked conditions using runtime hardware information" })
public class OshiPermissionEmitter extends BasePermissionEmitter implements PermissionEmitter {

	private HardwareInspector hardwareInspector;
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
		hardwareInspector = inspector;
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
			return disks().stream() //
					.anyMatch(disk -> LicensingConditions.evaluateSegmentValue(expected, disk.getSerial()));
		}
		String actual = hardwareInspector.inspectProperty(key);
		return LicensingConditions.evaluateSegmentValue(expected, actual);
	}

	private List<HWDiskStore> disks() {
		try {
			return Arrays.asList(CompletableFuture.supplyAsync(//
					() -> new SystemInfo().getHardware().getDiskStores()).get());
		} catch (InterruptedException | ExecutionException e) {
			licensingReporter.logResult(//
					LicensingResults.createError(//
							OshiMessages.OshiPermissionEmitter_e_retrieve_disks, getClass().getName(), e));
			return Collections.emptyList();
		}
	}

}
