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
package org.eclipse.passage.loc.yars.internal.api;

public interface ListMedia<T, C> {

	default ListMedia<T, C> start() {
		return this;
	}

	default ListMedia<T, C> finish() {
		return this;
	}

	default ListMedia<T, C> nodeStart(@SuppressWarnings("unused") T node) {
		return this;
	}

	default ListMedia<T, C> nodeFinish(@SuppressWarnings("unused") T node) {
		return this;
	}

	default ListMedia<T, C> inner(//
			@SuppressWarnings("unused") String data, //
			@SuppressWarnings("unused") String name) {
		return this;
	}

	C content();

}
