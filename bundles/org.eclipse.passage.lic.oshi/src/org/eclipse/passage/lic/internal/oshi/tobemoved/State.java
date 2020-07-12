/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
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
package org.eclipse.passage.lic.internal.oshi.tobemoved;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.inspection.EnvironmentProperty;
import org.eclipse.passage.lic.internal.base.inspection.hardware.BaseBoard;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Computer;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Cpu;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Disk;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Firmware;
import org.eclipse.passage.lic.internal.base.inspection.hardware.OS;
import org.eclipse.passage.lic.internal.oshi.i18n.AssessmentMessages;

import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

@SuppressWarnings("restriction")
final class State {

	private final Map<EnvironmentProperty, String> hardware = new HashMap<>();
	private final String diskFamily = new Disk.Name().family();
	private final List<Map<EnvironmentProperty, String>> disks = new ArrayList<>();
	private final Object lock = new Object();

	boolean hasValue(EnvironmentProperty property, String expected) throws LicensingException {
		String regexp = expected.replaceAll("\\*", ".*"); //$NON-NLS-1$//$NON-NLS-2$
		synchronized (lock) {
			read();
			if (diskFamily.equals(property.family())) {
				return disks.stream()//
						.map(properties -> Optional.ofNullable(properties.get(property)))//
						.filter(Optional::isPresent)//
						.map(Optional::get)//
						.anyMatch(value -> value.matches(regexp));
			}
			return Optional.ofNullable(hardware.get(property))//
					.map(value -> value.matches(regexp))//
					.orElse(false);
		}
	}

	private void read() throws LicensingException {
		clean();
		try {
			SystemInfo system = new SystemInfo();
			readOS(system.getOperatingSystem());
			readHal(system.getHardware());
		} catch (Exception e) {
			throw new LicensingException(AssessmentMessages.State_error_reading_hw, e);
		}
	}

	private void clean() {
		disks.clear();
		hardware.clear();
	}

	private void readOS(OperatingSystem info) {
		store(info::getFamily, new OS.Family(), hardware);
		store(info::getManufacturer, new OS.Manufacturer(), hardware);
		store(info.getVersion()::getVersion, new OS.Version(), hardware);
		store(info.getVersion()::getBuildNumber, new OS.BuildNumber(), hardware);
	}

	private void readHal(HardwareAbstractionLayer hal) {
		readSystem(hal.getComputerSystem());
		readProcessor(hal.getProcessor());
		readDisks(hal.getDiskStores());
	}

	private void readSystem(ComputerSystem info) {
		store(info::getManufacturer, new Computer.Manufacturer(), hardware);
		store(info::getModel, new Computer.Model(), hardware);
		store(info::getSerialNumber, new Computer.Serial(), hardware);
		readBaseBoard(info.getBaseboard());
		readFirmware(info.getFirmware());
	}

	private void readBaseBoard(Baseboard info) {
		store(info::getManufacturer, new BaseBoard.Manufacturer(), hardware);
		store(info::getModel, new BaseBoard.Model(), hardware);
		store(info::getVersion, new BaseBoard.Version(), hardware);
		store(info::getSerialNumber, new BaseBoard.Serial(), hardware);
	}

	private void readFirmware(oshi.hardware.Firmware info) {
		store(info::getManufacturer, new Firmware.Manufacturer(), hardware);
		store(info::getVersion, new Firmware.Version(), hardware);
		store(info::getReleaseDate, new Firmware.ReleaseDate(), hardware);
		store(info::getName, new Firmware.Name(), hardware);
		store(info::getDescription, new Firmware.Description(), hardware);
	}

	private void readProcessor(CentralProcessor info) {
		store(info::getVendor, new Cpu.Vendor(), hardware);
		store(info::getFamily, new Cpu.Family(), hardware);
		store(info::getModel, new Cpu.Model(), hardware);
		store(info::getName, new Cpu.Name(), hardware);
		store(info::getProcessorID, new Cpu.ProcessorId(), hardware);
	}

	private void readDisks(HWDiskStore[] info) {
		Arrays.stream(info)//
				.map(this::diskProperties)//
				.forEach(this.disks::add);
	}

	private Map<EnvironmentProperty, String> diskProperties(HWDiskStore info) {
		Map<EnvironmentProperty, String> props = new HashMap<>();
		store(info::getName, new Disk.Name(), props);
		store(info::getModel, new Disk.Model(), props);
		store(info::getSerial, new Disk.Serial(), props);
		return props;
	}

	private void store(Supplier<String> value, EnvironmentProperty key, Map<EnvironmentProperty, String> target) {
		Optional.ofNullable(value.get()).ifPresent(valuable -> target.put(key, valuable));
	}
}
