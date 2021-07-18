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
package org.eclipse.passage.lic.hc.remote.impl;

import org.eclipse.passage.lic.api.LicensedProduct;

/**
 * 
 * @since 1.1
 */
public abstract class RemoteServiceData {

	private final LicensedProduct product;

	protected RemoteServiceData(LicensedProduct product) {
		this.product = product;
	}

	public LicensedProduct product() {
		return product;
	}

	public static final class Bulk extends RemoteServiceData {

		public Bulk(LicensedProduct product) {
			super(product);
		}

	}

	public static final class OfFeature extends RemoteServiceData {

		private final String feature;

		public OfFeature(LicensedProduct product, String feature) {
			super(product);
			this.feature = feature;
		}

		public String feature() {
			return feature;
		}

	}

	public static final class WithPayload<T> extends RemoteServiceData {

		private final T payload;

		public WithPayload(LicensedProduct product, T payload) {
			super(product);
			this.payload = payload;
		}

		public T payload() {
			return payload;
		}

	}
}
