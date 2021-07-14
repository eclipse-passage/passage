/*******************************************************************************
 * Copyright (c) 2020, 2021 ArSysOp
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
package org.eclipse.passage.lic.oshi;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.inspection.EnvironmentProperty;
import org.eclipse.passage.lic.internal.base.inspection.hardware.BaseBoard;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Computer;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Cpu;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Firmware;
import org.eclipse.passage.lic.internal.base.inspection.hardware.OS;
import org.eclipse.passage.lic.internal.oshi.i18n.AssessmentMessages;

import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
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
final class State {

	private final EnvironmentProperties hardware;
	private final List<Swath<?>> swaths;

	State() throws LicensingException {
		this.hardware = new EnvironmentProperties();
		this.swaths = Arrays.asList(new Swath.Disks(), new Swath.Nets());
		read(); // eager due to OSHI specifics
	}

	boolean hasValue(EnvironmentProperty property, String expected) {
		String regexp = expected.replaceAll("\\*", ".*"); //$NON-NLS-1$//$NON-NLS-2$
		Optional<Swath<?>> swath = swaths.stream().filter(sw -> sw.relates(property.family())).findAny();
		if (swath.isPresent()) {
			return swath.get().hasValue(property, regexp);
		}
		return Optional.ofNullable(hardware.get(property))//
				.map(value -> value.matches(regexp))//
				.orElse(false);
	}

	Set<EnvironmentProperty> properties() {
		return hardware.all();
	}

	String value(EnvironmentProperty key) {
		return hardware.get(key);
	}

	List<Swath<?>> swaths() {
		return swaths;
	}

	private void read() throws LicensingException {
		try {
			SystemInfo system = new SystemInfo();
			readOS(system.getOperatingSystem());
			readHal(system.getHardware());
			swaths.forEach(swath -> swath.read(system));
		} catch (Throwable e) {
			throw new LicensingException(AssessmentMessages.State_error_reading_hw, e);
		}
	}

	private void readOS(OperatingSystem info) {
		hardware.store(info::getFamily, new OS.Family());
		hardware.store(info::getManufacturer, new OS.Manufacturer());
		hardware.store(info.getVersion()::getVersion, new OS.Version());
		hardware.store(info.getVersion()::getBuildNumber, new OS.BuildNumber());
	}

	private void readHal(HardwareAbstractionLayer hal) {
		readPart(hal::getComputerSystem, this::readSystem);
		readPart(hal::getProcessor, this::readProcessor);
	}

	private void readSystem(ComputerSystem info) {
		hardware.store(info::getManufacturer, new Computer.Manufacturer());
		hardware.store(info::getModel, new Computer.Model());
		hardware.store(info::getSerialNumber, new Computer.Serial());
		readPart(info::getBaseboard, this::readBaseBoard);
		readPart(info::getFirmware, this::readFirmware);
	}

	private void readBaseBoard(Baseboard info) {
		hardware.store(info::getManufacturer, new BaseBoard.Manufacturer());
		hardware.store(info::getModel, new BaseBoard.Model());
		hardware.store(info::getVersion, new BaseBoard.Version());
		hardware.store(info::getSerialNumber, new BaseBoard.Serial());
	}

	private void readFirmware(oshi.hardware.Firmware info) {
		hardware.store(info::getManufacturer, new Firmware.Manufacturer());
		hardware.store(info::getVersion, new Firmware.Version());
		hardware.store(info::getReleaseDate, new Firmware.ReleaseDate());
		hardware.store(info::getName, new Firmware.Name());
		hardware.store(info::getDescription, new Firmware.Description());
	}

	private void readProcessor(CentralProcessor info) {
		hardware.store(info::getVendor, new Cpu.Vendor());
		hardware.store(info::getFamily, new Cpu.Family());
		hardware.store(info::getModel, new Cpu.Model());
		hardware.store(info::getName, new Cpu.Name());
		hardware.store(info::getProcessorID, new Cpu.ProcessorId());
	}

	/**
	 * #569352 HAL likes to fail on native access, thus we isolate each HAL aspect
	 * assessment and legalize absence of corresponding properties.
	 */
	private <T> void readPart(Supplier<T> aspect, Consumer<T> read) {
		new FragileData<>(aspect, read).supply();
	}

}
