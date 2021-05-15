/*******************************************************************************
 * Copyright (c) 2021 ArSysOp
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
package org.eclipse.passage.lic.emf.migration;

import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;

/**
 * @since 2.0
 */
public interface EClassRoutes {

	void define(String found, EClass destination);

	Map<String, EClass> defined();

	Optional<EClass> route(String found);

	public static final class Smart implements EClassRoutes {

		private final EClassRoutes delegate;

		public Smart(EClassRoutes delegate) {
			this.delegate = delegate;
		}

		@Override
		public void define(String found, EClass destination) {
			delegate.define(found, destination);
		}

		public void define(EClass destination) {
			delegate.define(destination.getName(), destination);
		}

		@Override
		public Map<String, EClass> defined() {
			return delegate.defined();
		}

		@Override
		public Optional<EClass> route(String found) {
			return delegate.route(found);
		}

	}

}
