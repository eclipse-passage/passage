/*******************************************************************************
 * Copyright (c) 2021, 2022 ArSysOp
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
package org.eclipse.passage.loc.internal.api.workspace;

import java.util.Optional;

import org.eclipse.passage.lic.api.LicensedProduct;

public interface Keys {

	Optional<String> existing(String product, String version);

	ResourceHandle located(String product, String version);

	ResourceHandle locatedPub(String product, String version);

	ResourceType xmi = new ResourceType() {

		@Override
		public String id() {
			return "keys_xmi"; //$NON-NLS-1$
		}
	};

	ResourceType pub = new ResourceType() {

		@Override
		public String id() {
			return "pub"; //$NON-NLS-1$
		}
	};

	public static final class Smart implements Keys {

		private final Keys delegate;

		public Smart(Keys delegate) {
			this.delegate = delegate;
		}

		@Override
		public Optional<String> existing(String product, String version) {
			return delegate.existing(product, version);
		}

		public Optional<String> existing(LicensedProduct product) {
			return existing(product.identifier(), product.version());
		}

		public boolean exists(LicensedProduct product) {
			return existing(product).isPresent();
		}

		@Override
		public ResourceHandle located(String product, String version) {
			return delegate.located(product, version);
		}

		public ResourceHandle located(LicensedProduct product) {
			return delegate.located(product.identifier(), product.version());
		}

		@Override
		public ResourceHandle locatedPub(String product, String version) {
			return delegate.locatedPub(product, version);
		}

		public ResourceHandle locatedPub(LicensedProduct product) {
			return delegate.locatedPub(product.identifier(), product.version());
		}

	}

}
