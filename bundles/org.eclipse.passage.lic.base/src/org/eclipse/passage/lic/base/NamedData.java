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
package org.eclipse.passage.lic.base;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * <p>
 * Orthodox OOP construction designed for <i>getting a value from some source by
 * a {@code key}</i>. Main purpose is to encapsulate the {@code key} and avoid
 * static string literals.
 * </p>
 * <p>
 * Current contract is to have the any constructor light-weight and postpone
 * actual value reading at all cost - to make it reasonable to construct such a
 * data just to get the {@code key}. Yet debatable.
 * </p>
 * 
 * @param <T> type of a value to be supplied
 * 
 * @since 2.1
 */
public interface NamedData<T> extends Supplier<Optional<T>> {

	String key();

	default String printed(T value) {
		return String.valueOf(value);
	}

	default String keyValueSeparator() {
		return "="; //$NON-NLS-1$
	}

	default String entrySeparator() {
		return ""; //$NON-NLS-1$
	}

	public static final class Writable<T> {

		private final NamedData<T> data;

		public Writable(NamedData<T> data) {
			this.data = data;
		}

		public void write(Map<String, Object> target) {
			data.get().ifPresent(value -> target.put(data.key(), data.printed(value)));
		}

		public void write(StringBuilder target) {
			write(target, data.keyValueSeparator(), data.entrySeparator());
		}

		public void write(StringBuilder target, String mediator, String separator) {
			data.get().ifPresent(//
					value -> {
						if (target.length() > 0) {
							target.append(separator);
						}
						target //
								.append(data.key()) //
								.append(mediator) //
								.append(data.printed(value));
					});
		}

	}

}
