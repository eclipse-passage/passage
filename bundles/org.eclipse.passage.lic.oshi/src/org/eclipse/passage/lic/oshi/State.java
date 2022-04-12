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

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.passage.lic.api.LicensingException;
import org.eclipse.passage.lic.api.inspection.EnvironmentProperty;
import org.eclipse.passage.lic.internal.base.Cached;
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

	State(EnvironmentProperty property) throws LicensingException {
		this.hardware = new EnvironmentProperties(property);
		this.swaths = Stream.of(new Swath.Disks(), new Swath.Nets())
				.filter(sw -> property == null || sw.relates(property.family())) //
				.collect(Collectors.toList());
		read(); // eager due to OSHI specifics
	}

	boolean hasValue(EnvironmentProperty prop, String expected) {
		String regexp = expected.replace("*", ".*"); //$NON-NLS-1$//$NON-NLS-2$
		Optional<Swath<?>> swath = swaths.stream().filter(sw -> sw.relates(prop.family())).findAny();
		if (swath.isPresent()) {
			return swath.get().hasValue(prop, regexp);
		}
		return Optional.ofNullable(hardware.get(prop))//
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
			readOS(cache(system::getOperatingSystem));
			readHal(cache(system::getHardware));
			swaths.forEach(swath -> swath.read(system));
		} catch (Throwable e) {
			throw new LicensingException(AssessmentMessages.State_error_reading_hw, e);
		}
	}

	private void readOS(Supplier<OperatingSystem> info) {
		hardware.store(() -> info.get().getFamily(), new OS.Family());
		hardware.store(() -> info.get().getManufacturer(), new OS.Manufacturer());
		hardware.store(() -> info.get().getVersion().getVersion(), new OS.Version());
		hardware.store(() -> info.get().getVersion().getBuildNumber(), new OS.BuildNumber());
	}

	private void readHal(Supplier<HardwareAbstractionLayer> hal) {
		readPart(cache(() -> hal.get().getComputerSystem()), this::readSystem);
		readPart(cache(() -> hal.get().getProcessor()), this::readProcessor);
	}

	private void readSystem(Supplier<ComputerSystem> info) {
		hardware.store(() -> info.get().getManufacturer(), new Computer.Manufacturer());
		hardware.store(() -> info.get().getModel(), new Computer.Model());
		hardware.store(() -> info.get().getSerialNumber(), new Computer.Serial());
		readPart(cache(() -> info.get().getBaseboard()), this::readBaseBoard);
		readPart(cache(() -> info.get().getFirmware()), this::readFirmware);
	}

	private void readBaseBoard(Supplier<Baseboard> info) {
		hardware.store(() -> info.get().getManufacturer(), new BaseBoard.Manufacturer());
		hardware.store(() -> info.get().getModel(), new BaseBoard.Model());
		hardware.store(() -> info.get().getVersion(), new BaseBoard.Version());
		hardware.store(() -> info.get().getSerialNumber(), new BaseBoard.Serial());
	}

	private void readFirmware(Supplier<oshi.hardware.Firmware> info) {
		hardware.store(() -> info.get().getManufacturer(), new Firmware.Manufacturer());
		hardware.store(() -> info.get().getVersion(), new Firmware.Version());
		hardware.store(() -> info.get().getReleaseDate(), new Firmware.ReleaseDate());
		hardware.store(() -> info.get().getName(), new Firmware.Name());
		hardware.store(() -> info.get().getDescription(), new Firmware.Description());
	}

	private void readProcessor(Supplier<CentralProcessor> info) {
		hardware.store(() -> info.get().getVendor(), new Cpu.Vendor());
		hardware.store(() -> info.get().getFamily(), new Cpu.Family());
		hardware.store(() -> info.get().getModel(), new Cpu.Model());
		hardware.store(() -> info.get().getName(), new Cpu.Name());
		hardware.store(() -> info.get().getProcessorID(), new Cpu.ProcessorId());
	}

	/**
	 * #569352 HAL likes to fail on native access, thus we isolate each HAL aspect
	 * assessment and legalize absence of corresponding properties.
	 */
	private <T> void readPart(Supplier<T> aspect, Consumer<Supplier<T>> read) {
		new FragileData<>(aspect, read).supply();
	}

	@SuppressWarnings("restriction")
	private static <T> Supplier<T> cache(Supplier<T> supplier) {
		return new Cached<>(supplier);
	}
}
