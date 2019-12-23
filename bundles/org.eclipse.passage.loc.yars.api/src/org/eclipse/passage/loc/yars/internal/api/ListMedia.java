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

/**
 * <p>
 * Represents a final export format, like CSV file (s) or JSON entry.
 * </p>
 * <p>
 * Intended to export ({@code write} to an output) <i>list</i>-like collection
 * (something iterable) of a single type ({@code T})
 * </p>
 * <p>
 * ListMedia has at least two clients:
 * </p>
 * <ul>
 * <li>Some target-format aware service: it creates a format-dedicated media and
 * iterates over a given <i>data</i>, writing each to the media. This client is
 * responsible for orchestrating media calls like {@linkplain #start()}.</li>
 * <li>Type {@code T}</li>
 * <li></li>
 * </ul>
 * 
 * @param T type of a single entity in a <i>list</i>
 * @param C output format type
 * @since 0.1
 */
public interface ListMedia<T, C> {

	/**
	 * @since 0.1
	 */
	default ListMedia<T, C> start() {
		return this;
	}

	/**
	 * @since 0.1
	 */
	default ListMedia<T, C> finish() {
		return this;
	}

	/**
	 * @since 0.1
	 */
	default ListMedia<T, C> startNode(@SuppressWarnings("unused") T node) {
		return this;
	}

	/**
	 * @since 0.1
	 */
	default ListMedia<T, C> finishNode(@SuppressWarnings("unused") T node) {
		return this;
	}

	/**
	 * @since 0.1
	 */
	default ListMedia<T, C> inner(//
			@SuppressWarnings("unused") String data, //
			@SuppressWarnings("unused") String name) {
		return this;
	}

	/**
	 * @since 0.1
	 */
	C content();

}
