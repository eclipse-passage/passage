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
package org.eclipse.passage.lic.oshi;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.passage.lic.api.inspector.HardwareInspector;

public class OshiHal {

	public static final String CONDITION_TYPE_HARDWARE = "hardware"; //$NON-NLS-1$

	private static Set<String> osProperties = new LinkedHashSet<>();
	private static Set<String> systemProperties = new LinkedHashSet<>();
	private static Set<String> baseboardProperties = new LinkedHashSet<>();
	private static Set<String> firmwareProperties = new LinkedHashSet<>();
	private static Set<String> cpuProperties = new LinkedHashSet<>();
	private static Set<String> hwdiskProperties = new LinkedHashSet<>();

	static {
		osProperties.add(HardwareInspector.PROPERTY_OS_MANUFACTURER);
		osProperties.add(HardwareInspector.PROPERTY_OS_FAMILY);
		osProperties.add(HardwareInspector.PROPERTY_OS_VERSION);
		osProperties.add(HardwareInspector.PROPERTY_OS_BUILDNUMBER);

		systemProperties.add(HardwareInspector.PROPERTY_SYSTEM_MANUFACTURER);
		systemProperties.add(HardwareInspector.PROPERTY_SYSTEM_MODEL);
		systemProperties.add(HardwareInspector.PROPERTY_SYSTEM_SERIALNUMBER);

		baseboardProperties.add(HardwareInspector.PROPERTY_BASEBOARD_MANUFACTURER);
		baseboardProperties.add(HardwareInspector.PROPERTY_BASEBOARD_MODEL);
		baseboardProperties.add(HardwareInspector.PROPERTY_BASEBOARD_VERSION);
		baseboardProperties.add(HardwareInspector.PROPERTY_BASEBOARD_SERIALNUMBER);

		firmwareProperties.add(HardwareInspector.PROPERTY_FIRMWARE_MANUFACTURER);
		firmwareProperties.add(HardwareInspector.PROPERTY_FIRMWARE_VERSION);
		firmwareProperties.add(HardwareInspector.PROPERTY_FIRMWARE_RELEASEDATE);
		firmwareProperties.add(HardwareInspector.PROPERTY_FIRMWARE_NAME);
		firmwareProperties.add(HardwareInspector.PROPERTY_FIRMWARE_DESCRIPTION);

		cpuProperties.add(HardwareInspector.PROPERTY_CPU_VENDOR);
		cpuProperties.add(HardwareInspector.PROPERTY_CPU_FAMILY);
		cpuProperties.add(HardwareInspector.PROPERTY_CPU_MODEL);
		cpuProperties.add(HardwareInspector.PROPERTY_CPU_NAME);
		cpuProperties.add(HardwareInspector.PROPERTY_CPU_IDENTIFIER);
		cpuProperties.add(HardwareInspector.PROPERTY_CPU_PROCESSORID);

		hwdiskProperties.add(HardwareInspector.PROPERTY_HWDISK_MODEL);
		hwdiskProperties.add(HardwareInspector.PROPERTY_HWDISK_NAME);
		hwdiskProperties.add(HardwareInspector.PROPERTY_HWDISK_SERIAL);
	}

	private OshiHal() {
		// block
	}

	public static void dumpHardwareInfo(OutputStream output, Map<String, String> values) throws IOException {
		dumpOperatingSystem(output, values);
		output.write('\n');

		dumpComputerSystem(output, values);
		output.write('\n');

		dumpCentralProcessor(output, values);
		output.write('\n');

		dumpDiskStores(output, values);
	}

	public static void dumpOperatingSystem(OutputStream output, Map<String, String> values) throws IOException {
		dumpProperties(output, osProperties, values);
	}

	public static void dumpComputerSystem(OutputStream output, Map<String, String> values) throws IOException {
		dumpProperties(output, systemProperties, values);
		dumpProperties(output, baseboardProperties, values);
		dumpProperties(output, firmwareProperties, values);
	}

	public static void dumpCentralProcessor(OutputStream output, Map<String, String> values) throws IOException {
		dumpProperties(output, cpuProperties, values);
	}

	public static void dumpDiskStores(OutputStream output, Map<String, String> values) throws IOException {
		dumpProperties(output, hwdiskProperties, values);
	}

	private static void dumpProperties(OutputStream output, Iterable<String> keys, Map<String, String> values)
			throws IOException {
		for (String key : keys) {
			String value = values.get(key);
			if (value == null) {
				continue;
			}
			dumpProperty(output, key, value);
		}
	}

	private static void dumpProperty(OutputStream output, String name, String value) throws IOException {
		output.write(name.getBytes());
		output.write('=');
		output.write(value.getBytes());
		output.write('\n');
	}

}
