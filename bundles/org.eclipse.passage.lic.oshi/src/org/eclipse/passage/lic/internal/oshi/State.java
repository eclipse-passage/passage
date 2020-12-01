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
package org.eclipse.passage.lic.internal.oshi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
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

/**
 * <p>
 * State has aggressively not thread-safe hardware state reading phase, which is
 * done on construction time.
 * </p>
 * <p>
 * Reading hardware (done eagerly on a State construction) boils down to lots
 * tricky native code invocation, which has static parts and does not stand
 * concurrent access.
 * </p>
 * <p>
 * Thus, State construction must be synchronized externally: until one State if
 * fully constructed, no another one must even think about it.
 * </p>
 * <p>
 * We construct it under a class-dedicated lock only in a
 * {@linkplain HardwareEnvironment} service, which, it turn, must have only
 * single instance at runtime (which is guaranteed by {@code Framework}).
 * </p>
 * <p>
 * Regarding to the data it collects - it's fully immutable, always fresh and
 * absolutely thread safe.
 * </p>
 */
@SuppressWarnings("restriction")
final class State {

	private final Map<EnvironmentProperty, String> hardware = new HashMap<>();
	private final String diskFamily = new Disk.Name().family();
	private final List<Map<EnvironmentProperty, String>> disks = new ArrayList<>();

	State() throws LicensingException {
		read();
	}

	boolean hasValue(EnvironmentProperty property, String expected) {
		String regexp = expected.replaceAll("\\*", ".*"); //$NON-NLS-1$//$NON-NLS-2$
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

	Set<EnvironmentProperty> properties() {
		return hardware.keySet();
	}

	String value(EnvironmentProperty key) {
		return hardware.get(key);
	}

	int disksAmount() {
		return disks.size();
	}

	Set<EnvironmentProperty> diskProperties(int no) {
		return disks.get(no).keySet();
	}

	String diskValue(int no, EnvironmentProperty key) {
		return disks.get(no).get(key);
	}

	private void read() throws LicensingException {
		try {
			SystemInfo system = new SystemInfo();
			readOS(system.getOperatingSystem());
			readHal(system.getHardware());
		} catch (Throwable e) {
			throw new LicensingException(AssessmentMessages.State_error_reading_hw, e);
		}
	}

	private void readOS(OperatingSystem info) {
		store(info::getFamily, new OS.Family(), hardware);
		store(info::getManufacturer, new OS.Manufacturer(), hardware);
		store(info.getVersion()::getVersion, new OS.Version(), hardware);
		store(info.getVersion()::getBuildNumber, new OS.BuildNumber(), hardware);
	}

	private void readHal(HardwareAbstractionLayer hal) {
		readHalPart(hal::getComputerSystem, this::readSystem);
		readHalPart(hal::getProcessor, this::readProcessor);
		readHalPart(hal::getDiskStores, this::readDisks);
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

	/**
	 * #569352 HAL likes to fail on native access, thus we isolate each HAL aspect
	 * assessment and legalize absence of corresponding properties.
	 */
	private <T> void readHalPart(Supplier<T> aspect, Consumer<T> read) {
		T descriptor;
		try {
			descriptor = aspect.get();
		} catch (Throwable any) {
			return; // legal; 'read' just isn't going to happen
		}
		read.accept(descriptor);
	}

}
