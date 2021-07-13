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

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * <p>
 * Data supplier dynamically fail on various native use cases with non-checked
 * platform-dependent exceptions.
 * </p>
 * <p>
 * Any failure is legal and means only that the actual data will not be
 * presented.
 * </p>
 */
final class FragileData<T> {

	private final Supplier<T> aspect;
	private final Consumer<T> read;

	FragileData(Supplier<T> aspect, Consumer<T> read) {
		this.aspect = aspect;
		this.read = read;
	}

	void supply() {
		T descriptor;
		try {
			descriptor = aspect.get();
		} catch (Throwable any) {
			return; // legal; 'read' just isn't going to happen
		}
		read.accept(descriptor);
	}

}
