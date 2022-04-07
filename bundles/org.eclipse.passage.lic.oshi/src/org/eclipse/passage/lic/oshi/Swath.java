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
package org.eclipse.passage.lic.oshi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.eclipse.passage.lic.api.inspection.EnvironmentProperty;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Disk;
import org.eclipse.passage.lic.internal.base.inspection.hardware.NetworkInterface;

import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.NetworkIF;

/**
 * Pack of sibling entities in a hardware description
 */
abstract class Swath<T> {

	private final String family;

	protected final List<EnvironmentProperties> properties = new ArrayList<>();

	Swath(String family) {
		this.family = family;
	}

	final boolean relates(String anotehr) {
		return this.family.equals(anotehr);
	}

	final int capacity() {
		return properties.size();
	}

	final Set<EnvironmentProperty> properties(int no) {
		return properties.get(no).all();
	}

	final String value(int no, EnvironmentProperty key) {
		return properties.get(no).get(key);
	}

	final String family() {
		return family;
	}

	final boolean hasValue(EnvironmentProperty property, String regexp) {
		return properties.stream()//
				.map(props -> props.get(property))//
				.filter(Objects::nonNull)//
				.anyMatch(value -> value.matches(regexp));
	}

	final void read(SystemInfo system) {
		new FragileData<>(() -> source(system), this::readSource).supply();
	}

	private void readSource(T[] source) {
		Arrays.stream(source).forEach(src -> properties.add(fillProperties(src, new EnvironmentProperties())));
	}

	protected abstract T[] source(SystemInfo system);

	protected abstract EnvironmentProperties fillProperties(T source, EnvironmentProperties props);

	static final class Disks extends Swath<HWDiskStore> {

		Disks() {
			super(new Disk.Name().family());
		}

		@Override
		protected HWDiskStore[] source(SystemInfo system) {
			return system.getHardware().getDiskStores();
		}

		@Override
		protected EnvironmentProperties fillProperties(HWDiskStore source, EnvironmentProperties props) {
			props.store(source::getName, new Disk.Name());
			props.store(source::getModel, new Disk.Model());
			props.store(source::getSerial, new Disk.Serial());
			return props;
		}

	}

	static final class Nets extends Swath<NetworkIF> {

		Nets() {
			super(new NetworkInterface.Name().family());
		}

		@Override
		protected NetworkIF[] source(SystemInfo system) {
			return system.getHardware().getNetworkIFs();
		}

		@Override
		protected EnvironmentProperties fillProperties(NetworkIF source, EnvironmentProperties props) {
			props.store(source::getName, new NetworkInterface.Name());
			props.store(source::getDisplayName, new NetworkInterface.DisplayName());
			props.store(source::getMacaddr, new NetworkInterface.MacAddress());
			props.store(new NetHardwareAddress(source), new NetworkInterface.HwAddress());
			return props;
		}

	}

}
