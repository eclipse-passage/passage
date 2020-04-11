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

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.passage.lic.api.LicensingReporter;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.inspector.HardwareInspector;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.SystemReporter;
import org.eclipse.passage.lic.internal.oshi.i18n.OshiMessages;
import org.eclipse.passage.lic.oshi.OshiHal;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Firmware;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystemVersion;

@Component
public class OshiHardwareInspector implements HardwareInspector {

	private final Map<String, String> hardwareProperties = new LinkedHashMap<>();
	private LicensingReporter licensingReporter = SystemReporter.INSTANCE;

	@Activate
	public void activate() {
		initHardwareProperties();
	}

	private void initHardwareProperties() {
		try {
			SystemInfo systemInfo = new SystemInfo();
			OperatingSystem os = systemInfo.getOperatingSystem();
			hardwareProperties.put(HardwareInspector.PROPERTY_OS_MANUFACTURER, os.getManufacturer());
			hardwareProperties.put(HardwareInspector.PROPERTY_OS_FAMILY, os.getFamily());
			OperatingSystemVersion version = os.getVersion();
			hardwareProperties.put(HardwareInspector.PROPERTY_OS_VERSION, version.getVersion());
			hardwareProperties.put(HardwareInspector.PROPERTY_OS_BUILDNUMBER, version.getBuildNumber());

			HardwareAbstractionLayer hal = systemInfo.getHardware();
			ComputerSystem computerSystem = hal.getComputerSystem();
			hardwareProperties.put(HardwareInspector.PROPERTY_SYSTEM_MANUFACTURER, computerSystem.getManufacturer());
			hardwareProperties.put(HardwareInspector.PROPERTY_SYSTEM_MODEL, computerSystem.getModel());
			hardwareProperties.put(HardwareInspector.PROPERTY_SYSTEM_SERIALNUMBER, computerSystem.getSerialNumber());

			Baseboard baseboard = computerSystem.getBaseboard();
			hardwareProperties.put(HardwareInspector.PROPERTY_BASEBOARD_MANUFACTURER, baseboard.getManufacturer());
			hardwareProperties.put(HardwareInspector.PROPERTY_BASEBOARD_MODEL, baseboard.getModel());
			hardwareProperties.put(HardwareInspector.PROPERTY_BASEBOARD_VERSION, baseboard.getVersion());
			hardwareProperties.put(HardwareInspector.PROPERTY_BASEBOARD_SERIALNUMBER, baseboard.getSerialNumber());

			Firmware firmware = computerSystem.getFirmware();
			hardwareProperties.put(HardwareInspector.PROPERTY_FIRMWARE_MANUFACTURER, firmware.getManufacturer());
			hardwareProperties.put(HardwareInspector.PROPERTY_FIRMWARE_VERSION, firmware.getVersion());
			hardwareProperties.put(HardwareInspector.PROPERTY_FIRMWARE_RELEASEDATE, firmware.getReleaseDate());
			hardwareProperties.put(HardwareInspector.PROPERTY_FIRMWARE_NAME, firmware.getName());
			hardwareProperties.put(HardwareInspector.PROPERTY_FIRMWARE_DESCRIPTION, firmware.getDescription());

			CentralProcessor processor = hal.getProcessor();
			hardwareProperties.put(HardwareInspector.PROPERTY_CPU_VENDOR, processor.getVendor());
			hardwareProperties.put(HardwareInspector.PROPERTY_CPU_FAMILY, processor.getFamily());
			hardwareProperties.put(HardwareInspector.PROPERTY_CPU_MODEL, processor.getModel());
			hardwareProperties.put(HardwareInspector.PROPERTY_CPU_NAME, processor.getName());
			hardwareProperties.put(HardwareInspector.PROPERTY_CPU_IDENTIFIER, processor.getIdentifier());
			hardwareProperties.put(HardwareInspector.PROPERTY_CPU_PROCESSORID, processor.getProcessorID());

			HWDiskStore[] diskStores = hal.getDiskStores();
			for (HWDiskStore hwDiskStore : diskStores) {
				hardwareProperties.put(HardwareInspector.PROPERTY_HWDISK_MODEL, hwDiskStore.getModel());
				hardwareProperties.put(HardwareInspector.PROPERTY_HWDISK_NAME, hwDiskStore.getName());
				hardwareProperties.put(HardwareInspector.PROPERTY_HWDISK_SERIAL, hwDiskStore.getSerial());
				break;
			}
		} catch (Exception | UnsatisfiedLinkError | NoClassDefFoundError e) {
			String source = getClass().getName();
			LicensingResult result = LicensingResults.createError(OshiMessages.OshiHardwareInspector_e_reading_hw,
					source, e);
			licensingReporter.logResult(result);
		}
	}

	@Reference
	public void bindLicensingReporter(LicensingReporter reporter) {
		this.licensingReporter = reporter;
	}

	public void unbindLicensingReporter(LicensingReporter reporter) {
		if (licensingReporter == reporter) {
			this.licensingReporter = SystemReporter.INSTANCE;
		}
	}

	@Override
	public void dumpHardwareInfo(OutputStream output) throws IOException {
		OshiHal.dumpHardwareInfo(output, hardwareProperties);
	}

	@Override
	public String inspectProperty(String name) {
		return hardwareProperties.get(name);
	}

	@Override
	public Iterable<String> getKnownProperties() {
		return hardwareProperties.keySet();
	}

}
