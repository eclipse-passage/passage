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
package org.eclipse.passage.lic.internal.base;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public interface NamedData<T> extends Supplier<Optional<T>> {

	String key();

	default String printed(T value) {
		return String.valueOf(value);
	}

	class Writable<T> {
		private final NamedData<T> data;

		public Writable(NamedData<T> data) {
			this.data = data;
		}

		public void write(Map<String, Object> target) {
			data.get().ifPresent(value -> target.put(data.key(), data.printed(value)));
		}

		public void write(StringBuilder target) {
			data.get().ifPresent(//
					value -> target //
							.append(data.key()) //
							.append("=") //$NON-NLS-1$
							.append(data.printed(value)));
		}

	}

}
