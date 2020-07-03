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
package org.eclipse.passage.lic.internal.bc.tests;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

abstract class PairInfo<I> {

	private final I first;
	private final I second;

	PairInfo(Path first, Path second) throws IOException {
		this.first = info(first);
		this.second = info(second);
	}

	I firstInfo() {
		return first;
	}

	I secondInfo() {
		return second;
	}

	protected abstract I info(Path file) throws IOException;

	@Override
	public boolean equals(Object object) {
		if (!getClass().isInstance(object)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		PairInfo<I> another = (PairInfo<I>) object;
		return Objects.deepEquals(first, another.first) //
				&& Objects.deepEquals(second, another.second);
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}
