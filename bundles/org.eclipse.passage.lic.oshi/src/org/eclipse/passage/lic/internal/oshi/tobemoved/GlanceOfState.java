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

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.passage.lic.internal.api.inspection.EnvironmentProperty;
import org.eclipse.passage.lic.internal.base.inspection.hardware.Disk;

@SuppressWarnings("restriction")
final class GlanceOfState implements Supplier<String> {

	private final State state;

	GlanceOfState(State state) {
		Objects.requireNonNull(state);
		this.state = state;
	}

	@Override
	public String get() {
		StringBuilder out = new StringBuilder();
		state.properties().stream() //
				.collect(Collectors.groupingBy(EnvironmentProperty::family))//
				.entrySet().stream() //
				.forEach(e -> appendFamily(e.getKey(), e.getValue(), out));
		IntStream.range(0, state.disksAmount()) //
				.forEach(no -> appendDisk(no, out));
		return out.toString();
	}

	private void appendDisk(int no, StringBuilder out) {
		out.append(new Disk.Model().family())//
				.append(" #")//$NON-NLS-1$
				.append(no)//
				.append("\r\n"); //$NON-NLS-1$
		state.diskProperties(no).stream() //
				.forEach(p -> appendProperty(p, state.diskValue(no, p), out));
	}

	private void appendFamily(String family, List<EnvironmentProperty> properties, StringBuilder out) {
		out.append(family).append("\r\n"); //$NON-NLS-1$
		properties.stream().forEach(p -> appendProperty(p, state.value(p), out));
	}

	private StringBuilder appendProperty(EnvironmentProperty property, String value, StringBuilder out) {
		out.append("\t") //$NON-NLS-1$
				.append(property.name()) //
				.append(": ") //$NON-NLS-1$
				.append(value)//
				.append("\r\n"); //$NON-NLS-1$
		return out;
	}

}
