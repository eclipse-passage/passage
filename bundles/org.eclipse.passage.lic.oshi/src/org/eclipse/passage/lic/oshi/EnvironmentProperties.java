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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

import org.eclipse.passage.lic.api.inspection.EnvironmentProperty;

final class EnvironmentProperties {

	private final Map<EnvironmentProperty, String> properties = new HashMap<>();
	private final EnvironmentProperty property;

	public EnvironmentProperties() {
		this(null);
	}

	public EnvironmentProperties(EnvironmentProperty property) {
		this.property = property;
	}

	void store(Supplier<String> value, EnvironmentProperty key) {
		if (property != null && !property.equals(key)) {
			return;
		}
		Optional<String> read;
		try {
			read = Optional.ofNullable(value.get());
		} catch (Throwable e) { // native errors
			read = Optional.empty();
		}
		read.ifPresent(valuable -> properties.put(key, valuable));
	}

	String get(EnvironmentProperty prop) {
		return properties.get(prop);
	}

	Set<EnvironmentProperty> all() {
		return properties.keySet();
	}

}
