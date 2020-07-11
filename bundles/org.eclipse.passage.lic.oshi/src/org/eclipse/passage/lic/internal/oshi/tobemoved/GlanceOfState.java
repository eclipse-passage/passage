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

import java.util.Objects;
import java.util.function.Supplier;

final class GlanceOfState implements Supplier<String> {

	private final State state;

	GlanceOfState(State state) {
		Objects.requireNonNull(state);
		this.state = state;
	}

	@Override
	public String get() {
		return "ytbd"; //$NON-NLS-1$
	}

}
