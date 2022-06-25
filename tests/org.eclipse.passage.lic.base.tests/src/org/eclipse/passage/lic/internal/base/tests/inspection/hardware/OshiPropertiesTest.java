/*******************************************************************************
 * Copyright (c) 2022 ArSysOp
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
package org.eclipse.passage.lic.internal.base.tests.inspection.hardware;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.eclipse.passage.lic.api.inspection.EnvironmentProperty;
import org.eclipse.passage.lic.internal.base.inspection.hardware.BaseBoard;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Computer;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Cpu;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Disk;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Firmware;
import org.eclipse.passage.lic.internal.base.inspection.hardware.NetworkInterface;
import org.eclipse.passage.lic.internal.base.inspection.hardware.OS;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class OshiPropertiesTest {

	@Test
	public void allDifferent() {
		// having
		AtomicInteger amount = new AtomicInteger(0);
		// when
		Set<EnvironmentProperty> all = eachAndEveryProperty(amount);
		// then
		assertEquals(amount.get(), all.size());
	}

	private Set<EnvironmentProperty> eachAndEveryProperty(AtomicInteger additions) {
		return Arrays.asList(//
				new BaseBoard.Manufacturer(), //
				new BaseBoard.Model(), //
				new BaseBoard.Serial(), //
				new BaseBoard.Version(), //
				new Computer.Manufacturer(), //
				new Computer.Model(), //
				new Computer.Serial(), //
				new Cpu.Identifier(), //
				new Cpu.Family(), //
				new Cpu.Model(), //
				new Cpu.Name(), //
				new Cpu.ProcessorId(), //
				new Cpu.Vendor(), //
				new Disk.Model(), //
				new Disk.Name(), //
				new Disk.Serial(), //
				new Firmware.Description(), //
				new Firmware.Manufacturer(), //
				new Firmware.Name(), //
				new Firmware.ReleaseDate(), //
				new Firmware.Version(), //
				new NetworkInterface.DisplayName(), //
				new NetworkInterface.HwAddress(), //
				new NetworkInterface.MacAddress(), //
				new NetworkInterface.Name(), //
				new OS.BuildNumber(), //
				new OS.Family(), //
				new OS.Manufacturer(), //
				new OS.Version()//
		).stream()//
				.peek(property -> additions.incrementAndGet())//
				.collect(Collectors.toSet());

	}
}
