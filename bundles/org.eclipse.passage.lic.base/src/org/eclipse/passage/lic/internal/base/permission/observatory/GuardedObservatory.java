/*******************************************************************************
 * Copyright (c) 2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.internal.base.permission.observatory;

import java.util.Set;
import java.util.function.Consumer;

public final class GuardedObservatory<T extends Limited> {
	private final Observatory<T> observatory;
	private final Guard<T> guard;

	public GuardedObservatory(int schedule, Consumer<Set<T>> farewell) {
		observatory = new Observatory<T>();
		guard = new Guard<T>(schedule, observatory, farewell);
	}

	public void open() {
		new Thread(guard).start();
	}

	public void watch(T limited) {
		observatory.watch(limited);
	}

	public void forget(T limited) {
		observatory.forget(limited);
	}

}
