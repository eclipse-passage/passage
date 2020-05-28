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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.passage.lic.api.LicensingReporter;
import org.eclipse.passage.lic.api.LicensingResult;
import org.eclipse.passage.lic.api.inspector.HardwareInspector;
import org.eclipse.passage.lic.base.LicensingResults;
import org.eclipse.passage.lic.base.SystemReporter;
import org.eclipse.passage.lic.internal.oshi.i18n.OshiMessages;
import org.eclipse.passage.lic.oshi.OshiHal;
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

	private final Map<String, String> hardware = new LinkedHashMap<>();
	private final List<HWDiskStore> discs = new ArrayList<>();
	private LicensingReporter licensingReporter = SystemReporter.INSTANCE;

	private Map<String, Object> inspect() {
		Map<String, Object> properties = new LinkedHashMap<>();
		try {
			SystemInfo systemInfo = new SystemInfo();
			OperatingSystem os = systemInfo.getOperatingSystem();
			properties.put(HardwareInspector.PROPERTY_OS_MANUFACTURER, os.getManufacturer());
			properties.put(HardwareInspector.PROPERTY_OS_FAMILY, os.getFamily());
			OperatingSystemVersion version = os.getVersion();
			properties.put(HardwareInspector.PROPERTY_OS_VERSION, version.getVersion());
			properties.put(HardwareInspector.PROPERTY_OS_BUILDNUMBER, version.getBuildNumber());

			HardwareAbstractionLayer hal = systemInfo.getHardware();
			ComputerSystem computerSystem = hal.getComputerSystem();
			properties.put(HardwareInspector.PROPERTY_SYSTEM_MANUFACTURER, computerSystem.getManufacturer());
			properties.put(HardwareInspector.PROPERTY_SYSTEM_MODEL, computerSystem.getModel());
			properties.put(HardwareInspector.PROPERTY_SYSTEM_SERIALNUMBER, computerSystem.getSerialNumber());

			Baseboard baseboard = computerSystem.getBaseboard();
			properties.put(HardwareInspector.PROPERTY_BASEBOARD_MANUFACTURER, baseboard.getManufacturer());
			properties.put(HardwareInspector.PROPERTY_BASEBOARD_MODEL, baseboard.getModel());
			properties.put(HardwareInspector.PROPERTY_BASEBOARD_VERSION, baseboard.getVersion());
			properties.put(HardwareInspector.PROPERTY_BASEBOARD_SERIALNUMBER, baseboard.getSerialNumber());

			Firmware firmware = computerSystem.getFirmware();
			properties.put(HardwareInspector.PROPERTY_FIRMWARE_MANUFACTURER, firmware.getManufacturer());
			properties.put(HardwareInspector.PROPERTY_FIRMWARE_VERSION, firmware.getVersion());
			properties.put(HardwareInspector.PROPERTY_FIRMWARE_RELEASEDATE, firmware.getReleaseDate());
			properties.put(HardwareInspector.PROPERTY_FIRMWARE_NAME, firmware.getName());
			properties.put(HardwareInspector.PROPERTY_FIRMWARE_DESCRIPTION, firmware.getDescription());

			CentralProcessor processor = hal.getProcessor();
			properties.put(HardwareInspector.PROPERTY_CPU_VENDOR, processor.getVendor());
			properties.put(HardwareInspector.PROPERTY_CPU_FAMILY, processor.getFamily());
			properties.put(HardwareInspector.PROPERTY_CPU_MODEL, processor.getModel());
			properties.put(HardwareInspector.PROPERTY_CPU_NAME, processor.getName());
			properties.put(HardwareInspector.PROPERTY_CPU_IDENTIFIER, processor.getIdentifier());
			properties.put(HardwareInspector.PROPERTY_CPU_PROCESSORID, processor.getProcessorID());

			HWDiskStore[] diskStores = hal.getDiskStores();
			properties.put(HWDiskStore[].class.getName(), diskStores);
			for (HWDiskStore hwDiskStore : diskStores) {
				properties.put(HardwareInspector.PROPERTY_HWDISK_MODEL, hwDiskStore.getModel());
				properties.put(HardwareInspector.PROPERTY_HWDISK_NAME, hwDiskStore.getName());
				properties.put(HardwareInspector.PROPERTY_HWDISK_SERIAL, hwDiskStore.getSerial());
				break;
			}
		} catch (Exception | UnsatisfiedLinkError | NoClassDefFoundError e) {
			String source = getClass().getName();
			LicensingResult result = LicensingResults.createError(OshiMessages.OshiHardwareInspector_e_reading_hw,
					source, e);
			licensingReporter.logResult(result);
		}
		return properties;
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
		OshiHal.dumpHardwareInfo(output, hardware());
	}

	@Override
	public String inspectProperty(String name) {
		return hardware().get(name);
	}

	@Override
	public Iterable<String> getKnownProperties() {
		return hardware().keySet();
	}

	private synchronized Map<String, String> hardware() {
		if (hardware.isEmpty()) {
			try {
				CompletableFuture.supplyAsync(this::inspect).get().entrySet().stream()//
						.forEach(this::fill);
			} catch (InterruptedException | ExecutionException e) {
				licensingReporter.logResult(//
						LicensingResults.createError(//
								OshiMessages.OshiHardwareInspector_e_reading_hw, getClass().getName(), e));
			}
		}
		return new LinkedHashMap<String, String>(hardware);
	}

	private void fill(Entry<String, Object> entry) {
		Object value = entry.getValue();
		if (value instanceof String) {
			hardware.put(entry.getKey(), String.class.cast(value));
		} else if (value instanceof HWDiskStore[]) {
			discs.addAll(Arrays.asList(HWDiskStore[].class.cast(value)));
		}

	}

	List<HWDiskStore> disks() {
		hardware();
		return new ArrayList<>(discs);
	}

}
