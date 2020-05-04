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
package org.eclipse.passage.loc.yars.internal.api;

/**
 * Call-back interface for export client to be notified of an actual export
 * process state change.
 * 
 * @param <T> type of entities under export
 * @see Inane
 */
public interface Progress<T> {

	void estimate(int amount);

	void reportNodeSrart(T entry);

	void reportNodeFinish(T entry);

	void report(String info);

	boolean cancelDemanded();

	/**
	 * Empty implementation of the {@linkplain Progress} interfaces, no action is
	 * taken on any event.
	 */
	final class Inane<T> implements Progress<T> {

		@Override
		public void estimate(int amount) {
			// do nothing
		}

		@Override
		public void reportNodeSrart(T entry) {
			// do nothing
		}

		@Override
		public void reportNodeFinish(T entry) {
			// do nothing
		}

		@Override
		public void report(String info) {
			// do nothing
		}

		@Override
		public boolean cancelDemanded() {
			return false;
		}

	}

}
